package com.kiran.ecommerce.kafka.order;

import com.kiran.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

//Orderconfirmation
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
