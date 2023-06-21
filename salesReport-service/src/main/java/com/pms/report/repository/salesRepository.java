package com.pms.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.report.model.Sales;

public interface salesRepository extends JpaRepository<Sales, Integer> {

}
