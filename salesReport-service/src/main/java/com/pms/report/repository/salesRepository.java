package com.pms.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pms.report.model.Sales;
@Repository
public interface salesRepository extends JpaRepository<Sales, Integer> {

	//custom query to find report by date is not working
}
