
<%@page import="java.util.List"%>
<%@page import="ma.ensao.hibernateDAO.*"%>
<%@page import="java.util.Date"%>
<%@page import="ma.ensao.entity.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <title>Home Page</title>        
    </head>
    <body>  
    <center>
        <div id="mystyle">
            <h1>Java, C++, J2EE, Tutorial</h1>
            <p><a href="http://www.forum.codecall.net">http://www.forum.codecall.net</a><br/>
                <b>Integrating JSP, Hibernate in an MVC Application</b><br/>
                <%=new Date()%></br>
                <%
                    User user = (User) session.getAttribute("user");
                %>            
                <b>Welcome <%= user.getFirstName() + " " + user.getLastName()%></b>            
                <br/>
                <a href="logout.jsp">Logout</a>
            </p>

            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>email</th> 
                        <th>status</th>                       
                    </tr>
                </thead>
                <tbody>
                    <%
                        LoginService loginService = new LoginService();
                        List<User> list = loginService.getListOfUsers();
                        for (User u : list) {
                    %>
                    <tr>
                        <td><%=u.getUserId()%></td>
                        <td><%=u.getFirstName()%></td>  
                        <td><%=u.getLastName()%></td>
                        <td><%=u.getEmail()%></td>
                        <td><%=u.getStatus()%></td>
                    </tr>
                    <%}%>
                <tbody>
            </table>            
            <br/>
        </div>

    </center>            
</body>
</html>
