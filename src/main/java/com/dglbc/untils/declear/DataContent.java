package com.dglbc.untils.declear;

/**
 * Created by LbcLT on 2017/6/17.
 */
public class DataContent<T> {
    private T content;

    public DataContent() {
    }

    public DataContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
