package com.kiran.ecommerce.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name="product-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    Integer requestOrderPayement(@RequestBody PaymentRequest request);

}
