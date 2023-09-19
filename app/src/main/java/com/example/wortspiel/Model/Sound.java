package com.example.wortspiel.Model;

public class Sound {
    String url;
    String fileName;

    public Sound() {
    }

    public Sound(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
