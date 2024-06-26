<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="../../components/header.jsp" %>
    <title>Pagamento</title>
    <link rel="stylesheet" href="../../assets/styles/cart.css">
    <script src="../../scripts/purchase.js"></script>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<main>
    <script>
        let cost = parseFloat(${requestScope.deliveryCost + requestScope.tot}).toFixed(2) //pagamenti non gestiti
    </script>
    <h1 class="orderTitle">Riepilogo</h1>
    <ol>
        <c:forEach items="${requestScope.basketList}" var="item">
            <li>
                <div class="summaryPayment">
                    <h1>
                        <c:out value="${item.food.name}"/>
                        <fmt:formatNumber value="${item.food.price}" pattern="#.#"/> €
                    </h1>
                    <h2>
                        <c:out value="${item.quantity}"/>
                        <c:choose>
                            <c:when test="${item.quantity <= 1}">
                                pezzo
                            </c:when>
                            <c:otherwise>
                                pezzi
                            </c:otherwise>
                        </c:choose>
                    </h2>
                </div>
            </li>
        </c:forEach>
    </ol>
    <div class="rightCost">
        <h1>Costo spedizione: <fmt:formatNumber value="${requestScope.deliveryCost}" pattern="#.##"/>€ </h1><br>
        <h1>Totale costo: <fmt:formatNumber value="${requestScope.tot}" pattern="#.##"/>€</h1>
        <h2>Iva 10%</h2>
    </div>
    <form id="form" action="purchase" method="post" enctype="application/x-www-form-urlencoded">
        <div class="delivery">
            <c:choose>
                <c:when test="${sessionScope.user.address != null}">
                    <label for="pick">
                        Ritira il tuo ordine
                    </label>
                    <input id="pick" value="true" name="pick_up" type="radio" checked>
                    <label for="noPick"><br>
                        Spedisci il tuo ordine a ${sessionScope.user.address.id.street}
                    </label>
                    <input id="noPick" value="false" name="pick_up" type="radio">
                </c:when>
                <c:otherwise>
                    <label for="pick">
                        Ritira
                    </label>
                    <input id="pick" type="radio" name="pick_up" value="true" checked readonly>
                    <h2>Per spedire il tuo ordine aggiungi un indirizzo nelle preferenze dell'account</h2>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="container"></div>
        <script async
                src="https://pay.google.com/gp/p/js/pay.js"
                onload="onGooglePayLoaded()">
        </script>
        <input name="auth" type="text" style="display: none; visibility: hidden" value="${requestScope.auth}">
    </form>
</main>
<%@include file="../../components/footer.jsp" %>
</body>
</html>
