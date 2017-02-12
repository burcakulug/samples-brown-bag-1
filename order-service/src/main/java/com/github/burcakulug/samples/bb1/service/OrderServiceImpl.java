package com.github.burcakulug.samples.bb1.service;

import com.github.burcakulug.samples.bb1.domain.OrderHeaderRepository;
import com.github.burcakulug.samples.bb1.domain.OrderLine;
import com.github.burcakulug.samples.bb1.domain.OrderLineRepository;
import com.github.burcakulug.samples.bb1.service.dto.OrderHeaderDto;
import com.github.burcakulug.samples.bb1.service.dto.OrderRequestDto;
import com.github.burcakulug.samples.bb1.domain.OrderHeader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderHeaderDto> getOrderReport(){
        final List<OrderHeader> orderHeaders = orderHeaderRepository.findAll();
        final List<OrderHeaderDto> orderHeaderDtos = orderHeaders.stream()
                .map(orderHeader -> modelMapper.map(orderHeader, OrderHeaderDto.class))
                .collect(toList());
        return orderHeaderDtos;
    }

    @Override
    public OrderHeaderDto placeOrder(OrderRequestDto orderRequestDto) {
        final OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderDate(new Date());
        orderHeaderRepository.save(orderHeader);
        final List<OrderLine> orderLines = orderRequestDto.getItemIds().stream()
                .map(itemId -> OrderLine.builder()
                        .itemId(itemId)
                        .orderHeader(orderHeader)
                        .build())
                .collect(toList());
        orderLineRepository.save(orderLines);
        orderHeader.setOrderLines(orderLines);
        final OrderHeaderDto orderHeaderDto = modelMapper.map(orderHeader, OrderHeaderDto.class);
        return orderHeaderDto;
    }
}
