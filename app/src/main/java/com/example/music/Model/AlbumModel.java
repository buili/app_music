package com.example.music.Model;

import java.util.List;

public class AlbumModel {
    boolean success;
    String message;
    List<Album> result;

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

    public List<Album> getResult() {
        return result;
    }

    public void setResult(List<Album> result) {
        this.result = result;
    }
}
