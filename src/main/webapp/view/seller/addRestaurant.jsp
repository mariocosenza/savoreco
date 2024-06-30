<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Registrazione Ristorante</title>
    <link href="../../assets/styles/addRestaurant.css" rel="stylesheet">
    <%@ include file="/components/header.jsp" %>
    <script src="../../scripts/addRestaurant.js"></script>
    <script src="../../scripts/coordinate.js"></script>
    <script src="../../scripts/image.js"></script>
</head>
<body>
<a href="home">
    <img src="../assets/images/savoreco-logo.svg" alt="page logo" class="accessLogo">
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
            <input type="text" id="name" name="name" placeholder="Inserisci il tuo nome" maxlength="25" required>

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

            <label for="deliveryCost" id="deliveryCost_label">Costo di Consegna</label>
            <input type="text" id="deliveryCost" name="deliveryCost" placeholder="Inserisci il costo di consegna"
                   maxlength="5" required>

            <label for="category" id="category_label">Categoria</label>
            <input type="text" id="category" name="category" placeholder="Inserisci la categoria" maxlength="25"
                   required>

            <label for="description" id="description_label">Descrizione</label>
            <textarea id="description" name="description" placeholder="Inserisci la descrizione" maxlength="2000"
                      required></textarea>

            <label for="image" id="image_label">Immagine</label>
            <input accept=".png" type="file" id="image" name="image" placeholder="Inserisci la image">
            <input name="imageUrl" id="imageUrl" type="text" style="display: none" hidden="hidden">

            <button disabled>
                Crea ristorante
            </button>
            <p>
                Accetto di fornire informazioni accurate e aggiornate sul mio ristorante,
                inclusi menu e prezzi, e confermo di rispettare le normative sanitarie locali per garantire un servizio di qualità su Savoreco.
            </p>
        </form>
    </div>
</main>
<%@ include file="../../components/footer.jsp" %>
</body>
</html>
