package cs151.application.Model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String preferName;
    private String academicStatus; // Freshman, Sophomore, etc.
    private boolean employed;
    private String jobDetails;

    private List<String> programmingLanguages; // coding language
    private List<String> databases;             // database skill
    private String preferredRole;               // Front-End, Back-End, etc.

    private List<String> comments;              // prof's comments
    private boolean whitelist;
    private boolean blacklist;

    public Student(String name) {
        this.name = name;
        this.programmingLanguages = new ArrayList<>();
        this.databases = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferName() {
        return preferName;
    }

    public void setPreferName(String preferName) {
        this.preferName = preferName;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    public String getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public List<String> getDatabases() {
        return databases;
    }

    public void setDatabases(List<String> databases) {
        this.databases = databases;
    }

    public String getPreferredRole() {
        return preferredRole;
    }

    public void setPreferredRole(String preferredRole) {
        this.preferredRole = preferredRole;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public boolean isWhitelist() {
        return whitelist;
    }

    public void setWhitelist(boolean whitelist) {
        this.whitelist = whitelist;
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public void setBlacklist(boolean blacklist) {
        this.blacklist = blacklist;
    }

    public String toString() {
        return "Student{" +
                "fullName='" + name + '\'' +
                "preferName='" + preferName + '\'' +
                ", academicStatus='" + academicStatus + '\'' +
                ", employed=" + employed +
                ", jobDetails='" + jobDetails + '\'' +
                ", programmingLanguages=" + programmingLanguages +
                ", databases=" + databases +
                ", preferredRole='" + preferredRole + '\'' +
                ", whitelist=" + whitelist +
                ", blacklist=" + blacklist +
                ", comments=" + comments +
                '}';
    }
}
