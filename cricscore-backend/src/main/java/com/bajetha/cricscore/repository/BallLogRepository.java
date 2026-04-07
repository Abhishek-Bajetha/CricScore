package com.bajetha.cricscore.repository;

import com.bajetha.cricscore.entity.BallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallLogRepository extends JpaRepository<BallLog, Long> {

}
