package com.github.burcakulug.samples.bb1.service;

import com.github.burcakulug.samples.bb1.service.dto.OrderHeaderDto;
import com.github.burcakulug.samples.bb1.service.dto.OrderRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional(readOnly = true)
    List<OrderHeaderDto> getOrderReport();

    @Transactional
    OrderHeaderDto placeOrder(OrderRequestDto orderRequestDto);
}
