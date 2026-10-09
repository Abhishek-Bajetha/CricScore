package com.bajetha.cricscore.service;

import com.bajetha.cricscore.dto.BallScoringRequest;
import com.bajetha.cricscore.entity.BallLog;
import com.bajetha.cricscore.entity.Match;
import com.bajetha.cricscore.entity.Player;
import com.bajetha.cricscore.repository.BallLogRepository;
import com.bajetha.cricscore.repository.MatchRepository;
import com.bajetha.cricscore.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ScoringService {

    @Autowired
    BallLogRepository ballLogRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Transactional
    public BallLog recordBall(BallScoringRequest request) {

        Match match = matchRepository.findById(request.getMatchId()).
                orElseThrow(() -> new RuntimeException("Match not found"));

        if ("COMPLETED".equals(match.getStatus()))
            throw new RuntimeException("You cannot score a ball for a finished game!");

        if ("CREATED".equals(match.getStatus())) {
            match.setStatus("ONGOING");
            matchRepository.save(match);
        }

        Player striker = playerRepository.findById(request.getStrikerId())
                .orElseThrow(() -> new RuntimeException("Striker not found"));
        Player nonStriker = playerRepository.findById(request.getNonStrikerId())
                .orElseThrow(() -> new RuntimeException("Non-Striker not found"));
        Player bowler = playerRepository.findById(request.getBowlerId())
                .orElseThrow(() -> new RuntimeException("Bowler not found"));


        BallLog newBallLog = new BallLog();
        newBallLog.setMatch(match);
        newBallLog.setStriker(striker);
        newBallLog.setNonStriker(nonStriker);
        newBallLog.setBowler(bowler);

        newBallLog.setInningNumber(request.getInningNumber());
        newBallLog.setOverNumber(request.getOverNumber());
        newBallLog.setBallNumber(request.getBallNumber());
        newBallLog.setRunsScored(request.getRunsScored());
        newBallLog.setExtras(request.getExtras());
        newBallLog.setExtraType(request.getExtraType());
        newBallLog.setWicketType(request.getWicketType());

        if (request.getWicketPlayerId() != null) {
            Player wicketPlayer = playerRepository.findById(request.getWicketPlayerId())
                    .orElseThrow(() -> new RuntimeException("Wicket player not found"));

            newBallLog.setWicketPlayer(wicketPlayer);
        }

        return ballLogRepository.save(newBallLog);
    }

}
