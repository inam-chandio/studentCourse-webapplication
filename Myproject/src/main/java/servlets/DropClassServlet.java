package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Beans.Student;
import servlets.DBUtils;

@WebServlet("/drop")
public class DropClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Student student = (Student) session.getAttribute("student");
        
        if (student == null) {
            response.sendRedirect("error.jsp?message=Session expired. Please log in again.");
            return;
        }
        
        String courseId = request.getParameter("courseId");
        
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "DELETE FROM Enrollment WHERE ssn = ? AND courseID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, student.getSsn());
                stmt.setString(2, courseId);
                
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    response.sendRedirect("myRegist.jsp?message=Course dropped successfully.");
                } else {
                    response.sendRedirect("enrollDropError.jsp?message=Drop failed. No such enrollment exists.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("enrollDropError.jsp?message=Database error: " + e.getMessage());
        }
    }
}
