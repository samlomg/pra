package com.dglbc.note.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by LbcLT on 2017/5/16.
 */
@Entity
@Setter
@Getter
@ToString
public class SalesPlan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesPlanId;
    //    private String companyName; //公司名称
    private Contract contract;
    private BigDecimal accountReceivable; //应收款
    private BigDecimal collected;//已收款
    //    @DateTimeFormat(pattern="yyyy-MM-dd")
//    private Date documentTime;//单据时间
    private String collectionCycle;//周期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //生成时间
    private String createUser; //生成人
    private Integer flag; //状态

    public SalesPlan() {
    }
}
