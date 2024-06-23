<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="/components/header.jsp"%>
    <link href="../assets/styles/search.css" rel="stylesheet">
    <title>Ristoranti in zona</title>
</head>
<body>
    <jsp:include page="../components/navbar.jsp"/>
    <div>

    </div>
    <aside>
        <label>
            Italiano
            <input id="italian" type="checkbox"/>
        </label>
        <label>
            Giapponese
            <input id="giappo" type="checkbox"/>
        </label>
        <label>
            Dolci
            <input id="sweet" type="checkbox"/>
        </label>
    </aside>
    <main>
        <div class="allResult">
            <c:forEach items="${requestScope.restaurants}" var="resto">
                <div class="result">
                    <img src="${resto.imageObject}" alt="ristorante">
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
<%@ include file="/components/footer.jsp"%>
</body>
</html>
