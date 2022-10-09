package com.example.salarycalculator.model;

import java.math.BigDecimal;

public class ResultBuilder {

    private ResultBuilder() {
    }


    public static ApiResult getResult( Object result ) {
        return ApiResult.withResult(result.toString());
    }

    public static ApiResult getResult( double result ) {
        return ApiResult.withResult(Double.toString(result));
    }

    public static ApiResult getResultFromError(String errorMessage) {
        return ApiResult.withError(errorMessage);
    }
}