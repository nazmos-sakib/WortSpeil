package com.example.wortspiel.Model;

import java.util.ArrayList;

public class Wort {
    String germanWord;
    String englishMeaning;
    Sound pronunciation;
    String partsOfSpeech;
    String example;
    ArrayList<String> exampleSentence;
    ArrayList<String> synonym;
    ArrayList<String> acronym;

    public Wort(String germanWord, String englishMeaning) {
        this.germanWord = germanWord;
        this.englishMeaning = englishMeaning;
    }

    public String getGermanWord() {
        return germanWord;
    }

    public void setGermanWord(String germanWord) {
        this.germanWord = germanWord;
    }

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

    public Sound getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(Sound pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getPartsOfSpeech() {
        return partsOfSpeech;
    }

    public void setPartsOfSpeech(String partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public ArrayList<String> getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(ArrayList<String> exampleSentence) {
        this.exampleSentence = exampleSentence;
    }
    public void addExampleSentence(String exampleSentence) {
        this.exampleSentence.add(exampleSentence);
    }

    public ArrayList<String> getSynonym() {
        return synonym;
    }

    public void setSynonym(ArrayList<String> synonym) {
        this.synonym = synonym;
    }
    public void addSynonym(String synonym) {
        this.synonym.add(synonym);
    }

    public ArrayList<String> getAcronym() {
        return acronym;
    }

    public void setAcronym(ArrayList<String> acronym) {
        this.acronym = acronym;
    }
    public void addAcronym(String acronym) {
        this.acronym.add(acronym);
    }
}
