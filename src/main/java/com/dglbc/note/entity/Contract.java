package com.dglbc.note.entity;

import com.dglbc.login.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;
    private Date begDate;
    private Date endDate;
    private Integer flag;

    @ManyToOne
    @JoinColumn(name = "contract_company_fk")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "contract_user_fk")
    private User signUser;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CostTypes> costTypes;

    public Contract() {
    }

    public Contract(Date begDate, Date endDate, Integer flag, Company company, User signUser, Set<CostTypes> costTypes) {
        this.begDate = begDate;
        this.endDate = endDate;
        this.flag = flag;
        this.company = company;
        this.signUser = signUser;
        this.costTypes = costTypes;
    }
}
