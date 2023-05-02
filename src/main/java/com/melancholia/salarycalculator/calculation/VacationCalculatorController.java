package com.melancholia.salarycalculator.calculation;

import com.melancholia.salarycalculator.core.APICustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class VacationCalculatorController {

    private final CalculationService calculationService;

    public VacationCalculatorController(CalculationServiceImpl calculationServiceImpl) {
        this.calculationService = calculationServiceImpl;
    }

    @GetMapping("/calculate")
    public ResponseEntity<APICustomResponse> calculateVacation(
            @RequestParam("salary") BigDecimal salary,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "vacationDays", required = false) Integer vacationDays) {

        VacationCalculationResult vacationCalculationResult =
                calculationService.salaryCalculate(salary, startDate, endDate, vacationDays);

        return ResponseEntity.ok(
                APICustomResponse.builder()
                        .timeStamp(now())
                        .data(vacationCalculationResult)
                        .message("Calculated salary")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}



