<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <%@ include file="../../components/header.jsp"%>
    <link rel="stylesheet" href="../../assets/styles/purchase.css">
    <title>Conferma di Pagamento</title>
    <script src="../../scripts/purchase.js"></script>
</head>
<body class="confirmed" onload="countdown();">
<c:choose>
    <c:when test="${requestScope.confirmed}">
        <h1>Pagamento Confermato</h1>
        <div class="checkmark"></div>
        <p>Il tuo pagamento è stato processato con successo.</p>
    </c:when>
    <c:otherwise>
        <h1>Errore di Pagamento</h1>
        <div class="error-mark"></div>
        <p>C'è stato un problema con il tuo pagamento. Per favore, riprova.</p>
    </c:otherwise>
</c:choose>
<div class="countdown">
    Reindirizzamento alla home page in <span id="counter">0:10</span> secondi.
</div>
</body>
</html>

