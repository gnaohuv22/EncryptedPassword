<%-- 
    Document   : login
    Created on : Aug 24, 2023, 5:51:24 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            <h1>Sign-in</h1>
            <label for="email">Email</label><br>
            <input type="email" name="email" value="${email}" maxlength="64"><br>

            <label for="password">Password</label><br>
            <input type="password" name="password" maxlength="32"><br>
            
            <input type="submit" value="Sign-in"><br>
        </form>
            <a href="register">Register</a>
    </body>
</html>
