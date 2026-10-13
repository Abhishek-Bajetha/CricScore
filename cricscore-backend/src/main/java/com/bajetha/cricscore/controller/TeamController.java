package com.bajetha.cricscore.controller;

import com.bajetha.cricscore.dto.CreateTeamRequest;
import com.bajetha.cricscore.entity.Team;
import com.bajetha.cricscore.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/createTeam")
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamRequest request) {
        try {

            Team savedTeam = teamService.createTeam(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTeam);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
