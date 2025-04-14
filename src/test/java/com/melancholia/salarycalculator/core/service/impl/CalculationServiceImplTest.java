package com.melancholia.salarycalculator.core.service.impl;

import com.melancholia.salarycalculator.core.controller.model.CalculatedVacationResultDto;
import com.melancholia.salarycalculator.core.service.CalculationService;
import com.melancholia.salarycalculator.core.service.HolidayService;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = CalculationServiceImpl.class)
class CalculationServiceImplTest {

    @Autowired
    private CalculationService calculationService;
    @MockBean
    private HolidayService holidayService;

    @ParameterizedTest
    @MethodSource("getArgsForSalaryCalculate")
    @SneakyThrows
    void salaryCalculate(BigDecimal salary, LocalDate startDate, LocalDate endDate, Integer vacationDays, CalculatedVacationResultDto expected) {
        //Given
        when(holidayService.isHoliday(startDate)).thenReturn(true);
        //When-Then
        var actual = assertDoesNotThrow(() -> calculationService.salaryCalculate(salary, startDate, endDate, vacationDays));
        assertNotNull(actual);
        assertNotNull(actual.getVacationDays());
        assertNotNull(actual.getVacationPaySum());
        assertEquals(expected.getVacationDays(), actual.getVacationDays());
        assertTrue(actual.getVacationPaySum().compareTo(expected.getVacationPaySum()) == 0);
    }

    @ParameterizedTest
    @MethodSource("getArgsForSalaryCalculateThrowsException")
    @SneakyThrows
    void salaryCalculateThrowsException(BigDecimal salary, LocalDate startDate, LocalDate endDate, Integer vacationDays) {
        //Given-When
        assertThrows(IllegalArgumentException.class, () -> calculationService.salaryCalculate(salary, startDate, endDate, vacationDays));
        //Then
    }

    private static Stream<Arguments> getArgsForSalaryCalculate() {
        BigDecimal salary = valueOf(1000);
        var firstResult = buildResult(99, BigDecimal.valueOf(271.26));
        var secondResult = buildResult(30, BigDecimal.valueOf(82.20));
        return Stream.of(
                arguments(salary, null, null, 99, firstResult),
                arguments(salary, of(2023, 5, 1), of(2023, 5, 31), null, secondResult)
        );
    }

    private static CalculatedVacationResultDto buildResult(int vacationDays, BigDecimal vacationPaySum) {
        CalculatedVacationResultDto result = new CalculatedVacationResultDto();
        result.setVacationDays(vacationDays);
        result.setVacationPaySum(vacationPaySum);
        return result;
    }

    private static Stream<Arguments> getArgsForSalaryCalculateThrowsException() {
        BigDecimal salary = valueOf(1000);
        LocalDate startDateExc = of(2023, 5, 1);
        LocalDate endDateExc = of(2022, 5, 1);
        return Stream.of(
                arguments(salary, null, null, null),
                arguments(salary, startDateExc, endDateExc, null),
                arguments(salary, null, null, -1),
                arguments(salary, startDateExc, null, null)
        );
    }

}