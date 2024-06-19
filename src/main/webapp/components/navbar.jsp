<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="it">
<nav id="topNavBar">
    <script src="../scripts/navbar.js"></script>
    <a href="../../home">
        <img src="../assets/images/savoreco-logo.webp" alt="Logo Savoreco" class="logoImage">
    </a>
    <a href="home" class="siteLink">
        <em id="bigTextLogo">SAVOR</em><em id="ecoLogoText">ECO</em>
    </a>
    <span class="siteLinkRight">
              <c:if test="${sessionScope.logged != null}" >
                  <c:if test="${sessionScope.user != null}">
                      <div class="dropdown">
                          <a onclick="openNav(true)" class="siteLink">
                              <c:out value="Ciao"/>
                              <c:out value="${sessionScope.user.name}"/>
                          </a>
                              <div id="sidenav" class="sidenav">
                                  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                                  <a href="cart">Carrello</a>
                                  <a href="user/purchase">Ordini</a>
                                  <a href="user/preference">Preferenze</a>
                             </div>
                  </c:if>
                  <c:if test="${sessionScope.seller != null}">
                          <a onclick="toggleMenu(false)" class="siteLink">
                              <c:out value="Ciao"/>
                              <c:out value="${sessionScope.seller.name}"/>
                          </a>
                          <div id="sidenav" class="sidenav">
                                  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                                  <a href="seller/">Crea Ristorante</a>
                                  <a href="seller/seller">Visualizza Ordini</a>
                          </div>
                  </c:if>
              </c:if>
                <c:if test="${sessionScope.logged == null}">
                    <a href="login" class="siteLink">
                        <c:out value="Accedi"/>
                    </a>
            </c:if>
            <a href="help" class="siteLink">
                Aiuto
            </a>

    </span>
</nav>
</html>
