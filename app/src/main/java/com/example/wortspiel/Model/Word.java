package com.example.wortspiel.Model;


import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    private String germanWord;
    private String germanWordIdentity;
    private List<String> tagGermanWord;
    private String englishMeaning;
    private String englishMeaningIdentity;
    private List<String> tagEnglishMeaning;
    private Sound pronunciation;
    private String partsOfSpeech;
    private List<String> wordTag;
    private List<String> exampleSentence;
    private List<String> synonym;
    private List<String> acronym;

    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public Word() {
    }

    public String getGermanWord() {
        return germanWord;
    }

    public void setGermanWord(String germanWord) {
        this.germanWord = germanWord;
    }

    public List<String> getTagGermanWord() {
        return tagGermanWord;
    }

    public void setTagGermanWord(List<String> tagGermanWord) {
        this.tagGermanWord = tagGermanWord;
    }
    public void addTagGermanWord(String tagGermanWord) {
        this.tagGermanWord.add(tagGermanWord) ;
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

    public List<String> getTag() {
        return wordTag;
    }

    public void setTag(List<String> tag) {
        this.wordTag = tag;
    }
    public void addTag(String tag) {
        this.wordTag.add(tag);
    }

    public List<String> getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(List<String> exampleSentence) {
        this.exampleSentence = exampleSentence;
    }
    public void addExampleSentence(String exampleSentence) {
        this.exampleSentence.add(exampleSentence);
    }

    public List<String> getSynonym() {
        return synonym;
    }

    public void addSynonym(String synonym) {
        this.synonym.add(synonym);
    }

    public List<String> getAcronym() {
        return acronym;
    }

    public void setAcronym(List<String> acronym) {
        this.acronym = acronym;
    }
    public void addAcronym(String acronym) {
        this.acronym.add(acronym);
    }

    public String getGermanWordIdentity() {
        return germanWordIdentity;
    }

    public void setGermanWordIdentity(String germanWordIdentity) {
        this.germanWordIdentity = germanWordIdentity;
    }

    public String getEnglishMeaningIdentity() {
        return englishMeaningIdentity;
    }

    public void setEnglishMeaningIdentity(String englishMeaningIdentity) {
        this.englishMeaningIdentity = englishMeaningIdentity;
    }

    public List<String> getTagEnglishMeaning() {
        return tagEnglishMeaning;
    }

    public void setTagEnglishMeaning(List<String> tagEnglishMeaning) {
        this.tagEnglishMeaning = tagEnglishMeaning;
    }
    public void addTagEnglishMeaning(String tagEnglishMeaning) {
        this.tagEnglishMeaning.add(tagEnglishMeaning);
    }

    public List<String> getWordTag() {
        return wordTag;
    }

    public void setWordTag(List<String> wordTag) {
        this.wordTag = wordTag;
    }

    public void setSynonym(List<String> synonym) {
        this.synonym = synonym;
    }

    @Override
    public String toString() {
        return "Word{" +
                "germanWord='" + germanWord + '\'' +
                ", germanWordIdentity='" + germanWordIdentity + '\'' +
                ", tagGermanWord='" + tagGermanWord + '\'' +
                ", englishMeaning='" + englishMeaning + '\'' +
                ", englishMeaningIdentity='" + englishMeaningIdentity + '\'' +
                ", tagEnglishMeaning='" + tagEnglishMeaning + '\'' +
                ", pronunciation=" + pronunciation +
                ", partsOfSpeech='" + partsOfSpeech + '\'' +
                ", wordTag=" + wordTag +
                ", exampleSentence=" + exampleSentence +
                ", synonym=" + synonym +
                ", acronym=" + acronym +
                '}';
    }
}
