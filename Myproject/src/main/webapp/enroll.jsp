<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Beans.ClassesBean, dao.CourseDAO, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h3 {
            color: #333;
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin: 15px 0 5px;
        }
        select {
            width: 300px;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        .button {
            padding: 10px 20px;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        .button-register { background-color: #4CAF50; }
        .button-drop { background-color: #2196F3; }
        .button-exit {
            background-color: #f44336;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border: none;
            border-radius: 4px;
        }
        .button-exit:hover {
            background-color: #c33c25;
        }
        .button:hover {
            opacity: 0.9;
        }
    </style>
    <script>
        function validateForm() {
            var courseId = document.getElementById("courseId").value;
            var grade = document.getElementById("grade").value;
            if (courseId === "") {
                alert("Please select a course.");
                return false;
            }
            if (grade === "") {
                alert("Please select a grade.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h3>Course Registration</h3>
    <form id="courseForm" action="registerClass" method="post" onsubmit="return validateForm()">
        <label for="courseId">Select Course:</label>
        <select name="courseId" id="courseId" required aria-label="Select Course">
            <%
            List<ClassesBean> classes = CourseDAO.getAllCourses();
            if (classes != null && !classes.isEmpty()) {
                for (ClassesBean item : classes) {
            %>
                <option value="<%= item.getCourseID() %>"><%= item.getTitle() %></option>
            <%
                }
            } else {
            %>
                <option value="">No courses available. Please contact the administrator.</option>
            <%
            }
            %>
        </select>

        <label for="grade">Select Grade:</label>
        <select name="grade" id="grade" required aria-label="Select Grade">
            <option value="">Select Grade</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <option value="F">F</option>
            <option value="W">Withdraw</option>
            <option value="WN">Withdraw/No Grade</option>
        </select>
        <br><br>

        <button type="button" onclick="window.location.href='myRegist.jsp'" class="button button-exit">Exit</button>
        <button type="submit" formaction="registerClass" class="button button-register">Register</button>
        <button type="submit" formaction="drop" class="button button-drop">Drop</button>
    </form>
</body>
</html>
