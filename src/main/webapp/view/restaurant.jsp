<%@ page import="it.savoreco.model.entity.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.savoreco.model.entity.Restaurant" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%
    @SuppressWarnings("unchecked") List<Food> foodList = (List<Food>) request.getAttribute("foodList");
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
    if ((foodList == null) || (restaurant == null)) {
        response.sendRedirect("./home.jsp");
        return;
    }%>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="/components/header.jsp" %>
    <title>Ristorante</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/restaurant.css">
    <script>window.isLoggedIn = <%=request.getSession().getAttribute("user") != null%>;</script>
    <script src="../scripts/restaurant.js"></script>
    <script src="../scripts/animation.js"></script>

</head>
<body>
<jsp:include page="../components/navbar.jsp"/>
<main>
    <div class="restaurantBox">
        <div class="restaurantInfo">
            <img src="<%= restaurant.getImageObject()%>" alt="logo" class="restaurantImage">
            <div>
                <h1><%= restaurant.getName() %>
                </h1>
                <p><strong>Indirizzo: </strong> <%= restaurant.getAddress().getId().getStreet() %>
                    , <%= restaurant.getAddress().getId().getZipcode() %>
                </p>
                <p><strong>Categoria: </strong> <%= restaurant.getCategory() %>
                </p>
                <p><strong>Descrizione: </strong> <%= restaurant.getDescription() %>
                </p>
                <p><strong>Costo di Consegna: </strong> <%= String.format("%.2f", restaurant.getDeliveryCost())%>€</p>
            </div>
        </div>

        <%
            Map<String, List<Food>> categorizedFood = new HashMap<>();
            for (Food food : foodList) {
                categorizedFood.computeIfAbsent(food.getCategory(), k -> new ArrayList<>()).add(food);
            }

            for (Map.Entry<String, List<Food>> entry : categorizedFood.entrySet()) {
                String category = entry.getKey();
                List<Food> foods = entry.getValue();
        %>
        <div class="categoryBox">
            <h2><%= category %>
            </h2>
            <div class="categoryContent">
                <% for (Food food : foods) {
                    if (food.getAvailable()) {%>
                <div class="foodBox">
                    <img class="foodImage" src="<%= food.getImageObject() %>" alt="<%= food.getName() %>">
                    <div class="foodInfo">
                        <h3><%= food.getName() %>
                        </h3>
                        <p><%= food.getDescription() %>
                        </p>
                        <p><strong>Prezzo: </strong><%= String.format("%.2f", food.getPrice()) %> €</p>
                        <p><strong>Green Points: </strong> <%= food.getGreenPoint() %>
                        </p>
                        <p><strong>Allergeni: </strong> <%= food.getAllergens() %>
                        </p>
                    </div>

                    <div class="tooltipDiv">
                        <span class="tooltiptext">Devi essere registrato per aggiungere il prodotto al carrello</span>
                        <button class="add-to-cart" data-food-id="<%= food.getId() %>"> Aggiungi al carrello</button>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%
            }
        %>
        <h1>Ecco dove siamo</h1>
        <div id="map" class="map">
        </div>
        <script>
            initializeMap(<%=restaurant.getAddress().getLon()%>, <%=restaurant.getAddress().getLat()%>)
        </script>
    </div>
</main>
<%@ include file="../components/footer.jsp" %>
</body>
</html>
