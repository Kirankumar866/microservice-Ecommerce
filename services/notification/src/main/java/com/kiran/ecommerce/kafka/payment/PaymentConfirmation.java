package com.kiran.ecommerce.kafka.payment;

import com.kiran.ecommerce.kafka.order.Customer;

import java.math.BigDecimal;

public record PaymentConfirmation(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String orderReference,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
