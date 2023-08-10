package com.example.wortspiel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.wortspiel.Adapter.RecyclerAdapter;
import com.example.wortspiel.Model.Word;
import com.example.wortspiel.databinding.ActivityWordDetailsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class WordDetails extends AppCompatActivity {

    private RecyclerAdapter recAdapter;

    ActivityWordDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_word_details);
        //for setting app to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding = ActivityWordDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        HashMap<String, Word> words = (HashMap<String,Word>) intent.getSerializableExtra("words");

        setRecViewAdapter(new ArrayList<>(words.values()));

    }
    private void setRecViewAdapter(ArrayList<Word> w) {
        recAdapter = new RecyclerAdapter();
        recAdapter.setAdapterData(w);
        binding.recViewWords.setAdapter(recAdapter);
        binding.recViewWords.setLayoutManager(new LinearLayoutManager(this));
    }
    // In your details activity
    @Override
    public void onBackPressed() {
        finish(); // Finish the details activity
    }
}