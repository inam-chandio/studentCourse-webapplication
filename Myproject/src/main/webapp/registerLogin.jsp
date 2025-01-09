<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
    <HEAD>
        <TITLE>Log into Your Courses</TITLE>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            fieldset {
                width: 300px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            legend {
                padding: 0 10px;
                font-weight: bold;
                color: #333;
            }
            input[type="text"] {
                width: 200px;
                padding: 5px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 3px;
            }
            .error-message {
                color: red;
                font-size: 14px;
                margin-top: 5px;
                display: none;
            }
        </style>
        <script>
            function validateSSN(input) {
                var ssnPattern = /^\d{3}-\d{2}-\d{4}$/;
                var errorMessage = document.getElementById('ssnError');
                
                if (!ssnPattern.test(input.value)) {
                    errorMessage.style.display = 'block';
                    return false;
                }
                errorMessage.style.display = 'none';
                return true;
            }
        </script>
    </HEAD>
    <BODY BGCOLOR="#FDF5E6">
        <fieldset>
            <legend>Enter student's SSN</legend>
            <form action="LoginHandler" method="post" onsubmit="return validateSSN(document.getElementById('ssnInput'))">
                SSN: <input type="text" id="ssnInput" name="ssn" placeholder="XXX-XX-XXXX" required/><br/>
                <div id="ssnError" class="error-message">Please enter SSN in XXX-XX-XXXX format</div>
                <br>
                <input type="submit" value="Login" 
                    style="background-color: DodgerBlue;
                           font-family: Arial;
                           cursor: pointer;
                           border: none;
                           color: white;
                           padding: 8px 15px;
                           text-align: center;
                           text-decoration: none;
                           display: inline-block;
                           font-size: 14px;
                           border-radius: 4px;"/>
            </form>  
        </fieldset>
    </BODY>
</HTML>