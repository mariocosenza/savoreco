<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <link href="assets/styles/global.css" rel="stylesheet">
    <%@include file="WEB-INF/components/header.jsp" %>
    <title>Savoreco</title>
</head>
<body>
    <jsp:include page="WEB-INF/components/navbar.jsp"></jsp:include>
<h1>
    Hello World
</h1>
    <div class="cardCentral">
        <search>
            <form role="search" action="" method="post">
                <label>
                    <input id="searchBarHome" class="centralSearch" type="search" placeholder="Inserisci la via del ristorante">
                </label>
            </form>
        </search>
    </div>
</body>
</html>