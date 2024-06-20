<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accedi - Savoreco</title>
    <link href="../assets/styles/access.css" rel="stylesheet">
    <script src="../scripts/login.js"></script>
    <%@ include file="/components/header.jsp"%>
</head>
<body>
<a href="home">
    <img src="../assets/images/savoreco-logo.webp" alt="page logo" class="accessLogo">
</a>
<div class="alert">
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    <strong>Errore!</strong> Email o password errati
</div>
<c:if test="${error}">
    <script>
        document.querySelector(".alert").style.visibility = "visible"
    </script>
</c:if>
<main>
    <div class="centerLogin">
        <form method="post" id="form" action="login" onchange="validate()">
            <h1>
                Accedi a Savoreco
            </h1>
            <div>

                <label for="user">Sei un utente?</label>
                <input type="radio" id="user" name="profile_type" value="user" checked>
                <label for="seller">Sei un venditore?</label>
                <input type="radio" id="seller" name="profile_type" value="seller">
            </div>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Inserisci la tua email" required>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Inserisci la tua password" required onfocusin="showTooltip()" onfocusout="hideTooltip()">

            <button disabled>
                Accedi
            </button>
            <p>
                Prima volta qui? <a href="registration"> Crea un account</a>
            </p>
            <p>
                Condizioni
            </p>
        </form>
    </div>
</main>

<%@ include file="../components/footer.jsp" %>
</body>
</html>
