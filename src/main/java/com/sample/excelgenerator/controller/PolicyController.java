package com.sample.excelgenerator.controller;

import com.sample.excelgenerator.service.ExcelGeneratorService;
import com.sample.excelgenerator.service.PolicyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("policy")
public class PolicyController {
    @Autowired
    PolicyService policyService;
    @Autowired
    ExcelGeneratorService excelGeneratorService;

    @Scheduled(fixedRate = 30000)
    public void generateExcelWithScheduler() throws IOException {

        //excelGeneratorService.generateExcel(response, policyService.listAll());
        excelGeneratorService.generateExcel(null, policyService.listAll());
        System.out.println("------------excel generated with Scheduler------------");
    }

    @GetMapping(value = "/excel")
    public void generateExcelWithAPI(HttpServletResponse response) throws IOException {
        excelGeneratorService.generateExcel(response, policyService.listAll());
        excelGeneratorService.generateExcel(response, policyService.listAll());
        System.out.println("------------excel generated with API call------------");
    }
}
