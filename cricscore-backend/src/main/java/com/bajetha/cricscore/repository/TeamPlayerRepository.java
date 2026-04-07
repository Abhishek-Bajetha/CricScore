package com.bajetha.cricscore.repository;

import com.bajetha.cricscore.entity.Team;
import com.bajetha.cricscore.entity.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Long> {
    List<TeamPlayer> findByTeam(Team team);
}
