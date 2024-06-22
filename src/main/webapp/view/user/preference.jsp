<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="../../components/header.jsp"%>
    <title>Savoreco Preferenze</title>
    <script src="../../scripts/coordinate.js"></script>
    <link rel="stylesheet" href="../../assets/styles/preferenceStatus.css">
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
        <form id="form" action="user/preference" method="post" onsubmit="submitRegistration(); return false" onchange="validate()">
            <div class="formPreference">
                <label for="email" id="email_label">Email:</label>
                <input type="email"  value="${sessionScope.user.email}" id="email" name="email" required>

                <label id="password_label" for="password">Nuova Password:</label>
                <input type="password" id="password" name="password" required>

                <label id="check_label" for="check_password">Conferma Password:</label>
                <input type="password" id="check_password" name="confirm-password" required>
            </div>
            <c:if test="${sessionScope.user.address.city != null}">
                <label for="autocomplete">
                    <c:out value="Il tuo indirizzo ${sessionScope.user.address.id.street} ${sessionScope.user.address.city}"/>
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
        <form>

        </form>

    </div>
</main>
<%@include file="../../components/footer.jsp"%>
</body>
</html>
