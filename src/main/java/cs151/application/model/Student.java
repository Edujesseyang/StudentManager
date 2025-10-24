package cs151.application.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student {
    private String name;
    private String academicStatus; // Freshman, Sophomore, etc.
    private boolean employed;
    private String jobDetails;
    private String preferredRole;  // front end/ back end etc.

    private List<String> programmingLanguages; // coding language
    private String databases;             // database skill
    private List<String> comments;              // prof's comments
    private boolean whitelist = true;
    private boolean blacklist = false;

    public Student() {
        this.programmingLanguages = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDatabases() {
        return databases;
    }

    public void setDatabases(String databases) {
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

    public void addLanguages(List<String> lang) {
        programmingLanguages.addAll(lang);
    }

    public void addComments(List<String> c) {
        comments.addAll(c);
    }

    /**
     * convert to string for test use
     */
    public String toString() {
        return "FullName : " + name +
                "\nAcademicStatus : " + academicStatus +
                "\nEmployed : " + employed +
                "\nJobDetails : " + jobDetails +
                "\nProgrammingLanguages : " + programmingLanguages +
                "\nDatabases : " + databases +
                "\nPreferredRole : " + preferredRole +
                "\nWhitelist : " + whitelist +
                "\nBlacklist : " + blacklist +
                "\nComments : " + comments;
    }

    /**
     * make a string for all list that student skilled
     */

    public void addComment(String comment) {
        this.comments.add(comment);
    }



}
