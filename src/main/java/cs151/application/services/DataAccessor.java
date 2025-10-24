package cs151.application.services;

import cs151.application.model.Student;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataAccessor implements AutoCloseable {
    Path path = Paths.get("localData", "database.db");
    String url = "jdbc:sqlite:" + path;
    Connection conn;

    public DataAccessor() throws Exception {
        Files.createDirectories(path.getParent());
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " 1 ");
        }

        conn.setAutoCommit(true);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
            st.execute("PRAGMA journal_mode = WAL");
            st.execute("PRAGMA busy_timeout = 5000");
            conn.setAutoCommit(false);
            st.execute("""
                        CREATE TABLE IF NOT EXISTS students(
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL UNIQUE COLLATE NOCASE,
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
                            lang_name TEXT NOT NULL COLLATE NOCASE UNIQUE
                        )
                    """);

            st.execute("""
                        CREATE TABLE IF NOT EXISTS student_language_map(
                            student_id INTEGER NOT NULL REFERENCES students(id) ON DELETE CASCADE,
                            lang_id INTEGER NOT NULL REFERENCES languages(id),
                            PRIMARY KEY(student_id, lang_id)
                        )
                    """);

            st.execute("""
                        CREATE TABLE IF NOT EXISTS comments(
                            student_id INTEGER NOT NULL REFERENCES students(id) ON DELETE CASCADE,
                            comment TEXT
                        )
                    """);
        }

        conn.commit();
        conn.setAutoCommit(true);
    }

    public void ensureDefaultLanguage() throws SQLException {
        List<String> defaultLanguages = new ArrayList<>(Arrays.asList("Java", "C++", "Python"));
        for (String str : defaultLanguages) {
            if (languagesSize() >= 3) return;
            try {
                addLanguage(str);
            } catch (Exception ignored) {
            }
        }
    }

    public List<String> getLanguageList() throws SQLException {
        String sqlComment = """
                SELECT lang_name FROM languages
                """;

        List<String> res = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sqlComment)) {
            try (ResultSet set = ps.executeQuery()) {
                while (set.next()) {
                    res.add(set.getString(1));
                }
            }
        }
        return res;
    }

    public Long languagesSize() throws SQLException {
        String sql = """
                SELECT COUNT(*) FROM languages
                """;
        long size;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet report = ps.executeQuery();
            size = report.next() ? report.getLong(1) : 0L;
        }
        return size;
    }

    public boolean addLanguage(String langName) throws SQLException {
        String sql = """
                INSERT INTO languages(lang_name)
                VALUES (?)
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, langName.trim());
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteLanguage(String langName) throws SQLException {
        if (langName == null) return false;
        String sql = """
                DELETE FROM languages WHERE lang_name = ? COLLATE NOCASE
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, langName.trim());
            int deleteReport = ps.executeUpdate();
            if (deleteReport <= 0) return false;
            System.out.println("LanguageId=" + deleteReport + " is deleted");
        }
        return true;
    }


    public Long findStudentId(String studentName) throws SQLException {
        String sql = """
                SELECT id FROM students
                WHERE name = ? COLLATE NOCASE
                LIMIT 1
                """;
        Long res = null;
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
        if (stdName == null) return null;
        Student res = new Student();
        res.setName("data accessor issue");
        String sqlComment = """
                SELECT id, name, academic_status, is_employed, job_detail, prefer_role, db_list
                FROM students
                WHERE name = ?
                LIMIT 1
                """;
        long id;
        try (PreparedStatement ps = conn.prepareStatement(sqlComment)) {
            ps.setString(1, stdName);
            try (ResultSet set = ps.executeQuery()) {
                if (!set.next()) {
                    return null;
                }
                res.setName(set.getString("name"));
                res.setAcademicStatus(set.getString("academic_status"));
                res.setEmployed(set.getInt("is_employed") != 0);
                res.setJobDetails(set.getString("job_detail"));
                res.setPreferredRole(set.getString("prefer_role"));
                res.setDatabases(set.getString("db_list"));
                id = set.getInt("id");
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

    public List<String> getStudentNameList() throws SQLException {
        List<String> stdNameList = new ArrayList<>();
        String sql = """
                SELECT name FROM students ORDER BY name
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet allNameSet = ps.executeQuery();
            while (allNameSet.next()) {
                stdNameList.add(allNameSet.getString("name"));
            }
        }
        return stdNameList;
    }

    public boolean deleteStudent(String stdName) throws SQLException {
        String sql = """
                DELETE FROM students WHERE name = ? COLLATE NOCASE
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, stdName);
            int deleteLine = ps.executeUpdate();
            if (deleteLine <= 0) return false;
            System.out.println("Student " + stdName + " is deleted successful");
        }

        String deleteLang = """
                DELETE FROM student_language_map WHERE name = ? COLLATE NOCASE
                """;
        try (PreparedStatement ps = conn.prepareStatement(deleteLang)) {
            ps.setString(1, stdName);
            ps.executeUpdate();
        }

        return true;
    }

    public List<String> getLanguageListByStudent(long id) throws SQLException {
        final String sql = """
                SELECT lang_name FROM student_language_map WHERE student_id = ? COLLATE NOCASE
                """;
        List<String> res = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) res.add(rs.getString("lang_name"));
            }
        }
        return res;
    }

    public List<String> getCommentList(long id) throws SQLException {
        List<String> res = new ArrayList<>();
        String sql = """
                SELECT comment FROM comments WHERE student_id = ?
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet set = ps.executeQuery()) {
                while (set.next()) {
                    res.add(set.getString("comment"));
                }
            }
        }
        return res;
    }

    public boolean isPresent(String name) throws SQLException {
        String sql = """
                SELECT name FROM students WHERE name = ? COLLATE NOCASE
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name.trim());
            ResultSet set = ps.executeQuery();
            return set.next();
        }
    }

    public void addStudent(Student std) throws SQLException {
        if (std.getName().isBlank()) return;

        long newId = -1;
        String sql = """
                INSERT INTO students(name, academic_status, is_employed, job_detail, prefer_role, db_list)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, std.getName());
            ps.setString(2, std.getAcademicStatus());
            ps.setInt(3, (std.isEmployed() ? 1 : 0));
            ps.setString(4, std.getJobDetails());
            ps.setString(5, std.getPreferredRole());
            ps.setString(6, std.getDatabases());
            try (ResultSet set = ps.executeQuery()) {
                if (set.next()) newId = set.getLong("id");
            }
        }
        if (newId != -1 && !std.getProgrammingLanguages().isEmpty()) {
            insertStudentLangMap(newId, std.getProgrammingLanguages());
        }
        if (newId != -1  && !std.getComments().isEmpty()) {
            insertComments(newId, std.getComments());
        }
    }

    private void insertStudentLangMap(Long id, List<String> langList) throws SQLException {
        String sql = """
                INSERT INTO student_language_map(student_id, lang_name)
                VALUES (?, ?)
                """;

        insertList(id, langList, sql);
    }

    private void insertComments(Long id, List<String> comment) throws SQLException {
        String sql = """
                INSERT INTO comments(student_id, comment)
                VALUES (?, ?)
                """;
        insertList(id, comment, sql);
    }

    private void insertList(Long id, List<String> list, String sqlCommand) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sqlCommand)) {
            for (String c : list) {
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

    @Override
    public void close() throws Exception {
        conn.close();
    }
}
