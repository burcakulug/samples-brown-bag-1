package com.github.burcakulug.samples.bb1.web;

import com.github.burcakulug.samples.bb1.service.dto.OrderHeaderDto;
import com.github.burcakulug.samples.bb1.service.dto.OrderRequestDto;
import com.github.burcakulug.samples.bb1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<OrderHeaderDto> getOrderReport(){
        return orderService.getOrderReport();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public OrderHeaderDto placeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto){
        return orderService.placeOrder(orderRequestDto);
    }
}
