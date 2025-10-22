package cs151.application.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileStoreTool<T> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path file;
    private final Type type;

    public JsonFileStoreTool(Path file, Type type) {
        this.file = file;
        this.type = type;
    }

    /**
     * load from json
     * @param defaultValue : T
     * @return T
     */
    public T load(T defaultValue) {
        try {
            if (!Files.exists(file)) { // if file is not exists
                Files.createDirectories(file.getParent()); // create director
                Files.writeString(file, "[]"); // init default
                try (var write = Files.newBufferedWriter(file)) { // write a new file
                    gson.toJson(defaultValue, write);
                }
                return defaultValue;
            }
            // else
            try (var read = Files.newBufferedReader(file)) {
                return gson.fromJson(read, type);  // read that file
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return defaultValue;
        }
    }

    /**
     * save to json
     * @param data T
     */
    public void save(T data) {
        try {
            Files.createDirectories(file.getParent()); // try to create a directory
            try (var write = Files.newBufferedWriter(file)) {
                gson.toJson(data, type, write); // write data into the file
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
