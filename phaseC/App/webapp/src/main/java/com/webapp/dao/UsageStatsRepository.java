package com.webapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.bean.UsageStats;
@Repository
public interface UsageStatsRepository extends JpaRepository<UsageStats, Integer>{

}

