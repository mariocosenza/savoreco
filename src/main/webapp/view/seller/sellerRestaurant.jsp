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
                <form method="post" id="form" onsubmit="submitRegistration(); return false" onchange="validate()">
                    <div class="foodBox">
                        <div class="attribute">
                            <label for="description" id="description_label">Descrizione</label>
                            <textarea id="description" name="description" placeholder="Inserisci la Descrizione"
                                      maxlength="2000" required><%= food.getDescription()%></textarea>
                        </div>
                        <div class="attribute">
                            <label for="fname" id="fname_label">Nome</label>
                            <input type="text" id="fname" name="fname" placeholder="Inserisci il Nome"
                                   value="<%= food.getName()%>" maxlength="25" required>
                        </div>
                        <div class="attribute">
                            <label for="price" id="price_label">Prezzo</label>
                            <input type="text" id="price" name="price" placeholder="Inserisci il Prezzo"
                                   value="<%= food.getPrice()%>" maxlength="5" required>
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
                            <button>Salva</button>
                        </div>
                        <div class="attribute">
                            <button>Rimuovi</button>
                        </div>
                    </div>
                </form>
                <% } %>
                <form method="post" id="form2" onsubmit="submitRegistration(); return false" onchange="validate()">
                    <div class="foodBox">
                        <div class="attribute">
                            <label for="description2" id="description2_label">Descrizione</label>
                            <textarea id="description2" name="description2" placeholder="Inserisci la Descrizione"
                                      maxlength="2000" required></textarea>
                        </div>
                        <div class="attribute">
                            <label for="fname2" id="fname2_label">Nome</label>
                            <input type="text" id="fname2" name="fname2" placeholder="Inserisci il Nome" maxlength="25"
                                   required>
                        </div>
                        <div class="attribute">
                            <label for="price2" id="price2_label">Prezzo</label>
                            <input type="text" id="price2" name="price2" placeholder="Inserisci il Prezzo" maxlength="5"
                                   required>
                        </div>
                        <div class="attribute">
                            <label for="allergens2" id="allergens2_label">Allergeni</label>
                            <input type="text" id="allergens2" name="allergens2" placeholder="Inserisci gli Allergeni"
                                   maxlength="50" required>
                        </div>
                        <div class="attribute">
                            <label for="greenPoints2" id="greenPoints2_label">Green Points</label>
                            <input type="text" id="greenPoints2" name="greenPoints2"
                                   placeholder="Inserisci i GreenPoints" maxlength="2" required>
                        </div>
                        <div class="attribute">
                            <button>Aggiungi Cibo</button>
                        </div>
                    </div>
                </form>
            </div>
            <button class="check-out-button" type="submit">SALVA LE MODIFICHE</button>
        </div>

        <div class="main-box">
            <h3 class="center"><strong>GESTISCI LA TUA ATTIVITÀ</strong></h3>
            <div>
                <div class="attribute">
                    <label for="name" id="name_label">Nome</label>
                    <input type="text" id="name" name="name" placeholder="Inserisci il Nome"
                           value="<%= restaurant.getCategory()%>" maxlength="25" required>
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
                    <label for="category" id="category_label">Categoria</label>
                    <input type="text" id="category" name="category" placeholder="Inserisci la Categoria"
                           value="<%= restaurant.getCategory()%>" maxlength="25" required>
                </div>
                <div class="attribute">
                    <label for="deliveryCost" id="deliveryCost_label">Costo di Consegna</label>
                    <input type="text" id="deliveryCost" name="deliveryCost"
                           placeholder="Inserisci il Costo di Consegna"
                           value="<%= restaurant.getDeliveryCost()%>" maxlength="5" required>
                </div>
                <button class="check-out-button" type="submit">SALVA LE MODIFICHE</button>
            </div>
            <p><strong>GUADAGNO TOTALE:</strong> <%=totalCost%></p>
        </div>
    </div>
</main>
<%@include file="../../components/footer.jsp" %>
</body>
</html>
