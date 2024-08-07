package com.example.music.Model;

import java.util.List;

public class PlaylistModel {
    boolean success;
    String message;
    List<Playlist> result;

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

    public List<Playlist> getResult() {
        return result;
    }

    public void setResult(List<Playlist> result) {
        this.result = result;
    }
}
