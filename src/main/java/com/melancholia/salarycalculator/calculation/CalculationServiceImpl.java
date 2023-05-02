package com.melancholia.salarycalculator.calculation;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CalculationServiceImpl implements CalculationService {

    private final HolidayService holidayService;

    public CalculationServiceImpl(HolidayService holidayService){
        this.holidayService = holidayService;
    }

    public VacationCalculationResult salaryCalculate(BigDecimal salary,
                                                     LocalDate startDate,
                                                     LocalDate endDate,
                                                     Integer vacationDays){

        if (salary.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Salary should be greater than 0");

        if (startDate != null && endDate != null) {

            if (startDate.isAfter(endDate)) throw new IllegalArgumentException("StartDate must be earlier than EndDate");

            int days = countVacationDays(startDate, endDate);
            return new VacationCalculationResult(
                    days,
                    calculateTotalVacationSalary(days, salary)
            );

        } else if (vacationDays != null && vacationDays > 0) {
            return new VacationCalculationResult(
                    vacationDays,
                    calculateTotalVacationSalary(vacationDays, salary)
            );

        } else {
            throw new IllegalArgumentException("Invalid parameters specified");
        }
    }

    private BigDecimal calculateTotalVacationSalary(int vacationDays, BigDecimal salary){
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
