package com.example.music.Model;

import java.util.List;

public class ChuDe_TheLoai_Model {
    boolean success;
    String message;
    ChuDe_TheLoai result;

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

    public ChuDe_TheLoai getResult() {
        return result;
    }

    public void setResult(ChuDe_TheLoai result) {
        this.result = result;
    }
}
