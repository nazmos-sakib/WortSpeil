package com.example.wortspiel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.wortspiel.Model.DataHolder;
import com.example.wortspiel.Model.ReadData;
import com.example.wortspiel.Model.Word;
import com.example.wortspiel.Model.WriteData;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;


public class FirstHit extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_first_hit);

        //String fileName = "multimap_data.json";
        String fileName = "wordList.ser";

        File file = new File(getApplicationContext().getFilesDir(), fileName);

        if (file.exists()) {
            // The file already exists in internal storage
            // You can perform actions accordingly

            new ReadData(this,
                    wordList -> {
                Intent intent = new Intent(FirstHit.this,MainActivity.class);
                DataHolder.setWordList(wordList);
                startActivity(intent);
                finish();
            });
            System.out.println("--------------i am from readData------------");
            //deletePrivateFileData(file);

        } else {
            // The file doesn't exist in internal storage
            // You can create and write to the file here if needed
            new WriteData(this,
                    wordList -> {
                        Intent intent = new Intent(FirstHit.this,MainActivity.class);
                        DataHolder.setWordList(wordList);
                        startActivity(intent);
                        finish();
                    });
            System.out.println("------------i am from write data------------");
        }


    }

    public void deletePrivateFileData(File fileToDelete){
        boolean isDeleted = fileToDelete.delete();

        if (isDeleted) {
            // File deleted successfully
            System.out.println("delete successful");
        } else {
            // Unable to delete the file
            System.out.println("delete not successful");
        }
    }
}