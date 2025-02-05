package org.example.exceltest.service;


import java.io.IOException;

public interface ExcelService {
    Integer findNmax(String filePath, Integer n) throws IOException;
}
