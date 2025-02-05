package org.example.exceltest.controller;

import lombok.RequiredArgsConstructor;
import org.example.exceltest.service.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelService excelService;

    @GetMapping("/find-n-max")
    public Integer findNMax(@RequestParam String filePath, @RequestParam Integer n) throws Exception {
        return excelService.findNmax(filePath, n);
    }
}
