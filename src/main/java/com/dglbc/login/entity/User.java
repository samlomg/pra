package com.dglbc.login.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by LbcLT on 2016/12/24.
 * 用户表
 */

@Entity
@Setter
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "Name is required.")
    private String Name;
    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Email is required.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Password is required.")
    private String password;

    // 用户使用状态
    private Date lastLogin;
    private String lastLoginIP;
    private Date lastUpdate;
    private Date createTime;
    @NotNull
    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean isAccountNonExpired;
    @NotNull
    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean isAccountNonLocked;
    @NotNull
    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean isCredentialsNonExpired;
    @NotNull
    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean isEnabled;
    private String remark;
    private int type;
    //对应关系
    @ManyToOne
    private UserRole userRole;

    public User(String name, String email, String password, Date lastLogin, String lastLoginIP, Date lastUpdate, Date createTime, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String remark, UserRole userRole) {
        Name = name;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
        this.lastLoginIP = lastLoginIP;
        this.lastUpdate = lastUpdate;
        this.createTime = createTime;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.remark = remark;
        this.userRole = userRole;
    }

    public User(String name, String email, String password, Date lastLogin, String lastLoginIP, Date lastUpdate,
                Date createTime, String remark, UserRole userRole) {
        Name = name;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
        this.lastLoginIP = lastLoginIP;
        this.lastUpdate = lastUpdate;
        this.createTime = createTime;
        this.remark = remark;
        this.userRole = userRole;
    }


    public User() {
    }
}
