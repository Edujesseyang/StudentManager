package cs151.application.model;

import com.google.gson.reflect.TypeToken;
import cs151.application.services.JsonFileStoreTool;

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

    public boolean isPresent(String name) {
        for (Student std : list) {
            if (std.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void sort() {
        mergeSort(this.list, 0, list.size() - 1);
    }

    private void mergeSort(List<Student> list, int start, int end) {
        if (start >= end) return;
        int mid = start + (end - start) / 2;
        mergeSort(list, start, mid);
        mergeSort(list, mid + 1, end);
        merge(list, start, mid, end);
    }

    private void merge(List<Student> list, int start, int mid, int end) {
        List<Student> merged = new ArrayList<>();
        int l = start, r = mid + 1;

        while (l <= mid && r <= end) {
            Student a = list.get(l);
            Student b = list.get(r);
            if (cmpNames(a.getName(), b.getName()) <= 0) {
                merged.add(a);
                l++;
            } else {
                merged.add(b);
                r++;
            }
        }

        while (l <= mid) merged.add(list.get(l++));
        while (r <= end) merged.add(list.get(r++));

        for (int i = 0; i < merged.size(); i++) {
            list.set(start + i, merged.get(i));
        }
    }

    private int cmpNames(String a, String b) {
        if (a == null && b == null) return 0;
        if (a == null) return -1;        // nulls first; change if you prefer last
        if (b == null) return 1;
        return a.compareToIgnoreCase(b); // lexicographic, case-insensitive
    }

    private void swap(List<Student> list, int i, int j) {
        Student temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
