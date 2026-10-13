package com.bajetha.cricscore.controller;

import com.bajetha.cricscore.dto.BallScoringRequest;
import com.bajetha.cricscore.entity.BallLog;
import com.bajetha.cricscore.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scoring")
public class ScoringController {

    @Autowired
    ScoringService scoringService;

    @PostMapping("/recordBall")
    public ResponseEntity<?> scoringRequest(@RequestBody BallScoringRequest ballScoringRequest) {
        try {
            BallLog savedBall = scoringService.recordBall(ballScoringRequest);
            return ResponseEntity.ok(savedBall);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
