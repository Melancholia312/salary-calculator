package com.melancholia.salarycalculator.core.service;

import com.melancholia.salarycalculator.core.controller.model.CalculatedVacationResultDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculationService {

    CalculatedVacationResultDto salaryCalculate(BigDecimal salary, LocalDate startDate, LocalDate endDate, Integer vacationDays);

}
