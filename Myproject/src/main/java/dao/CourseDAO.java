package dao;

import Beans.ClassesBean;
import servlets.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public static List<ClassesBean> getAllCourses() {
        List<ClassesBean> courses = new ArrayList<>();
        String sql = "SELECT courseID, title FROM Course";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ClassesBean course = new ClassesBean();
                course.setCourseID(rs.getString("courseID"));
                course.setTitle(rs.getString("title"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
}
