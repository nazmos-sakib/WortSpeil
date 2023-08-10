package com.example.wortspiel.Model;

import java.util.Map;

public class RandomWordDataHolder {
    private static Map<Integer,Word> words;

    public static Map<Integer,Word>  getWordList() {
        return words;
    }

    public static void setWordList(Map<Integer,Word> words) {
        RandomWordDataHolder.words = words;
    }
}
