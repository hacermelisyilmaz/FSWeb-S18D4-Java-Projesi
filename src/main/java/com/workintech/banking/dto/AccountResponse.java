package com.workintech.banking.dto;

public record AccountResponse(Long id, String accountName, double moneyAmount, CustomerResponse customerResponse) {
}
