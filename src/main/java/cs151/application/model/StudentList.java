package cs151.application.model;

import com.google.gson.reflect.TypeToken;
import cs151.application.tools.JsonFileStoreTool;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private static final StudentList instance = new StudentList();

    private final JsonFileStoreTool<List<Student>> store = new JsonFileStoreTool<>(Paths.get("localData", "studentsDB.json"), new TypeToken<List<Student>>() {
    }.getType());

    private StudentList() {
    }

    public static StudentList getInstance() {
        return instance;
    }

    private List<Student> list = new ArrayList();

    public boolean addStudent(Student std) {
        if (std != null) {
            list.add(std);
            return true;
        }
        return false;
    }

    public List<Student> getList() {
        return list;
    }

    public void load() {
        list.clear();
        List<Student> loaded = store.load(new ArrayList<>());
        if (loaded != null) {
            list.addAll(loaded);
        }

    }

    public void save() {
        store.save(list);
    }


}
