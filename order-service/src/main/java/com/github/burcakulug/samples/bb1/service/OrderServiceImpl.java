package com.github.burcakulug.samples.bb1.service;

import com.github.burcakulug.samples.bb1.domain.OrderHeader;
import com.github.burcakulug.samples.bb1.domain.OrderHeaderRepository;
import com.github.burcakulug.samples.bb1.domain.OrderLine;
import com.github.burcakulug.samples.bb1.domain.OrderLineRepository;
import com.github.burcakulug.samples.bb1.service.dto.ItemDto;
import com.github.burcakulug.samples.bb1.service.dto.OrderHeaderDto;
import com.github.burcakulug.samples.bb1.service.dto.OrderRequestDto;
import com.github.burcakulug.samples.bb1.service.exception.InvalidItemIdException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

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

    @Autowired
    private RestOperations restTemplate;

    @Value("${item-service}")
    private String itemServiceEndpoint;

    @Override
    public List<OrderHeaderDto> getOrderReport() {
        final List<OrderHeader> orderHeaders = orderHeaderRepository.findAll();
        final List<OrderHeaderDto> orderHeaderDtos = orderHeaders.stream()
                .map(orderHeader -> modelMapper.map(orderHeader, OrderHeaderDto.class))
                .collect(toList());
        return orderHeaderDtos;
    }

    @Override
    public OrderHeaderDto placeOrder(OrderRequestDto orderRequestDto) {
        try {
            orderRequestDto.getItemIds().stream()
                    .map(itemId -> itemServiceEndpoint + "/items/" + itemId)
                    .map(itemUri -> restTemplate.getForObject(itemUri, ItemDto.class))
                    .map(ItemDto::getId)
                    .forEach(Assert::notNull);
        } catch (RestClientException | IllegalArgumentException e) {
            throw new InvalidItemIdException("Item IDs cannot be validated");
        }
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
