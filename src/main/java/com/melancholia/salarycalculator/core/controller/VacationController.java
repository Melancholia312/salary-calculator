package com.melancholia.salarycalculator.core.controller;

import com.melancholia.salarycalculator.core.controller.model.CalculatedVacationResultDto;
import com.melancholia.salarycalculator.core.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class VacationController implements VacationApi {

    private final CalculationService calculationService;

    @Override
    public ResponseEntity<CalculatedVacationResultDto> calculateVacation(BigDecimal averageSalary,
                                                                         LocalDate vacationStartDate,
                                                                         LocalDate vacationEndDate,
                                                                         Integer vacationDays) {
        var entity = calculationService.salaryCalculate(averageSalary, vacationStartDate, vacationEndDate, vacationDays);
        return ResponseEntity
                .ok(entity);
    }
}
