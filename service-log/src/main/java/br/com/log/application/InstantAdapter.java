package br.com.log.application;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;

public class InstantAdapter implements JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonElement value, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Instant.ofEpochMilli(value.getAsLong());
    }
}
