package com.bajetha.cricscore.controller;

import com.bajetha.cricscore.dto.CreateMatchRequest;
import com.bajetha.cricscore.entity.Match;
import com.bajetha.cricscore.entity.Player;
import com.bajetha.cricscore.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping("/create")
    public ResponseEntity<?> matchController(@RequestBody CreateMatchRequest createMatchRequest) {

        try {

            Match createMatch = matchService.createMatch(createMatchRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createMatch);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }


}
