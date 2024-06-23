<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.savoreco.model.entity.Restaurant" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.savoreco.model.entity.BoughtFood" %>
<%@ page import="it.savoreco.model.entity.Purchase" %>
<%@ page import="it.savoreco.model.entity.UserAccount" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%
    List<BoughtFood> orders = (List<BoughtFood>) request.getAttribute("orders");
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
    if ((orders == null) || (restaurant == null)) {
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
    <h1 class="title"><strong>Tutti Gli Ordini del Ristorante</strong></h1>
    <% if (orders.isEmpty()) { %>
    <h1 class="center">Il tuo Ristorante non ha ancora ricevuto nessun'ordine</h1>
    <% }

        Map<Purchase, List<BoughtFood>> categorizedBoughtFood = new HashMap<>();
        for (BoughtFood boughtFood : orders) {
            categorizedBoughtFood.computeIfAbsent(boughtFood.getPurchase(), k -> new ArrayList<>()).add(boughtFood);
        }

        for (Map.Entry<Purchase, List<BoughtFood>> entry : categorizedBoughtFood.entrySet()) {
            Purchase purchase = entry.getKey();
            List<BoughtFood> boughtFoods = entry.getValue();
            UserAccount user = purchase.getUser();
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
            <% if (purchase.getAddress() == null) {%>
            <p><strong>Indirizzo: </strong> <%= user.getAddress().getId().getStreet() %>
                , <%= user.getAddress().getId().getZipcode() %></p>
            <% } else {%>
            <p><strong>Indirizzo: </strong> <%= purchase.getAddress().getId().getStreet() %>
                , <%= purchase.getAddress().getId().getZipcode() %></p>
            <% } %>
        </div>

        <h3>Dettagli utente:</h3>
        <div class="info">
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Nome:</strong> <%= user.getName() %> <%= user.getSurname() %></p>
            <p><strong>Data di Nascita:</strong> <%= user.getBirthdate() %></p>
            <p><strong>Indirizzo:</strong> <%= user.getAddress().getId().getStreet() %>
                , <%= user.getAddress().getId().getZipcode() %>, <%= user.getCountryCode() %></p>
            <p><strong>Eco Points:</strong> <%= user.getEcoPoint() %></p>
            <% if (user.getDeleted()) {%>
            <p>Verrà <strong>eliminato</strong> il: <%= DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    .format(user.getExpires().atZone(ZoneId.systemDefault()))%></p>
            <% } %>
        </div>

        <% for (BoughtFood boughtFood : boughtFoods) {%>
        <div class="foodItem">
            <div>
                <h2><%= boughtFood.getName() %></h2>
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
