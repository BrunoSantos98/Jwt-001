package com.MyBlog.Security.services.handlerExceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class StandardError implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError() {
    }

    public StandardError(Instant timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
