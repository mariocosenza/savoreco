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
                          <button onclick="toggleMenu(true)" class="dropbtn">
                              <c:out value="Ciao"/>
                              <c:out value="${sessionScope.user.name}"/>
                          </button>
                          <div id="userDropDown" class="dropdown-content">
                                <a href="#home">Home</a>
                                <a href="#about">About</a>
                                <a href="#contact">Contact</a>
                          </div>
                        </div>
                  </c:if>
                  <c:if test="${sessionScope.seller != null}">
                       <div class="dropdown">
                          <button onclick="toggleMenu(false)" class="dropbtn">
                              <c:out value="Ciao"/>
                              <c:out value="${sessionScope.seller.name}"/>
                          </button>
                          <div id="sellerDropDown" class="dropdown-content">
                                <a href="#home">Home</a>
                                <a href="#about">About</a>
                                <a href="#contact">Contact</a>
                          </div>
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
