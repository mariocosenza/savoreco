<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="../../components/header.jsp" %>
    <title>Punti Eco</title>
    <link rel="stylesheet" href="../../assets/styles/ecopoint.css">
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<main>
    <div class="progressDiv">
        <div class="content">
            <h1>Livello <c:out value="${requestScope.level}"/></h1>
            <h2>
                <c:choose>
                    <c:when test="${(requestScope.maxEcoPoint - requestScope.ecoPoint) != 0}">
                        Ti mancano <c:out value="${requestScope.maxEcoPoint - requestScope.ecoPoint + 1}"/>  punti
                    </c:when>
                    <c:otherwise>
                        Ti manca <c:out value="${requestScope.maxEcoPoint - requestScope.ecoPoint + 1}"/> punto
                    </c:otherwise>
                </c:choose>
                al prossimo livello
            </h2>
        </div>
        <progress max="${requestScope.maxEcoPoint + 1}" value="${requestScope.ecoPoint}"></progress>
    </div>
    <p class="paragrafo-stilizzato">
        La sostenibilità non è solo una scelta, è uno stile di vita. <br>
        Con Savoreco, ogni pasto diventa un'opportunità per fare la differenza.  <br>
        Unisciti a noi nell'adozione di abitudini alimentari che rispettano il nostro pianeta.
    </p>
</main>
<%@include file="../../components/footer.jsp"%>
</body>
</html>
