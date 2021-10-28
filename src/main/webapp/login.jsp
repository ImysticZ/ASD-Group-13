<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet"> 
        <jsp:include page="nav.jsp"/> 
    </head>
    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
        %>

        <br>
        <div class= "mx-auto text-center w-1/2 py-5 shadow-xl rounded-3xl my-96 max-w-2xl bg-white">
            <form action ="LoginServlet" method ="post">
                <table>
                    <tr class="notop1">
                        <td>Email</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="text" placeholder="<%= emailErr != null ? emailErr : "Enter Email"%>" name="email" required></td>
                    </tr>
                    <tr class="notop1">
                        <td>Password</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="password" placeholder="<%= passErr != null ? passErr : "Enter Password"%>" name="password" required></td>
                    </tr>
                </table>
                <input class="bg-blue-300 text-xl font-semibold px-4 py-1 rounded-lg hover:bg-blue-800 hover:text-white" type="submit" value="Login">
            </form>
            <p><%= existErr != null ? existErr : ""%></p>
        </div>

    </body>
</html>
