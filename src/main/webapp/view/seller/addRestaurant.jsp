<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione - Savoreco</title>
    <link href="../../assets/styles/addRestaurant.css" rel="stylesheet">
    <%@ include file="/components/header.jsp"%>
    <script src="../../scripts/addRestaurant.js"></script>
    <script src="../../scripts/tooltip.js"></script>
</head>
<body>
<a href="home">
    <img src="../assets/images/savoreco-logo.webp" alt="page logo" class="accessLogo">
</a>
<div class="alert">
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    <strong>Errore!</strong> Dati inseriti errati
</div>
<main>
    <div class="center">
        <form method="post" id="form" onsubmit="submitRegistration(); return false" onchange="validate()">
            <h1>
                Registra il tuo ristorante nella famiglia Savoreco
            </h1>

            <label for="name" id="name_label">Nome</label>
            <input type="text" id="name" name="name" placeholder="Inserisci il tuo nome" required>

            <label for="street" id="street_label">Street</label>
            <input type="text" id="street" name="street" placeholder="Inserisci il tuo street" required>

            <label for="zipcode" id="zipcode_label">Zipcode</label>
            <input type="text" id="zipcode" name="zipcode" placeholder="Inserisci il tuo zipcode" required>

            <label for="city" id="city_label">City</label>
            <input type="text" id="city" name="city" placeholder="Inserisci il tuo city" required>

            <label for="countryCode" id="countryCode_label">CountryCode</label>
            <input type="text" id="countryCode" name="countryCode" placeholder="Inserisci il tuo countryCode" required>

            <label for="description" id="description_label">Description</label>
            <input type="text" id="description" name="description" placeholder="Inserisci il tuo description" required>

            <label for="deliveryCost" id="deliveryCost_label">DeliveryCost</label>
            <input type="text" id="deliveryCost" name="deliveryCost" placeholder="Inserisci il tuo deliveryCost" required>

            <label for="category" id="category_label">Category</label>
            <input type="text" id="category" name="category" placeholder="Inserisci il tuo category" required>

            <button disabled>
                Crea ristorante
            </button>
            <p>
                Condizioni
            </p>
        </form>
    </div>
</main>
<%@ include file="../../components/footer.jsp"%>
</body>
</html>
