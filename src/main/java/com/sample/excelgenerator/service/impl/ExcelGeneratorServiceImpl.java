package com.sample.excelgenerator.service.impl;

import com.sample.excelgenerator.dto.Policy;
import com.sample.excelgenerator.service.ExcelGeneratorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExcelGeneratorServiceImpl implements ExcelGeneratorService {
    @Autowired
    private HttpServletRequest request;

    @Value("${file.path}")
    private String filePath;
    private final List<String> columnNameList = List.of("Policy Number", "Producer", "Application Method");
    /*private XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFSheet sheet;
    private List<Policy> listPolicy;

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Policies");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Policy Nuber", style);
        createCell(row, 1, "Producer", style);
        createCell(row, 2, "App Method", style);
       *//* createCell(row, 3, "Roles", style);
        createCell(row, 4, "Enabled", style);*//*

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Policy policy : listPolicy) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, policy.getPolicyNumber(), style);
            createCell(row, columnCount++, policy.getProducer(), style);
            createCell(row, columnCount++, policy.getAppMethod(), style);
            *//*createCell(row, columnCount++, policy.getRoles().toString(), style);
            createCell(row, columnCount++, policy.isEnabled(), style);*//*

        }
    }

    @Override
    public void generateExcel(HttpServletResponse response, List<Policy> policyList) throws IOException{
        this.listPolicy = policyList;
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
*/

    public void generateExcel(final HttpServletResponse response, List<Policy> policyList) throws IOException {
        OutputStream outputStream;
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        String fileName = "Policy"+currentDateTime+".xlsx";
        System.out.println(fileName);
        if (response!=null) {
            response.setContentType("application/octet-stream");
            response.setHeader(headerKey, "attachment; filename="+fileName);
            outputStream = new BufferedOutputStream(response.getOutputStream());
        }else {
            MediaType mediaType = MediaType.parseMediaType("application/vnd.ms-excel");
            File file = new File(filePath+"//"+fileName);
            file.createNewFile();
            outputStream = new FileOutputStream(file);
        }

        final var workBook = new XSSFWorkbook();
        final var workSheet = workBook.createSheet("Employee Records");
        final var initialRow = workSheet.createRow(0);

        // To make heading row's font bold
        final var style = workBook.createCellStyle();
        final var font = workBook.createFont();
        font.setBold(true);
        style.setFont(font);

        // Writing heading cell names in inital-row
        for (int column = 0; column < columnNameList.size(); column++) {
            final var cell = initialRow.createCell(column);
            cell.setCellStyle(style);
            cell.setCellValue(columnNameList.get(column));
        }

        // Iterating over each employee
        for (int row = 0; row < policyList.size(); row++) {

            // making a new row representing the current employee
            final var currentRow = workSheet.createRow(row + 1);

            // populating current employees data in columns
            for (int column = 0; column < columnNameList.size(); column++) {
                final var currentCell = currentRow.createCell(column);
                final var currentPolicy = policyList.get(row);
                currentCell.setCellValue(getCellValue(column, currentPolicy));
            }

        }

        // auto sizing each column in worksheet
        for (int column = 0; column < columnNameList.size(); column++) {
            workSheet.autoSizeColumn(column);
        }
        workBook.write(outputStream);
        workBook.close();
        outputStream.close();
    }

    private String getCellValue(int columnIndex, Policy policy) {
        switch (columnIndex) {
            case 0: {
                return policy.getPolicyNumber().toString();
            }
            case 1: {
                return policy.getProducer();
            }
            case 2: {
                return policy.getAppMethod();
            }

        }
        return "NaN";
    }
}
