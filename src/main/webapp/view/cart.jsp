<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="../components/header.jsp"%>
    <title>Carrello</title>
    <link rel="stylesheet" href="../assets/styles/cart.css">
</head>
<body>
<jsp:include page="../components/navbar.jsp"/>
<main>
    <c:choose>
        <c:when test="${requestScope.noItem = true}">
            <div class="empty">
                <img src="../assets/images/shopping-cart-logo.webp" alt="Cart logo">
                <h1>
                    Il tuo carrello è vuoto
                </h1>
                <form action="home" method="get">
                    <button>Torna Alla Home</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <form action="user/payment" method="get">
                <div>
                    <c:choose>
                        <c:when test="${sessionScope.logged == null  || sessionScope.user == null}">
                            <button>
                                Accedi e acquista
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button>
                                Procedi al checkout
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </c:otherwise>
    </c:choose>


</main>
<%@include file="../components/footer.jsp"%>
</body>
</html>
