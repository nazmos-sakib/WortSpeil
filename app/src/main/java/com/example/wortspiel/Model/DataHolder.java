package com.example.wortspiel.Model;


import com.google.common.collect.Multimap;

public class DataHolder {
    private static Multimap<String, Word> wordList;

    public static Multimap<String, Word> getWordList() {
        return wordList;
    }

    public static void setWordList(Multimap<String, Word> wordList) {
        DataHolder.wordList = wordList;
    }
}