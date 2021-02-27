package com.fabriciolondero.JasperReportsTeste.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabriciolondero.JasperReportsTeste.Models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> 
{
	
}
