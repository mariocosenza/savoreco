<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.savoreco.model.entity.UserAccount" %>

<%
    List<UserAccount> usersList = (List<UserAccount>) request.getAttribute("usersList");
    Map<UserAccount, Integer> greenPointMap = (Map<UserAccount, Integer>) request.getAttribute("greenPointMap");
    if ((usersList == null) || (greenPointMap == null)) {
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

    <h1>Dettagli Utenti attivi</h1>
    <table class="active-users">
        <tr class="fields">
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Green Points</th>
            <th></th>
        </tr>

        <% for (UserAccount user : usersList) {
            if (!user.getDeleted()) {%>
        <tr>
            <td data-label="Name"><%= user.getName() %></td>
            <td data-label="Surname"><%= user.getSurname() %></td>
            <td data-label="Age"><%= user.getBirthdate() %></td>
            <td data-label="Green Points"><%= greenPointMap.get(user) %></td>
            <td data-label=""><button>elimina</button></td>
        </tr>
    <%
            }
        }
    %>
    </table>

    <h1>Dettagli Utenti eliminati</h1>
    <table class="deleted-users">
        <tr class="fields">
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Green Points</th>
            <th>Expires</th>
        </tr>

        <% for (UserAccount user : usersList) {
            if (user.getDeleted()) {%>
        <tr>
            <td data-label="Name"><%= user.getName() %></td>
            <td data-label="Surname"><%= user.getSurname() %></td>
            <td data-label="Age"><%= user.getBirthdate() %></td>
            <td data-label="Green Points"><%= greenPointMap.get(user) %></td>
            <td data-label="Expires"><%= user.getExpires() %></td>
        </tr>
    <%
            }
        }
    %>
    </table>


</main>
<%@ include file="../../components/footer.jsp" %>
</body>
</html>