package com.example.music.Model;

import java.util.List;

public class ChuDeModel {
    boolean success;
    String message;
    List<ChuDe> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChuDe> getResult() {
        return result;
    }

    public void setResult(List<ChuDe> result) {
        this.result = result;
    }
}
