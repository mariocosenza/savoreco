<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="../../components/header.jsp" %>
    <title>Carrello</title>
    <link rel="stylesheet" href="../../assets/styles/cart.css">
    <script src="../../scripts/cart.js"></script>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<main>
    <c:choose>
        <c:when test="${requestScope.noItem <= 1 || requestScope.noItem == null}">
            <div class="empty">
                <img src="../../assets/images/shopping-cart-logo.webp" alt="Cart logo">
                <h1 class="empty">
                    Il tuo carrello è vuoto
                </h1>
                <form action="<c:url value="/home"/>" method="get" class="empty">
                    <button class="empty">Torna alla Home</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <h1 class="orderTitle">Il tuo ordine</h1>
            <ol>
                <c:forEach items="${requestScope.basketList}" var="item">
                    <li>
                        <div>
                            <div>
                                <h1>
                                    <c:out value="${item.food.name}"/>
                                    <fmt:formatNumber value="${item.food.price}" pattern="#.#"/> €
                                    <svg class="delete" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 27 26"
                                         fill="none" onclick="deleteProduct(this, '<c:out value="${item.food.id}"/>', ${item.food.price * item.quantity}); return false">
                                        <path d="M8.01465 18.4197L13.2303 13.0506L18.446 7.68152" stroke="#2F6A43"
                                              stroke-width="1.67038" stroke-linecap="round" stroke-linejoin="round"></path>
                                        <path d="M8.01465 7.68162L13.2303 13.0507L18.446 18.4198" stroke="#2F6A43"
                                              stroke-width="1.67038" stroke-linecap="round" stroke-linejoin="round"></path>
                                        <path d="M13.2646 24.8626C19.854 24.8626 25.1959 19.5208 25.1959 12.9313C25.1959 6.34183 19.854 1 13.2646 1C6.67508 1 1.33325 6.34183 1.33325 12.9313C1.33325 19.5208 6.67508 24.8626 13.2646 24.8626Z"
                                              stroke="#2F6A43" stroke-width="1.67038" stroke-linecap="round"
                                              stroke-linejoin="round"></path>
                                    </svg>
                                </h1>
                            </div>
                            <div>
                                <h2>
                                    <strong class="piece"><c:out value="${item.quantity}"/></strong>
                                        <c:choose>
                                            <c:when test="${item.quantity <= 1}">
                                                &nbsp pezzo
                                            </c:when>
                                            <c:otherwise>
                                                &nbsp pezzi
                                            </c:otherwise>
                                        </c:choose>
                                    <svg class="addIcon" xmlns="http://www.w3.org/2000/svg" width="2em" height="2em"
                                         viewBox="0 0 27 26" fill="none" onclick="addProduct(this, '${item.food.id}', '${item.food.price}'); return false">
                                        <path d="M5.56752 9.79568C7.50809 4.59322 13.0304 5.00778 13.0304 5.00778C13.0304 5.00778 18.555 4.59322 20.4933 9.79568C21.2522 11.8491 21.2522 14.1138 20.4933 16.1672C18.555 21.3674 13.0304 20.9552 13.0304 20.9552C13.0304 20.9552 7.50809 21.3673 5.56752 16.1672C4.81087 14.1133 4.81087 11.8496 5.56752 9.79568Z"
                                              stroke="#2F6A43" stroke-linecap="round" stroke-linejoin="round"></path>
                                        <path d="M13.2307 10V16.5281M16.4574 13.2255H10" stroke="#2F6A43"
                                              stroke-linecap="round" stroke-linejoin="round"></path>
                                    </svg>
                                </h2>

                            </div>

                        </div>
                    </li>
                </c:forEach>
            </ol>

            <h1>Totale costo senza tasse <strong id="price"><fmt:formatNumber value="${requestScope.tot}" pattern="#.##"/></strong>  <strong>€</strong></h1>
            <form action="<c:url value="/user/purchase"/>" method="get">
                <div>
                    <button>
                        Procedi al checkout
                    </button>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</main>
<%@include file="../../components/footer.jsp" %>
</body>
</html>
