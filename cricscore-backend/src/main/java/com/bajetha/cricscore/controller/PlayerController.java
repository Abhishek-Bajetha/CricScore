package com.bajetha.cricscore.controller;


import com.bajetha.cricscore.dto.AddPlayerToTeamRequest;
import com.bajetha.cricscore.entity.Player;
import com.bajetha.cricscore.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/add-to-team")
    public ResponseEntity<?> addPlayerToTeam(@RequestBody AddPlayerToTeamRequest request) {

        try {

            Player savedPlayer = playerService.addPlayerToTeam(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}
