package com.epam.web.util;

import javax.ws.rs.core.Response.Status;

public class WSException extends RuntimeException {

    private String message;
    private Status status;

    public WSException() {
    }

    public WSException(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }


}
