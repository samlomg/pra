package com.dglbc.websocket.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by gdlbc on 2017/6/30.
 */
@Setter
@Getter
@ToString
public class Greeting {
    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }


}
