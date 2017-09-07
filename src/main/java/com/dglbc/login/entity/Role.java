package com.dglbc.login.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by LbcLT on 2016/12/24.
 * 角色表
 */
@Entity
@Getter
@Setter
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    @NotNull
    @NotBlank
    private String roleName;
    private Date lastUpdate;
    private Date createTime;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

}
