package com.fabriciolondero.JasperReportsTeste.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fabriciolondero.JasperReportsTeste.Models.Employee;
import com.fabriciolondero.JasperReportsTeste.Repositories.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService 
{

    @Autowired
    private EmployeeRepository repository;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException 
    {
        String path = "D:\\OneDrive\\Aulas\\UFN - UNIFRA\\Curso - Java - Atos\\Gráficos e Relatórios\\JasperReport\\JasperReportsTeste\\src\\main\\resources\\relatorios";
        
        List<Employee> employees = repository.findAll();
        
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
       
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("criado por ", "Fabrício Londero");
       
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        if (reportFormat.equalsIgnoreCase("html")) 
        {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) 
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        }

        return "relatório gerado em : " + path;
    }
}