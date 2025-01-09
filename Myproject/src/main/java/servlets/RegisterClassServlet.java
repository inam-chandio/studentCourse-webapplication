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

@WebServlet("/registerClass")
public class RegisterClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        
        if (student == null) {
            response.sendRedirect("error.jsp?message=Session expired");
            return;
        }
        
        String courseId = request.getParameter("courseId");
        String grade = request.getParameter("grade");
        
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "INSERT INTO Enrollment (ssn, courseID, grade) VALUES (?, ?, ?)";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, student.getSsn());
                stmt.setString(2, courseId);
                stmt.setString(3, grade);
                
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    // Successfully registered
                    response.sendRedirect("myRegist.jsp");
                } else {
                    response.sendRedirect("error.jsp?message=Registration failed");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("enrollRegisterError.jsp?message=Database error");
        }
    }
}