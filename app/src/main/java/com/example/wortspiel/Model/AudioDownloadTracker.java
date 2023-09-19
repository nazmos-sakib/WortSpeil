package com.example.wortspiel.Model;

public class AudioDownloadTracker {
    private static int value;

    public static int getValue() {
        return value;
    }

    public static void increase() {
        value ++;
    }

    public static void reset() {
        value  = 0;
    }


}
