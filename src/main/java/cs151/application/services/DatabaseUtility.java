package cs151.application.services;

import cs151.application.model.Student;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseUtility {
    private final List<String> defaultDatabaseList = new ArrayList<>(Arrays.asList("SQLite", "MySQL", "PostgresSQL", "MongoDB", "AWS", "Joins", "Indexing", "Transactions", "ACID", "Normalization", "Schema Design", "Primary Keys", "Foreign Keys", "Views", "Stored Procedures", "Query Optimization", "Execution Plans", "Replication", "Backup Strategies"));
    private final List<String> defaultLanguages = new ArrayList<>(Arrays.asList("Java", "C++", "Python"));

    public DatabaseUtility() {

    }

    public void initDatabaseSkillList() {
        try (DataAccessor da = new DataAccessor()) {
            if (!da.isDatabaseInitialed()) {
                for (String name : defaultDatabaseList) {
                    da.addDatabaseSkill(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initDefaultLanguages() {
        try (DataAccessor da = new DataAccessor()) {
            for (String name : defaultLanguages) {
                if (da.languagesSize() < 3) {
                    da.addLanguage(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initDatabase() {
        try (DataAccessor da = new DataAccessor()) {
            da.initDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSampleStudents() {
        Student s1 = new Student();
        s1.setName("John Doe");
        s1.setEmployed(true);
        s1.setAcademicStatus("Junior");
        s1.setPreferredRole("Front End");
        s1.addDatabase("SQLite");
        s1.addDatabase("MongoDB");
        s1.addDatabase("AWS");
        s1.addLanguage("Java");
        s1.setJobDetails("Sample Student");
        s1.addComment("Good sample student, number one");
        s1.addComment("Good coding style");

        Student s2 = new Student();
        s2.setName("Jenny Doe");
        s2.setEmployed(true);
        s2.setAcademicStatus("Freshmen");
        s2.setPreferredRole("Back End");
        s2.addDatabase("SQLite");
        s2.addDatabase("Joins");
        s2.addDatabase("MySQL");
        s2.addLanguage("Java");
        s2.addLanguage("C++");
        s2.setJobDetails("Sample Student manager");
        s2.addComment("She is good at front end coding");
        s2.addComment("Bad designer");

        Student s3 = new Student();
        s3.setName("Jackie Chen");
        s3.setEmployed(true);
        s3.setAcademicStatus("Freshmen");
        s3.setPreferredRole("QA");
        s3.addDatabase("SQLite");
        s3.addDatabase("AWS");
        s3.addLanguage("Python");
        s3.setJobDetails("Sample Student team leader");
        s3.addComment("He knows Kong Fu");

        Student s4 = new Student();
        s4.setName("Jesse Yang");
        s4.setEmployed(false);
        s4.setAcademicStatus("Junior");
        s4.setPreferredRole("Back end");
        s4.addDatabase("AWS");
        s4.addLanguage("Python");
        s4.addLanguage("Java");
        s4.addLanguage("C++");
        s4.addComment("Harding working but slow");

        Student s5 = new Student();
        s5.setName("Mr. CoCoulee");
        s5.setEmployed(true);
        s5.setAcademicStatus("Junior");
        s5.setPreferredRole("Cyber security");
        s5.addDatabase("SQLite");
        s5.addLanguage("Java");
        s5.addLanguage("C++");
        s3.setJobDetails("Sample Student");
        s5.addComment("He has good Java skills");
        s5.addComment("Smart, but play too hard");
        s5.addComment("Bad altitude");


        List<Student> sampleStudent = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5));

        ControllerUtility popper = new ControllerUtility();
        popper.popAlert(Alert.AlertType.CONFIRMATION, "This is the first time run this application, Do you want to load sample students?").showAndWait().filter(btn -> btn == ButtonType.OK).ifPresent(
                response -> {
                    try (DataAccessor da = new DataAccessor()) {
                        for (Student s : sampleStudent) {
                            da.addStudent(s);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
