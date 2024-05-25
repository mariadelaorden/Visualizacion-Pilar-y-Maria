package es.uah.matcomp.proyecto.serializacion;

import com.google.gson.*;
import javafx.beans.property.*;

import java.lang.reflect.Type;

public class PropertyAdapters {

    public static class IntegerPropertyAdapter implements JsonSerializer<IntegerProperty>, JsonDeserializer<IntegerProperty> {
        @Override
        public JsonElement serialize(IntegerProperty src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.get());
        }

        @Override
        public IntegerProperty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new SimpleIntegerProperty(json.getAsInt());
        }
    }

    public static class DoublePropertyAdapter implements JsonSerializer<DoubleProperty>, JsonDeserializer<DoubleProperty> {
        @Override
        public JsonElement serialize(DoubleProperty src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.get());
        }

        @Override
        public DoubleProperty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new SimpleDoubleProperty(json.getAsDouble());
        }
    }

    public static class StringPropertyAdapter implements JsonSerializer<StringProperty>, JsonDeserializer<StringProperty> {
        @Override
        public JsonElement serialize(StringProperty src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.get());
        }

        @Override
        public StringProperty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new SimpleStringProperty(json.getAsString());
        }
    }

    public static class BooleanPropertyAdapter implements JsonSerializer<BooleanProperty>, JsonDeserializer<BooleanProperty> {
        @Override
        public JsonElement serialize(BooleanProperty src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.get());
        }

        @Override
        public BooleanProperty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new SimpleBooleanProperty(json.getAsBoolean());
        }
    }
}
