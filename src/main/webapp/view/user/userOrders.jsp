<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="it.savoreco.model.entity.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%
    @SuppressWarnings("unchecked") List<BoughtFood> orders = (List<BoughtFood>) request.getAttribute("orders");
    UserAccount user = (UserAccount) request.getAttribute("user");
    if ((orders == null) || (user == null)) {
        response.sendRedirect("./home.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ordini</title>
    <link rel="stylesheet" type="text/css" href="../../assets/styles/orders.css">
</head>
<body>
<%@ include file="../../components/navbar.jsp" %>

<main>
    <h1 class="title"><strong>Tutti I Tuoi Ordini</strong></h1>
    <% if (orders.isEmpty()) { %>
    <h1>Non hai ancora effettuato nessun ordine</h1>
    <% }

        Map<Purchase, List<BoughtFood>> categorizedBoughtFood = new HashMap<>();
        for (BoughtFood boughtFood : orders) {
            categorizedBoughtFood.computeIfAbsent(boughtFood.getPurchase(), k -> new ArrayList<>()).add(boughtFood);
        }

        for (Map.Entry<Purchase, List<BoughtFood>> entry : categorizedBoughtFood.entrySet()) {
            Purchase purchase = entry.getKey();
            List<BoughtFood> boughtFoods = entry.getValue();
    %>
    <div class="purchaseBox">
        <h3>Acquisto del  <%= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .format(purchase.getTime().atZone(ZoneId.systemDefault()))%>:</h3>
        <div class="info">
            <p><strong>Costo consegna:</strong> <%= String.format("%.2f", purchase.getDeliveryCost()) %>€</p>
            <p><strong>IVA:</strong> <%= purchase.getIva() %>%</p>
            <p><strong>Stato:</strong> <%= purchase.getStatus() %></p>
            <p><strong>Metodo di pagamento:</strong> <%= purchase.getPaymentMethod() %></p>
            <p><strong>Costo totale:</strong> <%= String.format("%.2f", purchase.getTotalCost()) %>€</p>
            <% if(purchase.getPickUp()) { %>
            <p><strong>Consegna:</strong> Ritiro al ristorante </p>
            <%} else {%>
            <p><strong>Indirizzo: </strong> <%= user.getAddress().getId().getStreet() %>
                , <%= user.getAddress().getId().getZipcode() %></p>
            <% } %>
        </div>

        <% for (BoughtFood boughtFood : boughtFoods) {
            Restaurant restaurant = boughtFood.getRestaurant();
        %>
        <div class="foodItem">
            <div>
                <h2><%= boughtFood.getName() %></h2>
                <p><strong>Da</strong> <a href="<c:url value="/restaurant?id=<%=restaurant.getId()%>"/>"><%= restaurant.getName()%></a></p>
                <p><strong>Quantità:</strong> <%= boughtFood.getQuantity() %></p>
                <p><strong>Prezzo:</strong> <%= String.format("%.2f", boughtFood.getPrice()) %>€</p>
                <p><strong>Green Points:</strong> <%= boughtFood.getGreenPoint() %></p>
            </div>
        </div>
        <% } %>
    </div>
    <%
        }
    %>
</main>
<%@ include file="../../components/footer.jsp" %>
</body>
</html>
