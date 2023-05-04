package com.melancholia.salarycalculator.calculation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceImplTest {

    private final CalculationServiceImpl calculationService = new CalculationServiceImpl(new HolidayServiceImpl());


    @Test
    void salaryCalculate() {

        int vacationDays1 = 99;
        BigDecimal salary1 = BigDecimal.valueOf(1000);

        BigDecimal salary2 = BigDecimal.valueOf(1000);
        LocalDate startDate1 = LocalDate.of(2023, 5,1);
        LocalDate endDate1 = LocalDate.of(2023, 5,31);

        BigDecimal salaryExc = BigDecimal.valueOf(1000);
        LocalDate startDateExc = LocalDate.of(2023, 5, 1);
        LocalDate endDateExc = LocalDate.of(2022, 5, 1);
        int vacationDaysExc = -1;

        assertEquals(
                calculationService.salaryCalculate(salary1, null, null, vacationDays1),
                     new VacationCalculationResult(99, BigDecimal.valueOf(271.26)));


        assertEquals(
                calculationService.salaryCalculate(salary2, startDate1, endDate1, null),
                    new VacationCalculationResult(21, BigDecimal.valueOf(57.54)));


        assertThrows(IllegalArgumentException.class, () -> calculationService.salaryCalculate(
                salaryExc, null, null, null));

        assertThrows(IllegalArgumentException.class, () -> calculationService.salaryCalculate(
                salaryExc, startDateExc, endDateExc, null));

        assertThrows(IllegalArgumentException.class, () -> calculationService.salaryCalculate(
                salaryExc, null, null, vacationDaysExc));

        assertThrows(IllegalArgumentException.class, () -> calculationService.salaryCalculate(
                salaryExc, startDateExc, null, null));
    }
}