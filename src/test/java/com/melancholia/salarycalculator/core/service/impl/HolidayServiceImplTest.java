package com.melancholia.salarycalculator.core.service.impl;

import com.melancholia.salarycalculator.core.service.HolidayService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.stream.Stream;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringJUnitConfig(HolidayServiceImpl.class)
class HolidayServiceImplTest {

    @Autowired
    private HolidayService holidayService;

    @ParameterizedTest
    @MethodSource("getArgsForIsHoliday")
    void isHoliday(LocalDate date, boolean isHoliday) {
        assertEquals(isHoliday, holidayService.isHoliday(date));
    }

    private static Stream<Arguments> getArgsForIsHoliday() {
        return Stream.of(
                arguments(of(2025, 5, 1), true),
                arguments(of(2025, 5, 4), true),
                arguments(of(2025, 5, 6), false),
                arguments(of(2025, 5, 7), false)
        );
    }
}