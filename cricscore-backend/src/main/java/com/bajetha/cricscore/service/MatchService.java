package com.bajetha.cricscore.service;

import com.bajetha.cricscore.dto.CreateMatchRequest;
import com.bajetha.cricscore.entity.Match;
import com.bajetha.cricscore.entity.Team;
import com.bajetha.cricscore.repository.MatchRepository;
import com.bajetha.cricscore.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Transactional
    public Match createMatch(CreateMatchRequest request) {

//       Check both teams are not the same
        if (request.getTeamAId().equals(request.getTeamBId())) {
            throw new RuntimeException("A team cannot play against itself!");
        }

//        Check both teams exists or not

        Team teamA = teamRepository.findById(request.getTeamAId())
                .orElseThrow(() ->
                        new RuntimeException("Team A not found"));

        Team teamB = teamRepository.findById(request.getTeamBId())
                .orElseThrow(() ->
                        new RuntimeException("Team B not found"));



        Match match = new Match();

        match.setTeamA(teamA);
        match.setTeamB(teamB);

        match.setTotalOvers(request.getTotalOvers());
        match.setVenue(request.getVenue());

        match.setStatus("CREATED");
        match.setDate(LocalDateTime.now());

        return matchRepository.save(match);
    }
}
