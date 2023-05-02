package com.melancholia.salarycalculator.calculation;

import java.math.BigDecimal;

public record VacationCalculationResult(
        long days,
        BigDecimal total
) {}