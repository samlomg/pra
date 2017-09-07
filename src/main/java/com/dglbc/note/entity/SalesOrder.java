package com.dglbc.note.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
public class SalesOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesOrderdId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receiveTime; //收款时间
    private String receiveUser; //收款人
    private BigDecimal collected; //收款金额
    private Integer flag;
    @ManyToOne
    private SalesPlan salesPlan;

}
