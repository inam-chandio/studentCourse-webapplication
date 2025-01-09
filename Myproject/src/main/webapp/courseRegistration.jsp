<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Beans.ClassesBean, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Registration</title>
    <style>
        .button {
            cursor: pointer;
            border: none;
            color: white;
            padding: 7px 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 5px;
        }
        .button-register { background-color: #4CAF50; }
        .button-drop { background-color: DodgerBlue; }
        .button-exit { background-color: red; }
        select {
            height: 30px;
            margin: 10px 0;
        }
    </style>
    <script>
        function validateForm() {
            const course = document.getElementById("courseId").value;
            const grade = document.getElementById("grade").value;
            if (!course) {
                alert("Please select a course.");
                return false;
            }
            if (!grade) {
                alert("Please select a grade.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h3>Course Registration</h3>
    <form method="post" onsubmit="return validateForm()">
        <label for="courseId">Select Course:</label>
        <select name="courseId" id="courseId" aria-label="Select Course">
            <%
            List<ClassesBean> classes = (List<ClassesBean>) session.getAttribute("classes");
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
        <select name="grade" id="grade" aria-label="Select Grade">
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <option value="F">F</option>
            <option value="W">W</option>
            <option value="WN">WN</option>
        </select>
        <br><br>

        <a href="myRegist.jsp" class="button button-exit">Exit</a>
        <button type="submit" formaction="registerClass" class="button button-register">Register</button>
        <button type="submit" formaction="drop" class="button button-drop">Drop</button>
    </form>
</body>
</html>
