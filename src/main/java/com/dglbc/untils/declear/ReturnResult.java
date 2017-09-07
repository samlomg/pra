package com.dglbc.untils.declear;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by LbcLT on 2017/2/18.
 */
@Setter
@Getter
public class ReturnResult<T,S> {
    private T status;
    private S content;

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    public S getContent() {
        return content;
    }

    public void setContent(S content) {
        this.content = content;
    }

    public ReturnResult(T status, S content) {
        this.status = status;
        this.content = content;
    }

    public ReturnResult(T status) {
        this.status = status;
    }

    public ReturnResult() {
    }

}
