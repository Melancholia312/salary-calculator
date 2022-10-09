package com.example.salarycalculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SalaryCalculatorService {

    final int daysInYear = 365;

    public double calculateFreeDaysSalary(double salary, int freeDays){
        if (salary < 0 || freeDays <= 0 || freeDays > daysInYear) {
            throw new IllegalArgumentException("Wrong parameters");
        }
        double result = salary / daysInYear * freeDays;
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
