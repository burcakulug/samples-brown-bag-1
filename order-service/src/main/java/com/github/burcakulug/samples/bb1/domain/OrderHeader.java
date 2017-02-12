package com.github.burcakulug.samples.bb1.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class OrderHeader {

    @Id
    @GeneratedValue
    private Long id;

    private Date orderDate;

    @OneToMany(mappedBy = "orderHeader")
    private List<OrderLine> orderLines;
}
