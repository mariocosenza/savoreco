<%@ page import="org.apache.lucene.util.SloppyMath" %>
<%@ page import="it.savoreco.model.entity.Restaurant" %>
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
    <search>
        <form action="search" method="get">
            <input inputmode="search" autocomplete="off" placeholder="Inserisci nome del ristorante" name="byName" type="search"
            <c:if test="${param.byName != null}">
                   value="<c:out value="${param.byName}"/>"
            </c:if>>
            <button class="searchLogo">
                <img src="../assets/icons/search-svgrepo-com.svg" alt="searchLogo">
            </button>
            <input style="display: none" name="lat" value="${param.lat}">
            <input style="display: none" name="lon" value="${param.lon}">
        </form>
    </search>
    <div class="mobileSelector">
        <label for="selectSearch">
            Scegli categoria ristorante <!--Per motivi di sicurezza non usare id che iniziano con searchCategory-->
        </label>
        <select id="selectSearch" class="mobile classic" onclick=toggleSelect(this)>
            <option value="noCategory" selected>
                Nessun Filtro
            </option>
            <c:forEach items="${requestScope.restaurants.stream().map(r -> r.category).sorted().distinct().toList()}" var="category">
                <option value="${category.replaceAll("\\s+","")}">
                         <c:out value="${category}"/>
                </option>
            </c:forEach>
        </select>
    </div>
    <main>
        <aside>
            <h1>Seleziona categoria</h1>
            <c:forEach items="${requestScope.restaurants.stream().map(r -> r.category).sorted().distinct().toList()}" var="category">
                <div class="filter" id='searchCategory${category.replaceAll("\\s+","")}' style="border-color: var(--md-sys-color-secondary-container)" onclick="toggleCategory(this,'${category.replaceAll("\\s+","")}')">
                        <c:out value="${category}"/>
                </div>
            </c:forEach>
                <form action="search" method="get" onchange="this.submit()">
                <label>
                    Ordina risultati
                </label>
                        <select name="sort" class="classic">
                            <option <c:if test="${param.sort == 'distance' or param.sort == null}">
                                selected
                            </c:if> value="distance">
                                Rage di 10 km
                            </option>
                            <option <c:if test="${param.sort == 'name'}">
                                selected
                            </c:if> value="name">
                                A => Z
                            </option>
                            <option <c:if test="${param.sort == 'deliveryPrice'}">
                                selected
                            </c:if> value="deliveryPrice">
                                Costo consegna
                            </option>
                        </select>
                    <input style="display: none" name="lat" value="${param.lat}">
                    <input style="display: none" name="lon" value="${param.lon}">
                </form>
        </aside>

        <div class="allResult">
            <c:choose>
                <c:when test="${requestScope.restaurants.size() > 0}">
                    <c:forEach items="${requestScope.restaurants}" var="resto">
                        <div class="result resultCategory${resto.category.replaceAll("\\s+","")}">
                            <img class="resultImage" src="${resto.imageObject}" alt="ristorante">
                            <div class="infoResto">
                                <h1><c:out value="${resto.name}"/></h1>
                                <h2><img class="svgLogo" src="../assets/icons/placeholder-pin-svgrepo-com.svg" alt="Distanza"> <fmt:formatNumber value='<%=SloppyMath.haversinMeters(Double.parseDouble(request.getParameter("lat")), Double.parseDouble(request.getParameter("lon")),
                                        ((Restaurant)   pageContext.getAttribute("resto")).getAddress().getLat(), ((Restaurant)   pageContext.getAttribute("resto")).getAddress().getLon())/1000%>' pattern="#.#"/> km</h2>
                                <h2><c:out value="${resto.category}"/></h2>
                                <h2 class="price">Costo consegna <fmt:formatNumber value="${resto.deliveryCost}" pattern="#.#"/> €</h2>
                                <a href="restaurant?id=${resto.id}">
                                    <button>
                                        Visualizza
                                    </button>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h1>Nessun risultato</h1>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
    <c:if test="${requestScope.maxResult > 0}">
        <form action="search" method="get">
            <button name="maxResult" class="showMoreButton" value="${requestScope.maxResult}">
                Mostra di più
            </button>
            <input style="display: none" name="lat" value="${param.lat}">
            <input style="display: none" name="lon" value="${param.lon}">
        </form>
    </c:if>
<%@ include file="/components/footer.jsp"%>
</body>
</html>
