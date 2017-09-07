package com.dglbc.worklists.entity;

import com.dglbc.login.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by LbcLT on 2017/2/15.
 */
@Entity
@Setter
@Getter
@ToString
public class MissionRecorder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Sequence")
    private	Integer	sequence	;//自动生成
    private	Integer	progress;//进度
    private Date createDate	;//生成时间
    private Date endDate	;//完成时间
    private User users	;//触发人
    private String workTitle; //任务标题
    private Integer workType; //workType

    public MissionRecorder(Integer progress, Date createDate, Date endDate, User users, String workTitle, Integer workType) {
        this.progress = progress;
        this.createDate = createDate;
        this.endDate = endDate;
        this.users = users;
        this.workTitle = workTitle;
        this.workType = workType;
    }

    public MissionRecorder(Integer progress, Date createDate, User users, String workTitle, Integer workType) {
        this.progress = progress;
        this.createDate = createDate;
        this.users = users;
        this.workTitle = workTitle;
        this.workType = workType;
    }

    public MissionRecorder() {
    }
}
