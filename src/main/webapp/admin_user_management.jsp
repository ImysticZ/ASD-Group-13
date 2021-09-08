
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <jsp:include page="nav.jsp"/> 
    </head>
    <body>
        <div>
                <table>
                    <form action="AdminManagementServlet" method="GET">
                        <tr>
                            <td>E-mail address</td>
                            <td><input type="text" placeholder="name@something.com" name="email"></td>
                            <td><input type="submit" value="submit" class="button"></td>
                        </tr>
                    </form>
                </table>
        </div>
    </body>
</html>