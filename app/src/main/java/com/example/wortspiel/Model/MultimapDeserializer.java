package com.example.wortspiel.Model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.*;
import java.lang.reflect.Type;

public class MultimapDeserializer implements JsonDeserializer<Multimap<String, Word>> {
    @Override
    public Multimap<String, Word> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Multimap<String, Word> multimap = ArrayListMultimap.create();

        JsonObject jsonObject = json.getAsJsonObject();
        for (String key : jsonObject.keySet()) {
            JsonArray jsonArray = jsonObject.getAsJsonArray(key);
            for (JsonElement element : jsonArray) {
                Word word = context.deserialize(element, Word.class);
                multimap.put(key, word);
            }
        }

        return multimap;
    }
}

