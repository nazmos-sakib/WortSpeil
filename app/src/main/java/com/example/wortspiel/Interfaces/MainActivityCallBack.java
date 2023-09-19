package com.example.wortspiel.Interfaces;

import com.example.wortspiel.Model.Word;
import com.google.common.collect.Multimap;

public interface MainActivityCallBack {
    void startMainActivity(Multimap<String, Word> wordList);
}
