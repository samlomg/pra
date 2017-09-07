package com.dglbc.note.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Setter
@Getter
@ToString
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    private String companyName;
    private Integer flag;

    public Company() {
    }

    public Company(String companyName, Integer flag) {
        this.companyName = companyName;
        this.flag = flag;
    }
}
