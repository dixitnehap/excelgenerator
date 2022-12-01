package com.sample.excelgenerator.service;

import com.sample.excelgenerator.dto.Policy;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ExcelGeneratorService {
    public void generateExcel(HttpServletResponse response,  List<Policy> policyList) throws IOException;
}
