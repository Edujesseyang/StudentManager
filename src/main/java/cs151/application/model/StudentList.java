package cs151.application.model;

import com.google.gson.reflect.TypeToken;
import cs151.application.tools.JsonFileStoreTool;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private static final StudentList instance = new StudentList();

    // json building to for parse
    private final JsonFileStoreTool<List<Student>> store = new JsonFileStoreTool<>(Paths.get("localData", "studentsDB.json"), new TypeToken<List<Student>>() {
    }.getType());

    private StudentList() { // singleton private constructor
    }

    public List<Student> getList() {
        return list;
    } // getter

    public static StudentList getInstance() {
        return instance;
    } // getInstance expose to public

    private final List<Student> list = new ArrayList<>();

    /**
     * add a student object ot list
     *
     * @param std Student
     */
    public void addStudent(Student std) {
        if (std != null) {
            list.add(std);
        }
    }

    /**
     * load json file to the student list
     */
    public void load() {
        list.clear();
        List<Student> loaded = store.load(new ArrayList<>());
        if (loaded != null) {
            list.addAll(loaded);
        }
    }

    /**
     * save the list to json file
     */
    public void save() {
        store.save(list);
    }

    public boolean isPresent(String name){
        for(Student std : list){
            if(std.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
