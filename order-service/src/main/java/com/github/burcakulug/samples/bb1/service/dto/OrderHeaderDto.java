package com.github.burcakulug.samples.bb1.service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class OrderHeaderDto {

    @NotEmpty
    private Long id;

    @NotNull
    private Date orderDate;

    @NotEmpty
    @NotNull
    @Size(min = 1)
    private List<OrderLineDto> orderLines;
}
