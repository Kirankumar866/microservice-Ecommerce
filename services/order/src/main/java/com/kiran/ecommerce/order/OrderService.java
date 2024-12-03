package com.kiran.ecommerce.order;

import com.kiran.ecommerce.customer.CustomerClient;
import com.kiran.ecommerce.exception.BusinessException;
import com.kiran.ecommerce.kafka.OrderConfirmation;
import com.kiran.ecommerce.kafka.OrderProducer;
import com.kiran.ecommerce.orderline.OrderLineRequest;
import com.kiran.ecommerce.orderline.OrderLineService;
import com.kiran.ecommerce.product.ProductClient;
import com.kiran.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createdOrder(OrderRequest request) {

        // check the customer --> openfeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create an order:: No Customer exists"));

        // purchase the products --> product-ms(RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

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
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )


        );



        return order.getId();


    }

    public List<OrderResponse> findAll() {
        return orderRepo.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepo.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No Order found with provided Id: %d", orderId)));
    }
}
