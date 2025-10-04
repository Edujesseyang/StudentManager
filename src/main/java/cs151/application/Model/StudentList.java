package cs151.application.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private static final StudentList instance = new StudentList();

    private int size = 0;

    private StudentList() {
    }

    public static StudentList getInstance(){
        return instance;
    }

    private List<Student> list = new ArrayList();

    public boolean addStudent(Student std) {
        if (std != null) {
            list.add(std);
            size++;
            return true;
        }
        return false;
    }
}
