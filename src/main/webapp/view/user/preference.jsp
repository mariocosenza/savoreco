<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="../../components/header.jsp" %>
    <title>Savoreco Preferenze</title>
    <script src="../../scripts/coordinate.js"></script>
    <link rel="stylesheet" href="../../assets/styles/preference.css">
    <script src="../../scripts/userPreference.js"></script>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<div class="alert">
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    <strong id="textAlert">Errore nell'aggiornamento </strong>
</div>
<main>
    <div class="form-container">
        <h3>Aggiorna le informazioni del tuo account</h3>
        <form id="form" action="user/preference" method="post" onsubmit="submitRegistration(); return false"
              onchange="validate()">
            <div class="formPreference">
                <label for="email" id="email_label">Email: ${sessionScope.user.email}</label>
                <input type="email" inputmode="email" value="" id="email" name="email"
                       placeholder="Inserisci la tua nuova email">

                <label id="old_password_label" for="old_password">Vecchia Password</label>
                <input type="password" value="" id="old_password" name="old_password"
                       placeholder="Inserisci vecchia password">

                <label id="password_label" for="password">Nuova Password</label>
                <input type="password" value="" id="password" name="password" placeholder="Inserisci nuova password">

                <label id="check_label" for="check_password">Conferma Password</label>
                <input type="password" value="" id="check_password" name="confirm-password"
                       placeholder="Conferma password">
            </div>
            <h2>Puoi modificare anche solo il tuo indirizzo</h2>
            <c:if test="${sessionScope.user.address.city != null}">
                <label for="autocomplete" id="labelAutocomplete">
                    <c:out value="Il tuo indirizzo ${sessionScope.user.address.id.street}, ${sessionScope.user.address.city}"/>
                </label>
            </c:if>
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
            <button disabled>
                Aggiorna Account
            </button>
        </form>
    </div>
</main>
<div class="wrapper" onclick="noScroll()">
    <a href="#delete-modal">Elimina Account</a>
</div>

<div id="delete-modal" class="modal">
    <div class="modal__content">
        <h1>Conferma cancellazione account</h1>
        <p>
            Il tuo account verrà eliminato potrai ripristinarlo entro 7 giorni<br>
            Invia una email a <a href="mailto:info@savoreco.it">
            info@savoreco.it </a>
        </p>
        <form action="../user/preference" method="post">
            <button class="deleteUser" onclick="" name="delete" value="true">
                Conferma
            </button>
        </form>

        <a href="#" onclick="revertScroll()" class="modal__close">&times;</a>
    </div>
</div>
<%@include file="../../components/footer.jsp" %>
</body>
</html>
