package servlets;

import Beans.ClassesBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FetchCourses")
public class FetchCoursesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ClassesBean> courses = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT courseID, title FROM Course";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ClassesBean course = new ClassesBean();
                    course.setCourseID(rs.getString("courseID"));
                    course.setTitle(rs.getString("title"));
                    courses.add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("classes", courses);
        request.getRequestDispatcher("/courseRegistration.jsp").forward(request, response);
    }
}
