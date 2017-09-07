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
 */
@Entity
@Getter
@Setter
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userRoleId;
    @NotNull
    @NotBlank
    private String userRoleName;
    private Date lastUpdate;
    private Date createTime;
    @OneToMany
    private Set<User> users;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;


}
