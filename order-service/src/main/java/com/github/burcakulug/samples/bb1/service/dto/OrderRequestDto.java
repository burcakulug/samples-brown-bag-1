package com.github.burcakulug.samples.bb1.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class OrderRequestDto {
    @NotNull
    @Size(min = 1)
    private Set<Long> itemIds;
}
