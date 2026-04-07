package com.bajetha.cricscore.service;

import com.bajetha.cricscore.dto.AddPlayerToTeamRequest;
import com.bajetha.cricscore.entity.Player;
import com.bajetha.cricscore.entity.Team;
import com.bajetha.cricscore.entity.TeamPlayer;
import com.bajetha.cricscore.repository.PlayerRepository;
import com.bajetha.cricscore.repository.TeamPlayerRepository;
import com.bajetha.cricscore.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired

    TeamRepository teamRepository;

    @Autowired
    TeamPlayerRepository teamPlayerRepository;

    @Transactional
    public Player addPlayerToTeam(AddPlayerToTeamRequest request) {

//        Team exists or not
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Player player;

//      if guest player
        if (request.isShadow()) {
            String dummyMobile = "SHADOW_" + UUID.randomUUID().toString().substring(0, 8);
            player = new Player();
            player.setName(request.getName());
            player.setMobileNumber(dummyMobile);
            player.setRegistered(false);

            player = playerRepository.save(player);
        }
//      IF player is real
        else {
            player = playerRepository.findByMobileNumber(request.getMobileNumber())
                    .orElse(null);

            // IF player real but not registered
            if (player == null) {
                player = new Player();
                player.setName(request.getName());
                player.setMobileNumber(request.getMobileNumber());
                player.setRegistered(false);

                player = playerRepository.save(player);
            }
        }
        TeamPlayer teamPlayer = new TeamPlayer();
        teamPlayer.setTeam(team);
        teamPlayer.setPlayer(player);

        String role = (request.getRole() != null) ? request.getRole() : "MEMBER";
        teamPlayer.setRole(role);

        teamPlayerRepository.save(teamPlayer);
        return player;
    }
}
