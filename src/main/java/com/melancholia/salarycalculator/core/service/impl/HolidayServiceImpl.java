package com.melancholia.salarycalculator.core.service.impl;

import com.melancholia.salarycalculator.core.service.HolidayService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.LocalDate.now;
import static java.util.List.of;

@Service
public class HolidayServiceImpl implements HolidayService {

    private static final int currentYear = now().getYear();
    private static final List<LocalDate> holidays = of(
            LocalDate.of(currentYear, 1, 1),
            LocalDate.of(currentYear, 1, 2),
            LocalDate.of(currentYear, 1, 3),
            LocalDate.of(currentYear, 1, 4),
            LocalDate.of(currentYear, 1, 5),
            LocalDate.of(currentYear, 1, 6),
            LocalDate.of(currentYear, 1, 7),
            LocalDate.of(currentYear, 1, 8),
            LocalDate.of(currentYear, 2, 23),
            LocalDate.of(currentYear, 3, 8),
            LocalDate.of(currentYear, 5, 1),
            LocalDate.of(currentYear, 5, 9),
            LocalDate.of(currentYear, 6, 12),
            LocalDate.of(currentYear, 11, 4)
    );

    @Override
    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date)
                || date.getDayOfWeek() == SATURDAY
                ||  date.getDayOfWeek() == SUNDAY;
    }
}
