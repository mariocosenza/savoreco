<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<nav id="topNavBar">
    <a href="../../home">
        <img src="../assets/images/savoreco-logo.webp" alt="Logo Savoreco" class="logoImage">
    </a>
    <a href="home" class="siteLink">
        <em id="bigTextLogo">SAVOR</em><em id="ecoLogoText">ECO</em>
    </a>
    <span class="siteLinkRight">
            <a href="login" class="siteLink">
                <c:catch var="catchException">
                    <c:if test="${account.name != null}" var="test" scope="page">
                        <c:out value="${account.name}"/>
                    </c:if>
                    <c:if test="${!test}">
                        <c:out value="Accedi"/>
                    </c:if>
                </c:catch>
                <c:if test="${catchException}">
                    Accedi
                </c:if>
            </a>
            <a href="Accedi" class="siteLink">
                Aiuto
            </a>

    </span>
</nav>
</html>
