package com.melancholia.salarycalculator.calculation;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculationService {
    VacationCalculationResult salaryCalculate(BigDecimal salary,
                                              LocalDate startDate,
                                              LocalDate endDate,
                                              Integer vacationDays);
}
