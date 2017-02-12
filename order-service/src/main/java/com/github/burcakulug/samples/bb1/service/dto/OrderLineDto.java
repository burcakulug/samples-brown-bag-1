package com.github.burcakulug.samples.bb1.service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class OrderLineDto {
    @NotEmpty
    private Long id;

    @NotNull
    private Long itemId;
}
