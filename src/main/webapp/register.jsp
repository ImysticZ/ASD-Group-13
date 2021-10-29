<%@page import="uts.asd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet"> 
        <jsp:include page="nav.jsp"/> 
</head>
<%
        String existErr = (String) session.getAttribute("existErr");
        String emailErr = (String) session.getAttribute("emailErr");
        String passErr = (String) session.getAttribute("passErr");
        String fnameErr = (String) session.getAttribute("fnameErr");
        String lnameErr = (String) session.getAttribute("lnameErr");
        String phoneErr = (String) session.getAttribute("phoneErr");
        String addressErr = (String) session.getAttribute("addressErr");
        String typeErr = (String) session.getAttribute("typeErr");
%>
<body>
        <div>
            <%-- <h1><a href="index.jsp">Four Seasons Hotel</a></h1>
            <a href="login.jsp">login</a>
            <a href="register.jsp">register</a> --%>
        </div>
        <br>
        <div class= "mx-auto text-center w-1/2 py-5 shadow-xl rounded-3xl my-96 max-w-2xl bg-white">
            <form action=RegisterServlet method="post">
                <table>
                    <tr class="notop1">
                        <td>First Name</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="text" placeholder="<%= fnameErr != null ? fnameErr : "Enter First Name"%>" name="first_name" required></td>
                    </tr>
                    <tr class="notop1">
                        <td>Last Name</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="text" placeholder="<%= lnameErr != null ? lnameErr : "Enter Last Name"%>" name="last_name" required></td>
                    </tr>
                    <tr class="notop1">
                        <td>Email</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="text" placeholder="<%= emailErr != null ? emailErr : "Enter Email"%>" name="email" required></td>
                    </tr>
                    <tr class="notop1">
                        <td>Phone</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="text" placeholder="<%= phoneErr != null ? phoneErr : "Enter Phone"%>" name="phone"></td>
                    </tr>
                    <tr class="notop1">
                        <td>Password</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="password" placeholder="<%= passErr != null ? passErr : "Enter Password"%>" name="password" required></td>
                    </tr>
                    <tr class="notop1">
                        <td>Address</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="text" placeholder="<%= addressErr != null ? addressErr : "Enter Address"%>" name="address" required></td>
                    </tr>
                    <tr class="notop1">
                        <td>User type</td>
                        <td><input class = "w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2" type="text" placeholder="<%= typeErr != null ? typeErr : "Enter Type c/s/a"%>" name="type" required></td>
                    </tr>
                </table>
                <input class="bg-blue-300 text-xl font-semibold px-4 py-1 rounded-lg hover:bg-blue-800 hover:text-white" type="submit" value="Sign up">
            </form>
            <p><%= existErr != null ? existErr : ""%></p>
        </div>
    </body>
</html>