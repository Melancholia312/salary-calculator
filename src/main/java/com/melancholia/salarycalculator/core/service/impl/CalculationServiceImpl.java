package com.melancholia.salarycalculator.core.service.impl;

import com.melancholia.salarycalculator.core.controller.model.CalculatedVacationResultDto;
import com.melancholia.salarycalculator.core.service.CalculationService;
import com.melancholia.salarycalculator.core.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final HolidayService holidayService;

    public CalculatedVacationResultDto salaryCalculate(BigDecimal salary,
                                                       LocalDate startDate,
                                                       LocalDate endDate,
                                                       Integer vacationDays) throws IllegalArgumentException {

        if (salary.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("Salary should be greater than 0");
        }
        if ((startDate == null || endDate == null) && (vacationDays == null || vacationDays <= 0)) {
            throw new IllegalArgumentException("Invalid parameters specified");
        }
        if (vacationDays != null) {
            return buildResult(vacationDays, salary);
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("StartDate must be earlier than EndDate");
        }
        return buildResult(countVacationDays(startDate, endDate), salary);
    }

    private CalculatedVacationResultDto buildResult(int vacationDays, BigDecimal vacationPaySum) {
        CalculatedVacationResultDto result = new CalculatedVacationResultDto();
        result.setVacationDays(vacationDays);
        result.setVacationPaySum(calculateTotalVacationSalary(vacationDays, vacationPaySum));
        return result;
    }

    private BigDecimal calculateTotalVacationSalary(int vacationDays, BigDecimal salary) {
        return salary
                .divide(BigDecimal.valueOf(365), 2, RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(vacationDays));
    }

    private int countVacationDays(LocalDate startDate, LocalDate endDate) {
        int days = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if (!holidayService.isHoliday(date)) {
                days++;
            }
            date = date.plusDays(1);
        }
        return days;
    }
}
