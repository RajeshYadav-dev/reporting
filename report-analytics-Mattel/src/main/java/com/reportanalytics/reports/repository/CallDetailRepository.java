package com.reportanalytics.reports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reportanalytics.entities.CallDetail;


@Repository
public interface CallDetailRepository extends JpaRepository<CallDetail, Long> {
	@Query(value = "SELECT * FROM call_detail WHERE call_start_time >= :startTime AND call_end_time <= :endTime", nativeQuery = true)
	public List<CallDetail> getCallDetailByDate(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
