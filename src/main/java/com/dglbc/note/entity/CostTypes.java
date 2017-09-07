package com.dglbc.note.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class CostTypes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer costTypesId;
    private String costTypesName;
    private BigDecimal price;
    private Integer flag;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Contract> contracts;
}
