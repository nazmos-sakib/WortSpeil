package com.example.wortspiel.Model;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WordDeserializer implements JsonDeserializer<Word> {
    @Override
    public Word deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Word word = new Word();
        word.setGermanWord(jsonObject.get("germanWord").getAsString());
        word.setGermanWordIdentity(jsonObject.get("germanWordIdentity").getAsString());
        word.setEnglishMeaning(jsonObject.get("englishMeaning").getAsString());
        word.setEnglishMeaningIdentity(jsonObject.get("englishMeaningIdentity").getAsString());
        word.setPartsOfSpeech(jsonObject.get("partsOfSpeech").getAsString());
        word.setAll(jsonObject.get("all").getAsString());

        JsonArray tagGermanWordArray = jsonObject.getAsJsonArray("tagGermanWord");
        List<String> tagGermanWordList = new ArrayList<>();
        for (JsonElement tagElement : tagGermanWordArray) {
            tagGermanWordList.add(tagElement.getAsString());
        }
        word.setTagGermanWord(tagGermanWordList);

        JsonArray tagEnglishMeaningArray = jsonObject.getAsJsonArray("tagEnglishMeaning");
        List<String> tagEnglishMeaningList = new ArrayList<>();
        for (JsonElement tagElement : tagEnglishMeaningArray) {
            tagEnglishMeaningList.add(tagElement.getAsString());
        }
        word.setTagEnglishMeaning(tagEnglishMeaningList);

        JsonArray wordTagArray = jsonObject.getAsJsonArray("wordTag");
        List<String> wordTagList = new ArrayList<>();
        for (JsonElement tagElement : wordTagArray) {
            wordTagList.add(tagElement.getAsString());
        }
        word.setWordTag(wordTagList);

        // Similar deserialization logic for other lists

        return word;
    }
}
