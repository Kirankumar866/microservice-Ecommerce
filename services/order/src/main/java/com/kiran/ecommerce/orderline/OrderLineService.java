package com.kiran.ecommerce.orderline;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepo;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderLineMapper.toOrderLine(request);
        return orderLineRepo.save(order).getId();
    }
}
