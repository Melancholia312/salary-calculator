package com.melancholia.salarycalculator.core.service;

import java.time.LocalDate;

public interface HolidayService {

    boolean isHoliday(LocalDate date);

}