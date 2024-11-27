package com.kiran.ecommerce.order;

import com.kiran.ecommerce.customer.CustomerClient;
import com.kiran.ecommerce.exception.BusinessException;
import com.kiran.ecommerce.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createdOrder(OrderRequest request) {

        // check the customer --> openfeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create an order:: No Customer exists"));

        // purchase the products --> product-ms(RestTemplate)


        //persist order
        //persist orderlines
        // start payment process
        //send the order confirmation --> notification-ms(kafka)
        return null;


    }
}
