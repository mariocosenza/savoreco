<%@ page import="it.savoreco.model.entity.Food" %>
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    List<BoughtFood> orders = (List<BoughtFood>) request.getAttribute("orders");
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
    if ((orders == null)||(restaurant == null)) {
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
    <div class="orderBox">
    <div class="restaurantBox">
        <div class="info">
            <img src="<%= restaurant.getImageObject()%>" alt="logo" class="restaurantImage">
            <div>
                <h1><%= restaurant.getName() %></h1>
                <p><strong>Indirizzo:</strong> <%= restaurant.getAddress().getId().getStreet() %>
                    , <%= restaurant.getAddress().getId().getZipcode() %></p>
                <p><strong>Categoria:</strong> <%= restaurant.getCategory() %></p>
                <p><strong>Descrizione:</strong> <%= restaurant.getDescription() %></p>
                <p><strong>Costo di Consegna:</strong> <%= String.format("%.2f", restaurant.getDeliveryCost()) %>€</p>
            </div>
        </div>
    </div>

        <%
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
                <p><strong>Metodo di pagamento:</strong> <%= purchase.getPaymentMethod() %></p>
                <p><strong>Costo consegna:</strong> <%= String.format("%.2f", purchase.getDeliveryCost()) %>€</p>
                <p><strong>IVA:</strong> <%= purchase.getIva() %>%</p>
                <p><strong>Stato:</strong> <%= purchase.getStatus() %></p>
                <p><strong>Costo totale:</strong> <%= String.format("%.2f", purchase.getTotalCost()) %>€</p>
            </div>

            <h3>Dettagli utente:</h3>
            <div class="info">
                <p><strong>Email:</strong> <%= user.getEmail() %></p>
                <p><strong>Nome:</strong> <%= user.getName() %> <%= user.getSurname() %></p>
                <p><strong>Età:</strong> <%= user.getAge() %></p>
                <p><strong>Indirizzo:</strong> <%= user.getAddress().getId().getStreet() %>, <%= user.getAddress().getId().getZipcode() %>, <%= user.getCountryCode() %></p>
                <p><strong>Eliminato:</strong> <%= user.getDeleted() %></p>
            </div>

                <% for (BoughtFood boughtFood : boughtFoods) {
                    Food food = boughtFood.getFood();%>
                <div class="foodItem">
                    <img src="<%= food.getImageObject() %>" alt="<%= food.getName() %>" class="foodImage">
                    <div>
                        <h2><%= food.getName() %></h2>
                        <p>Descrizione: <%= food.getDescription() %></p>
                        <p><strong>Categoria:</strong> <%= food.getCategory() %></p>
                        <p><strong>Prezzo:</strong> <%= String.format("%.2f", boughtFood.getPrice()) %>€</p>
                        <p><strong>Green Points:</strong> <%= boughtFood.getGreenPoint() %></p>
                        <p><strong>Allergeni:</strong> <%= food.getAllergens() %></p>
                        <p><strong>Quantità:</strong> <%= boughtFood.getQuantity() %></p>
                    </div>
                </div>
                <% } %>
        </div>
        <%
            }
        %>
    </div>
</main>
<%@ include file="../../components/footer.jsp" %>
</body>
</html>
