package cs151.application.services;

import cs151.application.model.Student;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataAccessor {
    Path path = Paths.get("localData", "database.db");
    String url = "jdbc:sqlite:" + path;
    Connection conn;

    DataAccessor() throws Exception {
        Files.createDirectories(path.getParent());
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (conn == null) throw new SQLException("Connection lost");

        conn.setAutoCommit(false);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
            st.execute("PRAGMA journal_mode = WAL");
            st.execute("PRAGMA busy_timeout = 5000");
        }

        try (Statement st = conn.createStatement()) {
            st.execute("""
                        CREATE TABLE IF NOT EXISTS students(
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            academic_status TEXT,
                            is_employed INTEGER DEFAULT 0 CHECK (is_employed IN (0,1)),
                            job_detail TEXT,
                            prefer_role TEXT,
                            db_list TEXT
                        )
                    """);

            st.execute("""
                        CREATE TABLE IF NOT EXISTS languages(
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            lang_name TEXT NOT NULL UNIQUE
                        )
                    """);

            st.execute("""
                        CREATE TABLE IF NOT EXISTS student_language_map(
                            student_id INTEGER NOT NULL,
                            lang_name TEXT
                        )
                    """);

            st.execute("""
                        CREATE TABLE IF NOT EXISTS comments(
                            student_id INTEGER NOT NULL,
                            comment TEXT
                        )
                    """);
        }
        conn.commit();
    }

    public List<String> getLanguageList() throws SQLException {
        String sqlComment = """
                SELECT lang_name FROM languages
                """;

        List<String> res = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sqlComment);) {
            try (ResultSet set = ps.executeQuery()) {
                while (set.next()) {
                    res.add(set.getString("lang_name"));
                }
            }
        }
        return res;
    }

    public Long findStudentId(String studentName) throws SQLException {
        String sql = """
                SELECT id FROM students
                WHERE name = ? COLLATE NOCASE
                LIMIT 1
                """;
        long res = -1;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentName);
            try (ResultSet set = ps.executeQuery()) {
                if (set.next()) {
                    res = set.getLong("id");
                }
            }
        }
        return res;
    }

    public Student getStudent(String stdName) throws SQLException {
        Long id = findStudentId(stdName);
        Student res = new Student();
        String sqlComment = """
                SELECT id, name, academic_status, is_Employed, job_detail, prefer_role
                FROM students
                WHERE id = ?
                LIMIT 1
                """;
        try (PreparedStatement ps = conn.prepareStatement(sqlComment)) {
            ps.setLong(1, id);
            try (ResultSet set = ps.executeQuery()) {
                if (!set.next()) {
                    return null;
                }
                res.setName(set.getString("name"));
                res.setAcademicStatus(set.getString("academic_status"));
                res.setEmployed(set.getInt("is_employed") != 0);
                res.setJobDetails(set.getString("job_detail"));
                res.setPreferredRole(set.getString("prefer_role"));
                res.setDatabasesString(set.getString("db_list"));
            }
        }

        try {
            res.addLanguages(getLanguageListByStudent(id));
            res.addComments(getCommentList(id));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public List<String> getLanguageListByStudent(Long studentId) throws SQLException {
        final String sql = """
                SELECT lang_name FROM student_language_map WHERE id = ?
                """;
        List<String> res = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) res.add(rs.getString("lang_name"));
            }
        }
        return res;
    }

    public List<String> getCommentList(Long studentId) throws SQLException {
        List<String> res = new ArrayList<>();
        String sql = """
                SELECT comment FROM comments WHERE student_id = ?
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, studentId);
            try (ResultSet set = ps.executeQuery()) {
                while (set.next()) {
                    res.add(set.getString("comment"));
                }
            }
        }
        return res;
    }

    public void insertStudent(Student std) throws SQLException {
        if (std.getName().isBlank()) return;

        long newId = -1;
        String sql = """
                INSERT INTO students(name, academic_status, isEmployed, job_detail, prefer_role, db_list)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, std.getName());
            ps.setString(2, std.getAcademicStatus());
            ps.setInt(3, (std.isEmployed() ? 1 : 0));
            ps.setString(4, std.getJobDetails());
            ps.setString(5, std.getPreferredRole());
            ps.setString(6, std.getDatabasesString());
            try (ResultSet set = ps.executeQuery()) {
                if (set.next()) newId = set.getLong("id");
            }
        }
        if (newId != -1 && !std.getProgrammingLanguages().isEmpty()) {
            insertStudentLangMap(newId, std.getProgrammingLanguages());
        }
        if (newId != -1 && !std.getComments().isEmpty()) {
            insertComments(newId, std.getComments());
        }

        conn.commit();
    }

    private void insertStudentLangMap(Long id, List<String> langList) throws SQLException {
        String sql = """
                INSERT INTO student_language_map(student_id, lang_name)
                VALUES (?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (String str : langList) {
                ps.setLong(1, id);
                ps.setString(2, str);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertComments(Long id, List<String> comment) throws SQLException {
        String sql = """
                INSERT INTO comments(student_id, comment)
                VALUES (?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (String c : comment) {
                ps.setLong(1, id);
                ps.setString(2, c);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public void addComment(String studentName, String comment) throws SQLException {
        Long id = findStudentId(studentName);
        List<String> input = new ArrayList<>();
        input.add(comment);
        insertComments(id, input);
    }




}
