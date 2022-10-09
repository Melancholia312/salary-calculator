package com.example.salarycalculator.model;

public class ApiResult {

    private String result;
    private String error;

    private ApiResult() {
    }

    static ApiResult withResult(String resultMessage) {
        ApiResult apiResult = new ApiResult();
        apiResult.result = resultMessage;
        return apiResult;
    }

    static ApiResult withError(String errorMessage) {
        ApiResult apiResult = new ApiResult();
        apiResult.error = errorMessage;
        return apiResult;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiResult apiResult = (ApiResult) o;

        if (result != null ? !result.equals(apiResult.result) : apiResult.result != null) return false;
        return error != null ? error.equals(apiResult.error) : apiResult.error == null;
    }

    @Override
    public int hashCode() {
        int result1 = result != null ? result.hashCode() : 0;
        result1 = 31 * result1 + (error != null ? error.hashCode() : 0);
        return result1;
    }
}