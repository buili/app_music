package com.example.music.Model;

import java.util.List;

public class BaiHatModel {
    boolean success;
    String message;
    List<BaiHat> result;

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

    public List<BaiHat> getResult() {
        return result;
    }

    public void setResult(List<BaiHat> result) {
        this.result = result;
    }
}
