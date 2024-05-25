package es.uah.matcomp.proyecto.serializacion;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ListViewTypeAdapter<T> implements JsonSerializer<ListView<T>>, JsonDeserializer<ListView<T>> {

    @Override
    public JsonElement serialize(ListView<T> src, Type typeOfSrc, JsonSerializationContext context) {
        ObservableList<T> items = src.getItems();
        return context.serialize(items);
    }

    @Override
    public ListView<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ObservableList<T> items = FXCollections.observableArrayList();
        T[] array = context.deserialize(json, ((Class<T>) ((ParameterizedType) typeOfT).getActualTypeArguments()[0]).arrayType());
        items.addAll(array);
        return new ListView<>(items);
    }
}
