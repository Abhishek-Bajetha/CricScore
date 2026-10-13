package com.bajetha.cricscore.service;

import com.bajetha.cricscore.dto.CreateTeamRequest;
import com.bajetha.cricscore.entity.Team;
import com.bajetha.cricscore.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team createTeam(CreateTeamRequest request) {

        if(teamRepository.findByTeamName(request.getTeamName()).isPresent()){
            throw new RuntimeException("Team with this name already exists");
        }

        Team newTeam = new Team();

        newTeam.setTeamName(request.getTeamName());
        newTeam.setShortName(request.getShortName());

        return teamRepository.save(newTeam);
    }
}
