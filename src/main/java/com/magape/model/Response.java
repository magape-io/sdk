package com.magape.model;

/**
 * Interface returns results
 */
public class Response<T> {

    /**
     * Status code
     */
    private int code;

    /**
     * message
     */
    private String message;

    /**
     * data
     */
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static Response builder() {
        return new Response();
    }

    public Response<T> code(int code) {
        this.code = code;
        return this;
    }

    public Response<T> message(String message) {
        this.message = message;
        return this;
    }

    public Response<T> data(T data) {
        this.data = data;
        return this;
    }

    public static <T> Response<T> success(T data) {
        return (Response<T>) Response.builder()
                .code(200)
                .message("success")
                .data(data);
    }

    public static Response fail(String msg) {
        return Response.builder()
                .code(400)
                .message(msg);
    }

    public static <T> Response<T> fail(int errCode,T data) {
        return (Response<T>) Response.builder()
                .code(errCode)
                .data(data);
    }


    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response() {
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
