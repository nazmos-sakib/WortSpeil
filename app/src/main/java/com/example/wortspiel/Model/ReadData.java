package com.example.wortspiel.Model;

import android.content.Context;

import com.example.wortspiel.MainActivityCallBack;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ReadData {
    public ReadData(Context context, MainActivityCallBack callBack) {
        try {

            String filename = "wordList.ser";
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Map.Entry<String, Word>> wordEntries = (List<Map.Entry<String, Word>>) in.readObject();
            in.close();
            fileIn.close();

            // Convert the list back to Multimap
            Multimap<String, Word> wordList = ArrayListMultimap.create();
            for (Map.Entry<String, Word> entry : wordEntries) {
                wordList.put(entry.getKey(), entry.getValue());
            }

            System.out.println("after reading size is ------------" + wordList.size());
            callBack.startMainActivity(wordList);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
