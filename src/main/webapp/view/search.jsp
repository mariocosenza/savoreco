<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <c:forEach items="${requestScope.restaurants}" var="resto">
            <div class="result">
                    <img src="${resto.imageObject}" alt="ristorante">
                    <h1><c:out value="${resto.name}"/></h1>
                    <a href="restaurant">
                        <button>
                            Visualizza
                        </button>
                    </a>

            </div>
        </c:forEach>
    </main>
<%@ include file="/components/footer.jsp"%>
</body>
</html>
