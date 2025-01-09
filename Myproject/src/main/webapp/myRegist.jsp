<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="Beans.Student" %>
<%@ page import="Beans.EnrollBean" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student's Registration</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- External CSS -->
    <style>
        table, th, td {
            border: 1px solid grey;
            padding: 7px;
        }
    </style>
</head>
<body style="background-color: #FDF5E6;">

<%
    // Get the student from session
    Student student = (Student) session.getAttribute("student");
    List<EnrollBean> enrollment = (List<EnrollBean>) request.getAttribute("enrollment");

    if (student != null) {
%>
        <h3>
            Name: <%= student.getFname() %> <%= student.getMname() %> <%= student.getLname() %> 
            Phone #: <%= student.getPhone() %>
        </h3>

        <p>My Classes:</p>
        <table style="border: 1px solid grey; border-collapse: collapse; background-color:white; text-align: left;">
            <thead style="background-color: #04AA6D; color: white;">
                <tr>
                    <th>Course ID</th>
                    <th>Course Title</th>
                    <th>Grade</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (enrollment != null && !enrollment.isEmpty()) {
                        for (EnrollBean enroll : enrollment) {
                %>
                            <tr>
                                <td><%= enroll.getCourseID() %></td>
                                <td><%= enroll.getTitle() %></td>
                                <td><%= enroll.getGrade() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3">No enrolled classes found.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <br>
        <a href="enroll.jsp?ssn=<%= student.getSsn() %>" 
           style="cursor:pointer;background-color: #4CAF50;font-family:Arial; border: none; color: white; padding: 7px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;">
           Enroll
        </a>
        <a href="registerLogin.jsp" 
           style="background-color: red;cursor:pointer;border: none; color: white; font-family:Arial; padding: 7px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;">
           Exit
        </a>
<%
    } else {
%>
        <h3>Student not found. Please log in again.</h3>
        <a href="registerLogin.jsp" style="background-color: red;cursor:pointer;border: none; color: white; font-family:Arial; padding: 7px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;">
            Login
        </a>
<%
    }
%>

</body>
</html>
