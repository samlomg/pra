package com.dglbc.worklists.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by LbcLT on 2017/2/16.
 */

@Entity
@Setter
@Getter
@ToString
public class ErrorInfo  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Sequence")
    private	Integer	sequence	;//自动生成
    private	Long	customerUserId	;//customerId
    private	String	mode	;//模式
    private	Integer	errorCode	;//错误代码
    private	String	errorContent	;//错误内容


    @ManyToOne
    private MissionRecorder missionRecorder;

    public ErrorInfo(Long customerUserId, String mode, Integer errorCode, String errorContent, MissionRecorder missionRecorder) {
        this.customerUserId = customerUserId;
        this.mode = mode;
        this.errorCode = errorCode;
        this.errorContent = errorContent;
        this.missionRecorder = missionRecorder;
    }

    public ErrorInfo() {
    }
}
