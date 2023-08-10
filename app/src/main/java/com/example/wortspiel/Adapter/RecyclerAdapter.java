package com.example.wortspiel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wortspiel.Model.Word;
import com.example.wortspiel.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Word> recArrayList = new ArrayList<>();
    //private Context context;
    //for setting up click listener in list item


    public RecyclerAdapter(ArrayList<Word> recArrayList) {
        this.recArrayList = recArrayList;
    }
    public RecyclerAdapter() {
    }

    public void addData(Word w){
        recArrayList.add(w);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_card,parent,false);

        return  new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int index = position;

        holder.tv_germanWord.setText(recArrayList.get(position).getGermanWord());
        holder.tv_EnglishWord.setText(recArrayList.get(position).getEnglishMeaning());
        //holder.tv_partsOfSpeech.setText(recArrayList.get(position).getPartsOfSpeech());
        //holder.tv_tag.setText(recArrayList.get(position).getTag().toString());


        //setting click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing the clicked position to the interface
            }
        });

    }

    @Override
    public int getItemCount() {
        return recArrayList.size();
    }

    //getting clicked data
    public Word getItemData(int position) {
        return recArrayList.get(position);
    }



    //updating the data of the recView array
    public void setAdapterData(ArrayList<Word> object) {
        this.recArrayList = object;
        notifyDataSetChanged();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_germanWord, tv_EnglishWord, tv_partsOfSpeech, tv_tag;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv_germanWord = itemView.findViewById(R.id.tv_germanWord_card);
            tv_EnglishWord = itemView.findViewById(R.id.tv_englishWord_card);
            tv_partsOfSpeech = itemView.findViewById(R.id.tv_partsOfSpeech_card);
            tv_tag = itemView.findViewById(R.id.tv_tag_card);
        }
    }
}