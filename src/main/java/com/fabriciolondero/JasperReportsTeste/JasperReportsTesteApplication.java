package com.fabriciolondero.JasperReportsTeste;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fabriciolondero.JasperReportsTeste.Repositories.EmployeeRepository;
import com.fabriciolondero.JasperReportsTeste.Services.ReportService;

import net.sf.jasperreports.engine.JRException;

import com.fabriciolondero.JasperReportsTeste.Models.Employee;

@SpringBootApplication
@RestController
public class JasperReportsTesteApplication 
{
	@Autowired
    private EmployeeRepository repository;
	
    @Autowired
    private ReportService service;
    
    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() 
    {

        return repository.findAll();
    }
    
    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException 
    {
        return service.exportReport(format);
    }

	public static void main(String[] args)
	{
		SpringApplication.run(JasperReportsTesteApplication.class, args);
	}

}
