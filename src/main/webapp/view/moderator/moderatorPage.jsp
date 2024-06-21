<%@ page import="java.util.List" %>
<%@ page import="it.savoreco.model.entity.UserAccount" %>

<%
    List<UserAccount> usersList = (List<UserAccount>) request.getAttribute("usersList");
    if (usersList == null) {
        response.sendRedirect("./home.jsp");
        return;
    }%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Moderatore</title>
    <link rel="stylesheet" type="text/css" href="../../assets/styles/table.css">
</head>
<body>
<%@ include file="../../components/navbar.jsp" %>
<main>

    <h1>Dettagli Utenti</h1>
    <table>
        <tr class="disappearSmall">
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Green Points</th>
            <th>Expires</th>
            <th></th>
        </tr>

        <% for (UserAccount user : usersList) {%>
        <tr class=<%= (user.getDeleted()) ? "deleted-users" : "active-users" %> >
            <td data-label="Name:"><%= user.getName() %></td>
            <td data-label="Surname:"><%= user.getSurname() %></td>
            <td data-label="Age:"><%= user.getAge() %></td>
            <td data-label="Green Points:"><%= user.getEcoPoint() %></td>
            <% if(user.getDeleted()){ %>
            <td data-label="Expires:"><%= user.getExpires() %></td>
            <td data-label=""><button class="deleted-users">Ripristina</button></td>
            <% } else {%>
            <td class="disappearSmall">/</td>
            <td data-label=""><button class="active-users">Elimina</button></td>
            <% } %>
        </tr>
    <%
        }
    %>
    </table>


</main>
<%@ include file="../../components/footer.jsp" %>
</body>
</html>