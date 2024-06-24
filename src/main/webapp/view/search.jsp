<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="/components/header.jsp"%>
    <link href="../assets/styles/search.css" rel="stylesheet">
    <script src="../scripts/search.js"></script>
    <title>Ristoranti in zona</title>
</head>
<body>
    <jsp:include page="../components/navbar.jsp"/>
    <div class="mobileSelector">
        <label>
            Scegli categoria ristorante <!--Per motivi di sicurezza non usare id di elementi html-->
        </label>
        <select class="mobile classic" onclick=toggleSelect(this)>
            <c:forEach items="${requestScope.restaurants.stream().map(r -> r.category).sorted().distinct().toList()}" var="category">
                <option value="${category.replaceAll("\\s+","")}">
                        ${category}
                </option>
            </c:forEach>
        </select>
    </div>
    <main>
        <aside>
            <h1>Seleziona categoria</h1>
            <c:forEach items="${requestScope.restaurants.stream().map(r -> r.category).sorted().distinct().toList()}" var="category">
                <div class="filter" id="${category.replaceAll("\\s+","")}" style="border-color: var(--md-sys-color-secondary-container)" onclick="toggleCategory(this,'${category.replaceAll("\\s+","")}')">
                        ${category}
                </div>
            </c:forEach>
                <form action="search" method="get" onchange="this.submit()">
                <label>
                    Ordina risultati
                </label>
                        <select name="sort" class="classic">
                            <option disabled selected value> -- Seleziona un opzione -- </option>
                            <option  value="distance">
                                Rage di 30km
                            </option>
                            <option  value="name">
                                Nome
                            </option>
                            <option  value="deliveryPrice">
                                Costo consegna
                            </option>
                        </select>
                    <input style="display: none" name="lat" value="${requestScope.lat}">
                    <input style="display: none" name="lon" value="${requestScope.lon}">
                </form>
        </aside>
        <div class="allResult">
            <c:forEach items="${requestScope.restaurants}" var="resto">
                <div class="result resultCategory${resto.category.replaceAll("\\s+","")}">
                    <img class="resultImage" src="${resto.imageObject}" alt="ristorante">
                    <div class="infoResto">
                        <h1><c:out value="${resto.name}"/></h1>
                        <h2>Costo consegna <fmt:formatNumber value="${resto.deliveryCost}" pattern="#.#"/>€</h2>
                        <h2><c:out value="${resto.category}"/></h2>
                        <a href="restaurant?id=${resto.id}">
                            <button>
                                Visualizza
                            </button>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
    <c:if test="${requestScope.maxResult > 0}">
        <form action="search" method="get">
            <button name="maxResult" class="showMoreButton" value="${requestScope.maxResult}">
                Mostra di più
            </button>
            <input style="display: none" name="lat" value="${requestScope.lat}">
            <input style="display: none" name="lon" value="${requestScope.lon}">
        </form>
    </c:if>
<%@ include file="/components/footer.jsp"%>
</body>
</html>
