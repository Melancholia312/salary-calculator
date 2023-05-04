package com.melancholia.salarycalculator.calculation;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VacationCalculationResult {
    private long days;
    private BigDecimal total;
}
