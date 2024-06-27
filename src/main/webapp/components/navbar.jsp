<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="it">
<c:set var="serverPath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<nav id="topNavBar">
    <script src="../scripts/navbar.js"></script>
    <a href="${serverPath}/home">
        <img src="../assets/images/savoreco-logo.svg" alt="Logo Savoreco" class="logoImage">
    </a>
    <a href="${serverPath}/home" class="siteLink">
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
                                  <a href="${serverPath}/user/cart">Carrello</a>
                                  <a href="${serverPath}/user/userOrders">Ordini</a>
                                  <a href="${serverPath}/user/ecopoint">Ecopunti</a>
                                  <a href="${serverPath}/user/preference">Preferenze</a>
                                  <a href="${serverPath}/exit">Esci</a>
                             </div>
                  </c:if>
                  <c:if test="${sessionScope.seller != null}">
                          <a onclick="openNav(false)" class="siteLink">
                              <c:out value="Ciao"/>
                              <c:out value="${sessionScope.seller.name}"/>
                          </a>
                          <div id="sidenav" class="sidenav">
                                  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                              <c:choose>
                                  <c:when test="${sessionScope.seller.restaurant == null}">
                                      <a href="${serverPath}/seller/addRestaurant">Crea Ristorante</a>
                                  </c:when>
                                  <c:otherwise>
                                      <a href="${serverPath}/seller/restaurantOrders">Visualizza Ordini</a>
                                      <a href="${serverPath}/seller/sellerRestaurant">Gestisci Ristorante</a>
                                  </c:otherwise>
                              </c:choose>
                              <a href="${serverPath}/exit">Esci</a>
                          </div>
                  </c:if>
                  <c:if test="${sessionScope.moderator != null}">
                           <a onclick="openNav(false)" class="siteLink">
                              <c:out value="Ciao"/>
                              <c:out value="${sessionScope.moderator.name}"/>
                          </a>
                          <div id="sidenav" class="sidenav">
                                  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                                  <a href="${serverPath}/moderator/moderatorPage">Pannello Moderatore</a>
                                  <a href="${serverPath}/exit">Esci</a>
                          </div>
                 </c:if>
              </c:if>
                <c:if test="${sessionScope.logged == null}">
                    <a href="${serverPath}/login" class="siteLink">
                        <c:out value="Accedi"/>
                    </a>
            </c:if>
            <a href="${serverPath}/help" class="siteLink">
                Aiuto
            </a>
    </span>
</nav>
</html>
