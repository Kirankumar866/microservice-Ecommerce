package com.kiran.ecommerce.order;

import com.kiran.ecommerce.customer.CustomerClient;
import com.kiran.ecommerce.exception.BusinessException;
import com.kiran.ecommerce.orderline.OrderLineRequest;
import com.kiran.ecommerce.orderline.OrderLineService;
import com.kiran.ecommerce.product.ProductClient;
import com.kiran.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createdOrder(OrderRequest request) {

        // check the customer --> openfeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create an order:: No Customer exists"));

        // purchase the products --> product-ms(RestTemplate)
        this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.orderRepo.save(mapper.toOrder(request));
        //persist orderlines
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }


        // TO DO start payment process
        //send the order confirmation --> notification-ms(kafka)
        return null;


    }
}
