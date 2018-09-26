package com.health.common;

import javax.ws.rs.core.Response;


public interface ResponseAction<T, R> {
    R apply(T t) throws Exception;

    default Response successResponse(int statusCode, R result) {
        return Response.status(statusCode).entity(new ResponseData(result)).build();
    }

    default Response errorResponse(String message) {
        return Response.serverError(). entity(new ResponseData(true, message)).build();
    }

    default Response errorResponse(int statusCode, String message) {
        return Response.status(statusCode).entity(new ResponseData(true, message)).build();
    }

    default Response errorResponse(int statusCode, Throwable se) {
        return Response.status(statusCode).entity(new ResponseData(true, se)).build();
    }
}
