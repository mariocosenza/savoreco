<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<nav id="topNavBar">
    <script src="../components/navbar.jsp"></script>
    <a href="../../home">
        <img src="../assets/images/savoreco-logo.webp" alt="Logo Savoreco" class="logoImage">
    </a>
    <a href="home" class="siteLink">
        <em id="bigTextLogo">SAVOR</em><em id="ecoLogoText">ECO</em>
    </a>
    <span class="siteLinkRight">
            <a href="login" class="siteLink">
                <c:catch var="catchException">
                    <c:if test="${param.account.name != null}" var="test" scope="page">
                        <c:out value="${param.account.name}"/>
                    </c:if>
                    <c:if test="${!test}">
                        <c:out value="Accedi"/>
                    </c:if>
                </c:catch>
                <c:if test="${catchException}">
                    Accedi
                </c:if>
            </a>
            <a href="help" class="siteLink">
                Aiuto
            </a>

    </span>
</nav>
</html>
