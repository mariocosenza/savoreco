<%@ page import="it.savoreco.model.entity.Restaurant" %>
<%@ page import="it.savoreco.model.entity.BoughtFood" %>
<%@ page import="it.savoreco.model.entity.Purchase" %>
<%@ page import="it.savoreco.model.entity.UserAccount" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%

    @SuppressWarnings("unchecked") List<BoughtFood> orders = (List<BoughtFood>) request.getAttribute("orders");
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
    if ((orders == null) || (restaurant == null)) {
        response.sendRedirect("./home.jsp");
        return;
    }
    orders.sort(Comparator.comparing(a -> a.getPurchase().getTime()));
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ordini</title>
    <link rel="stylesheet" type="text/css" href="../../assets/styles/orders.css">
    <%@ include file="/components/header.jsp" %>
    <script src="../../scripts/orders.js"></script>
</head>
<body>
<%@ include file="../../components/navbar.jsp" %>

<main>
    <h1 class="title"><strong>Tutti Gli Ordini del Ristorante</strong></h1>
    <% if (orders.isEmpty()) { %>
    <h1 class="center">Il tuo Ristorante non ha ancora ricevuto nessun ordine</h1>
    <% }
        Map<Purchase, List<BoughtFood>> foodMap = new HashMap<>();
        for (BoughtFood boughtFood : orders) {
            foodMap.computeIfAbsent(boughtFood.getPurchase(), k -> new ArrayList<>()).add(boughtFood);
        }

        List<Map.Entry<Purchase, List<BoughtFood>>> entries = new ArrayList<>(foodMap.entrySet());
        entries.sort(Comparator.comparing((Map.Entry<Purchase, List<BoughtFood>> entry) -> entry.getKey().getTime()).reversed());

        for (Map.Entry<Purchase, List<BoughtFood>> entry : entries) {
            Purchase purchase = entry.getKey();
            List<BoughtFood> boughtFoods = entry.getValue();
            UserAccount user = purchase.getUser();
    %>
    <div class="purchaseBox">
        <h3>Ordine del  <%= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .format(purchase.getTime().atZone(ZoneId.systemDefault()))%>:</h3>
        <div>
            <div class="info">
                <p><strong>Costo consegna:</strong> <%= String.format("%.2f", purchase.getDeliveryCost()) %>€</p>
                <p><strong>IVA:</strong> <%= purchase.getIva() %>%</p>
                <p><strong>Stato:</strong> <%= purchase.getStatus() %>
                </p>
                <p><strong>Costo totale:</strong> <%= String.format("%.2f", purchase.getTotalCost()) %>€</p>
                <% if (purchase.getPickUp()) { %>
                <p><strong>Consegna:</strong> Ritiro al ristorante </p>
                <%} else {%>
                <p><strong>Indirizzo: </strong> <%= user.getAddress().getId().getStreet() %>
                    , <%= user.getAddress().getId().getZipcode() %>
                </p>
                <% } %>
            </div>
            <div id="<%= purchase.getId()%>" class="info">
                <strong class="status">Stato dell'ordine: </strong>
                <p><%= purchase.getStatus() %>
                </p>
                <select name="dropdown" onchange="updateStatus(this.value, '<%= purchase.getId()%>')">
                    <option value="change">Change</option>
                    <% if (!purchase.getStatus().equals(Purchase.Statuses.pending)) { %>
                    <option value="delivering">Delivering</option>
                    <option value="delivered">Delivered</option>
                    <option value="confirmed">Confirmed</option>
                    <% } %>
                    <% if (purchase.getStatus().equals(Purchase.Statuses.canceled)) { %>
                    <option value="payed">Payed</option>
                    <option value="pending">Pending</option>
                    <% } else { %>
                    <option value="canceled">Canceled</option>
                    <% } %>

                </select>
            </div>
        </div>

        <h3>Dettagli utente:</h3>
        <div class="info">
            <p><strong>Email:</strong> <%= user.getEmail() %>
            </p>
            <p><strong>Nome:</strong> <%= user.getName() %> <%= user.getSurname() %>
            </p>
            <%if (user.getAddress() != null) {%>
            <p><strong>Indirizzo:</strong> <%= user.getAddress().getId().getStreet() %>
                , <%= user.getAddress().getId().getZipcode() %>, <%= user.getCountryCode() %>
            </p>
            <% } %>
        </div>

        <% for (BoughtFood boughtFood : boughtFoods) {%>
        <div class="foodItem">
            <div>
                <h2><%= boughtFood.getName() %>
                </h2>
                <p><strong>Quantità:</strong> <%= boughtFood.getQuantity() %>
                </p>
                <p><strong>Prezzo:</strong> <%= String.format("%.2f", boughtFood.getPrice()) %>€</p>
                <p><strong>Green Points:</strong> <%= boughtFood.getGreenPoint() %>
                </p>
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
