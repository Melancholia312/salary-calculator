package com.melancholia.salarycalculator.calculation;

import java.time.LocalDate;

interface HolidayService {
    boolean isHoliday(LocalDate date);
}