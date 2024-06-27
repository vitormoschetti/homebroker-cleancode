package br.com.log.application.event;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDAdapter implements JsonDeserializer<UUID> {
    @Override
    public UUID deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return UUID.fromString(jsonElement.getAsString());
    }
}
