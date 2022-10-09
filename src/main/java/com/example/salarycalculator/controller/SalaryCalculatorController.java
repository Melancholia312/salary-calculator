package com.example.salarycalculator.controller;

import com.example.salarycalculator.model.ResultBuilder;
import com.example.salarycalculator.model.ApiResult;
import com.example.salarycalculator.service.SalaryCalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryCalculatorController {

    private final SalaryCalculatorService salaryCalculatorService;

    public SalaryCalculatorController(SalaryCalculatorService salaryCalculatorService){
        this.salaryCalculatorService = salaryCalculatorService;
    }

    @GetMapping("/calculate")
    public ApiResult calculate(
            @RequestParam(name = "salary") double salary,
            @RequestParam(name = "days") int days) {

        try {
            double result = salaryCalculatorService.calculateFreeDaysSalary(salary, days);
            return ResultBuilder.getResult(result);
        }
        catch (IllegalArgumentException ex) {
            return ResultBuilder.getResultFromError(ex.getMessage());
        }
    }
}