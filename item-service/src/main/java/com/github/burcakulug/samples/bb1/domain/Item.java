package com.github.burcakulug.samples.bb1.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String name;
    private String description;
}
