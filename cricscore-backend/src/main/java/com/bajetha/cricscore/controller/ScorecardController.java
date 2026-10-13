package com.bajetha.cricscore.controller;

import com.bajetha.cricscore.dto.ScorecardResponse;
import com.bajetha.cricscore.service.ScorecardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class ScorecardController {

    @Autowired
    ScorecardService scorecardService;

    @GetMapping("/scorecard/{matchId}")
    public ResponseEntity<?> getScorecard(@PathVariable Long matchId) {
        try {
            ScorecardResponse response = scorecardService.getScorecard(matchId);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}