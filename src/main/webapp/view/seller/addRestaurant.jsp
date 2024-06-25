<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione Ristorante</title>
    <link href="../../assets/styles/addRestaurant.css" rel="stylesheet">
    <%@ include file="/components/header.jsp"%>
    <script src="../../scripts/addRestaurant.js"></script>
    <script src="../../scripts/coordinate.js"></script>
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


            <label for="description" id="description_label">Descrizione</label>
            <input type="text" id="description" name="description" placeholder="Inserisci la descrizione" required>

            <label for="deliveryCost" id="deliveryCost_label">Costo di Consegna</label>
            <input type="text" id="deliveryCost" name="deliveryCost" placeholder="Inserisci il costo di consegna" required>

            <label for="category" id="category_label">Categoria</label>
            <input type="text" id="category" name="category" placeholder="Inserisci la categoria" required>

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