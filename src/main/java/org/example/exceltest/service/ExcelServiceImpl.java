package org.example.exceltest.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    @Override
    public Integer findNmax(String filePath, Integer n) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден: " + filePath);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)){
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        Integer num = (int) cell.getNumericCellValue();
                        minHeap.add(num);
                        if (minHeap.size() > n) {
                            minHeap.poll();
                        }
                    }
                }
            }
            if (minHeap.size() < n) {
                throw new IllegalArgumentException("Недостаточно чисел в файле");
            }
            return minHeap.peek();
        }
    }
}
