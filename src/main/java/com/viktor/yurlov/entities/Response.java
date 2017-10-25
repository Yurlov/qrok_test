package com.viktor.yurlov.entities;

public class Response <T>{
    String massage;
    T body;
    public Response() {
    }

    public Response(String massage) {
        this.massage = massage;
    }

    public Response(T body) {
        this.body = body;
    }

    public Response(String massage, T body) {
        this.massage = massage;
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
