package com.kiran.ecommerce.kafka;

import com.kiran.ecommerce.customer.CustomerResponse;
import com.kiran.ecommerce.order.PaymentMethod;
import com.kiran.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {



}
