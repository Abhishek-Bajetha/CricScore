package com.bajetha.cricscore.service;

import com.bajetha.cricscore.dto.BattingStatDto;
import com.bajetha.cricscore.dto.BowlingStatDto;
import com.bajetha.cricscore.dto.ScorecardResponse;
import com.bajetha.cricscore.entity.BallLog;
import com.bajetha.cricscore.entity.Match;
import com.bajetha.cricscore.entity.Player;
import com.bajetha.cricscore.repository.BallLogRepository;
import com.bajetha.cricscore.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScorecardService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private BallLogRepository ballLogRepository;

    public ScorecardResponse getScorecard(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with ID: " + matchId));

        List<BallLog> ballLogs = ballLogRepository.findByMatchMatchId(matchId);

        int totalRuns = 0;
        int totalWickets = 0;
        int totalLegalBalls = 0;

        Map<Long, BattingStatDto> battingMap = new HashMap<>();
        Map<Long, BowlingStatDto> bowlingMap = new HashMap<>();

        for (BallLog ball : ballLogs) {
            boolean isWide = "WIDE".equalsIgnoreCase(ball.getExtraType());
            boolean isNoBall = "NO_BALL".equalsIgnoreCase(ball.getExtraType());
            boolean isLegalBall = !isWide && !isNoBall;

            int ballRuns = ball.getRunsScored() + ball.getExtras();
            totalRuns += ballRuns;

            if (ball.getWicketType() != null && !"NONE".equalsIgnoreCase(ball.getWicketType())) {
                totalWickets++;
            }

            if (isLegalBall) {
                totalLegalBalls++;
            }

            // 1. Process Batting Stats
            Player striker = ball.getStriker();
            if (striker != null) {
                BattingStatDto batStat = battingMap.computeIfAbsent(striker.getPlayerId(), id -> {
                    BattingStatDto dto = new BattingStatDto();
                    dto.setPlayerId(striker.getPlayerId());
                    dto.setPlayerName(striker.getName());
                    return dto;
                });

                batStat.setRuns(batStat.getRuns() + ball.getRunsScored());
                if (!isWide) {
                    batStat.setBallsFaced(batStat.getBallsFaced() + 1);
                }
                if (ball.getRunsScored() == 4) {
                    batStat.setFours(batStat.getFours() + 1);
                }
                if (ball.getRunsScored() == 6) {
                    batStat.setSixes(batStat.getSixes() + 1);
                }
            }

            // 2. Process Bowling Stats
            Player bowler = ball.getBowler();
            if (bowler != null) {
                BowlingStatDto bowlStat = bowlingMap.computeIfAbsent(bowler.getPlayerId(), id -> {
                    BowlingStatDto dto = new BowlingStatDto();
                    dto.setPlayerId(bowler.getPlayerId());
                    dto.setPlayerName(bowler.getName());
                    return dto;
                });

                // Byes and Leg Byes aren't charged to bowler
                boolean isByeOrLegBye = "BYE".equalsIgnoreCase(ball.getExtraType()) || "LEG_BYE".equalsIgnoreCase(ball.getExtraType());
                int bowlerRunsConceded = isByeOrLegBye ? 0 : ballRuns;
                bowlStat.setRunsConceded(bowlStat.getRunsConceded() + bowlerRunsConceded);

                if (ball.getWicketType() != null && !"NONE".equalsIgnoreCase(ball.getWicketType()) && !"RUN_OUT".equalsIgnoreCase(ball.getWicketType())) {
                    bowlStat.setWickets(bowlStat.getWickets() + 1);
                }
            }
        }

        // Calculate derived stats (Strike Rates, Economy, Overs string)
        battingMap.values().forEach(bat -> {
            if (bat.getBallsFaced() > 0) {
                double sr = (bat.getRuns() * 100.0) / bat.getBallsFaced();
                bat.setStrikeRate(Math.round(sr * 100.0) / 100.0);
            }
        });

        // Compute bowler overs
        Map<Long, Integer> bowlerLegalBallsMap = new HashMap<>();
        for (BallLog ball : ballLogs) {
            boolean isWide = "WIDE".equalsIgnoreCase(ball.getExtraType());
            boolean isNoBall = "NO_BALL".equalsIgnoreCase(ball.getExtraType());
            if (!isWide && !isNoBall && ball.getBowler() != null) {
                Long bId = ball.getBowler().getPlayerId();
                bowlerLegalBallsMap.put(bId, bowlerLegalBallsMap.getOrDefault(bId, 0) + 1);
            }
        }

        bowlingMap.values().forEach(bowl -> {
            int balls = bowlerLegalBallsMap.getOrDefault(bowl.getPlayerId(), 0);
            int completeOvers = balls / 6;
            int remainingBalls = balls % 6;
            double oversFormatted = Double.parseDouble(completeOvers + "." + remainingBalls);
            bowl.setOversBowled(oversFormatted);

            double oversDecimal = completeOvers + (remainingBalls / 6.0);
            if (oversDecimal > 0) {
                double eco = bowl.getRunsConceded() / oversDecimal;
                bowl.setEconomy(Math.round(eco * 100.0) / 100.0);
            }
        });

        // Format overall match totals
        int totalOversCompleted = totalLegalBalls / 6;
        int remainingBallsInOver = totalLegalBalls % 6;
        String formattedOvers = totalOversCompleted + "." + remainingBallsInOver + " / " + match.getTotalOvers();

        double totalOversDecimal = totalOversCompleted + (remainingBallsInOver / 6.0);
        double crr = totalOversDecimal > 0 ? Math.round((totalRuns / totalOversDecimal) * 100.0) / 100.0 : 0.0;

        // Populate ScorecardResponse
        ScorecardResponse response = new ScorecardResponse();
        response.setMatchId(match.getMatchId());
        response.setTeamAName(match.getTeamA().getTeamName());
        response.setTeamBName(match.getTeamB().getTeamName());
        response.setStatus(match.getStatus());
        response.setTotalRuns(totalRuns);
        response.setTotalWickets(totalWickets);
        response.setTotalOvers(formattedOvers);
        response.setCurrentRunRate(crr);
        response.setBatters(new ArrayList<>(battingMap.values()));
        response.setBowlers(new ArrayList<>(bowlingMap.values()));

        return response;
    }
}