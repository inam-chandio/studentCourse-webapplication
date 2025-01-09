package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.Student;
import Beans.EnrollBean;

@WebServlet(name = "LoginHandler", urlPatterns = {"/LoginHandler"})
public class LoginHandler extends HttpServlet {
    
    private boolean isValidSSN(String ssn) {
        return ssn != null && ssn.matches("^\\d{3}-\\d{2}-\\d{4}$");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String ssn = request.getParameter("ssn");
        
        if (!isValidSSN(ssn)) {
            response.sendRedirect("error.jsp?message=Invalid SSN format");
            return;
        }
        
        try (Connection conn = DBUtils.getConnection()) {
            // Fetch student data
            String studentSql = "SELECT * FROM Student WHERE ssn = ?";
            Student student = null;
            
            try (PreparedStatement stmt = conn.prepareStatement(studentSql)) {
                stmt.setString(1, ssn);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        student = new Student();
                        student.setSsn(rs.getString("ssn"));
                        student.setFname(rs.getString("fname"));
                        student.setMname(rs.getString("mname"));
                        student.setLname(rs.getString("lname"));
                        student.setPhone(rs.getString("phone"));
                        student.setEmail(rs.getString("email"));
                    }
                }
            }
            
            if (student != null) {
                // Fetch enrollment data
                String enrollSql = 
                    "SELECT e.courseID, c.title, e.grade " +
                    "FROM Enrollment e " +
                    "JOIN Course c ON e.courseID = c.courseID " +
                    "WHERE e.ssn = ?";
                
                List<EnrollBean> enrollments = new ArrayList<>();
                
                try (PreparedStatement stmt = conn.prepareStatement(enrollSql)) {
                    stmt.setString(1, ssn);
                    
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            EnrollBean enroll = new EnrollBean();
                            enroll.setCourseID(rs.getString("courseID"));
                            enroll.setTitle(rs.getString("title"));
                            enroll.setGrade(rs.getString("grade"));
                            enrollments.add(enroll);
                        }
                    }
                }
                
                // Store in session and request
                HttpSession session = request.getSession();
                session.setAttribute("student", student);
                request.setAttribute("enrollment", enrollments);
                
                // Forward to myRegist.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("myRegist.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("error.jsp?message=Invalid SSN");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error");
        }
    }
}