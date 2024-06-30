<%@ page import="it.savoreco.model.entity.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page import="it.savoreco.model.entity.Food" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    @SuppressWarnings("unchecked") List<Food> products = (List<Food>) request.getAttribute("products");
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
    String totalCost = (String) request.getAttribute("totalCost");
    if ((products == null) || (restaurant == null) || (totalCost == null)) {
        response.sendRedirect("./home.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Ristorante</title>
    <%@ include file="/components/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../assets/styles/sellerRestaurant.css">
    <script src="../../scripts/coordinate.js"></script>
    <script src="../../scripts/sellerRestaurant.js"></script>
    <script src="../../scripts/image.js"></script>
</head>
<body>
<%@ include file="../../components/navbar.jsp" %>
<main>
    <h1 class="center"><strong>Benvenuto a <%= restaurant.getName()%>, gestisci qui la tua attività:</strong></h1>
    <div class="main-container">
        <div class="products-box">
            <h3 class="center"><strong>GESTISCI I TUOI PRODOTTI</strong></h3>
            <div>
                <%for (Food food : products) {%>
                <form method="post" id="form<%=food.getId()%>"
                      onsubmit="submitFoodUpdate(<%= food.getId() %>); return false"
                      onchange="validateFood(<%= food.getId() %>)">
                    <div class="foodBox">
                        <%if (food.getId() != null) {%>
                        <div class="attribute">
                            <img class="foodImage" src="<%= food.getImageObject() %>" alt="<%= food.getName() %>">
                        </div>
                        <% } %>
                        <div class="attribute">
                            <label for="fname" id="fname_label">Nome</label>
                            <input type="text" id="fname" name="fname" placeholder="Inserisci il Nome"
                                   value="<%= food.getName()%>" maxlength="25" required>
                        </div>
                        <div class="attribute">
                            <label for="fdescription" id="fdescription_label">Descrizione</label>
                            <textarea id="fdescription" name="fdescription" placeholder="Inserisci la Descrizione"
                                      maxlength="2000" required><%= food.getDescription()%></textarea>
                        </div>
                        <div class="attribute">
                            <label for="fcategory" id="fcategory_label">Categoria</label>
                            <input type="text" id="fcategory" name="fcategory" placeholder="Inserisci il category"
                                   value="<%= food.getCategory()%>" maxlength="25" required>
                        </div>
                        <div class="attribute">
                            <label for="price" id="price_label">Prezzo</label>
                            <input type="text" id="price" name="price" placeholder="Inserisci il Prezzo"
                                   value="<%=String.format("%.2f", food.getPrice())%>" maxlength="5" required>
                        </div>
                        <div class="attribute">
                            <label for="allergens" id="allergens_label">Allergeni</label>
                            <input type="text" id="allergens" name="allergens" placeholder="Inserisci gli Allergeni"
                                   value="<%= food.getAllergens()%>" maxlength="50" required>
                        </div>
                        <div class="attribute">
                            <label for="greenPoints" id="greenPoints_label">Green Points</label>
                            <input type="text" id="greenPoints" name="greenPoints"
                                   placeholder="Inserisci i GreenPoints" value="<%= food.getGreenPoint()%>"
                                   maxlength="2" required>
                        </div>
                        <div class="attribute">
                            <label for="quantity" id="quantity_label">Quantità</label>
                            <input type="text" id="quantity" name="quantity"
                                   placeholder="Inserisci la quantity" value="<%= food.getQuantity()%>"
                                   maxlength="5" required>
                        </div>
                        <div class="attribute">
                            <label for="image" id="image_label">Immagine</label>
                            <input accept=".png" type="file" id="image" name="image" placeholder="Inserisci la image">
                            <input name="imageUrl" id="imageUrl" type="text" value="<%= food.getImageObject()%>"
                                   style="display: none" hidden="hidden">
                        </div>
                        <%if (food.getId() != null) {%>
                        <div class="attribute">
                            <button type="submit" class="save" disabled>Salva</button>
                        </div>
                        <div class="attribute">
                            <button type="button" class="delete" onclick="deleteFood(<%= food.getId() %>)">Rimuovi
                            </button>
                        </div>
                        <% } else {%>
                        <div class="attribute">
                            <button type="submit" class="save" disabled>Aggiungi</button>
                        </div>
                        <% } %>
                    </div>
                </form>
                <% } %>
            </div>
        </div>

        <div class="main-box">
            <h3 class="center"><strong>GESTISCI LA TUA ATTIVITÀ</strong></h3>
            <form method="post" id="formRest" onsubmit="submitRestaurantUpdate(); return false"
                  onchange="validateRestaurant()">
                <div>
                    <div class="attribute">
                        <img class="foodImage" src="<%= restaurant.getImageObject() %>"
                             alt="<%= restaurant.getName() %>">
                    </div>
                    <div class="attribute">
                        <label for="name" id="name_label">Nome</label>
                        <input type="text" id="name" name="name" placeholder="Inserisci il Nome"
                               value="<%= restaurant.getName()%>" maxlength="25" required>
                    </div>
                    <div class="attribute">
                        <label for="description" id="description_label">Descrizione</label>
                        <textarea id="description" name="description" placeholder="Inserisci la Descrizione"
                                  maxlength="2000" required><%= restaurant.getDescription()%></textarea>
                    </div>
                    <div class="attribute">
                        <label for="category" id="category_label">Categoria</label>
                        <input type="text" id="category" name="category" placeholder="Inserisci la Categoria"
                               value="<%= restaurant.getCategory()%>" maxlength="25" required>
                    </div>
                    <div class="attribute">
                        <label for="deliveryCost" id="deliveryCost_label">Costo di Consegna</label>
                        <input type="text" id="deliveryCost" name="deliveryCost"
                               placeholder="Inserisci il Costo di Consegna"
                               value="<%=String.format("%,.2f",restaurant.getDeliveryCost())%>" maxlength="5" required>
                    </div>
                    <div class="attribute">
                        <label for="autocomplete">Indirizzo</label>
                        <search>
                            <div id="autocomplete">
                            </div>
                            <script>
                                autocomplete()
                            </script>
                        </search>
                        <input name="lat" id="lat" type="text" style="display: none" hidden="hidden">
                        <input name="lon" id="lon" type="text" style="display: none" hidden="hidden">
                        <input name="postal" id="postal" type="text" style="display: none" hidden="hidden">
                        <input name="address" id="address" type="text" style="display: none" hidden="hidden">
                        <input name="city" id="city" type="text" style="display: none" hidden="hidden">
                    </div>
                    <div class="attribute">
                        <label for="logo" id="logo_label">Immagine</label>
                        <input accept=".png" type="file" id="logo" name="logo" placeholder="Inserisci la image">
                        <input name="logoUrl" id="logoUrl" type="text" value="<%= restaurant.getImageObject()%>"
                               style="display: none" hidden="hidden">
                    </div>
                    <button class="modify" disabled>Salva le Modifiche</button>
                </div>
            </form>
            <p><strong>GUADAGNO TOTALE:</strong> <%=totalCost%>
            </p>
        </div>
    </div>
</main>
<%@include file="../../components/footer.jsp" %>
</body>
</html>
