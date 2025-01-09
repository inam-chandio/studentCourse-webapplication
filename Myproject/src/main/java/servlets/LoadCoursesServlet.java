package servlets;

import Beans.ClassesBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loadCourses")
public class LoadCoursesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ssn = request.getParameter("ssn");
        
        try (Connection conn = DBUtils.getConnection()) {
            // Query to get available courses (not yet enrolled by the student)
            String sql = "SELECT c.courseID, c.title, c.credits FROM Course c " +
                        "WHERE c.courseID NOT IN (SELECT courseID FROM Enrollment WHERE ssn = ?)";
            
            List<ClassesBean> classes = new ArrayList<>();
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, ssn);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ClassesBean course = new ClassesBean();
                        course.setCourseID(rs.getString("courseID"));
                        course.setTitle(rs.getString("title"));
                        course.setCredits(rs.getInt("credits"));
                        classes.add(course);
                    }
                }
            }
            
            session.setAttribute("classes", classes);
            response.sendRedirect("enroll.jsp");
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error");
        }
    }
}