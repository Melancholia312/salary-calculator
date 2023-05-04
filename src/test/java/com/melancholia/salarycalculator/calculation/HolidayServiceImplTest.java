package com.melancholia.salarycalculator.calculation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayServiceImplTest {

    private final HolidayService holidayService = new HolidayServiceImpl();

    @Test
    void isHoliday() {
        LocalDate localDate1 = LocalDate.of(2023, 5, 1);
        LocalDate localDate2 = LocalDate.of(2023, 5, 4);
        LocalDate localDate3 = LocalDate.of(2023, 5, 6);
        LocalDate localDate4 = LocalDate.of(2023, 5, 7);

        assertTrue(holidayService.isHoliday(localDate1));
        assertTrue(holidayService.isHoliday(localDate3));
        assertTrue(holidayService.isHoliday(localDate4));
        assertFalse(holidayService.isHoliday(localDate2));
    }
}