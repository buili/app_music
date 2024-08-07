package com.example.music.Model;

import java.util.List;

public class TheLoaiModel {
    boolean success;
    String message;
    List<TheLoai> result;

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

    public List<TheLoai> getResult() {
        return result;
    }

    public void setResult(List<TheLoai> result) {
        this.result = result;
    }
}
