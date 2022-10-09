package com.example.salarycalculator.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryCalculatorServiceTest {

    private SalaryCalculatorService salaryCalculatorService = new SalaryCalculatorService();

    @Test
    void calculateFreeDaysSalary() {
        double result = salaryCalculatorService.calculateFreeDaysSalary(100000, 25);
        assertEquals(6849.32, result);

        double result1 = salaryCalculatorService.calculateFreeDaysSalary(100000, 365);
        assertEquals(100000, result1);

        assertThrows(IllegalArgumentException.class, () -> salaryCalculatorService.calculateFreeDaysSalary(-1, 25));

        assertThrows(IllegalArgumentException.class, () -> salaryCalculatorService.calculateFreeDaysSalary(10000, 0));

        assertThrows(IllegalArgumentException.class, () -> salaryCalculatorService.calculateFreeDaysSalary(10000, 366));
    }
}