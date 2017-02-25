package com.github.burcakulug.samples.bb1.config;

import com.github.burcakulug.samples.bb1.service.OrderService;
import com.github.burcakulug.samples.bb1.service.dto.OrderRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Configuration
@Profile("test-data")
public class TestDataConfig {

    @Autowired
    private OrderService orderService;

    @PostConstruct
    public void initData() {
        final OrderRequestDto order1 = OrderRequestDto.builder().itemIds(Stream.of(1L, 2L).collect(toSet())).build();
        final OrderRequestDto order2 = OrderRequestDto.builder().itemIds(Stream.of(2L, 3L).collect(toSet())).build();
        final OrderRequestDto order3 = OrderRequestDto.builder().itemIds(Stream.of(1L, 2L, 3L).collect(toSet())).build();
        orderService.placeOrder(order1);
        orderService.placeOrder(order2);
        orderService.placeOrder(order3);
    }
}
