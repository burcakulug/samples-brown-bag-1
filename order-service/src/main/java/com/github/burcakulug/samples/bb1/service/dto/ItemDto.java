package com.github.burcakulug.samples.bb1.service.dto;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class ItemDto {
    private Long id;
    @NotEmpty
    private String name;
    private String description;
}
