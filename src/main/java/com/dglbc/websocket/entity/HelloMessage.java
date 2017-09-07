package com.dglbc.websocket.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gdlbc on 2017/6/30.
 */
@Setter
@Getter
public class HelloMessage {
    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }


}
