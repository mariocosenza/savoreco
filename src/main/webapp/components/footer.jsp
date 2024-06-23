<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="it">
<footer>
    <c:set var="serverPath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
    <p class="footer">
        <span class="footerTitle">Apprezziamo la tua opinione</span> <br>
        <a href="mailto:feedback@savoreco.it">
            Invia il tuo feedback
        </a><br><br>
        <span class="footerTitle">Seguici su</span> <br>
        <a href="https://github.com/mariocosenza/savoreco" target="_blank" rel="noopener">
            GitHub
        </a><br><br>
        <img class="footerLogo" src="${serverPath}/assets/icons/github-svgrepo-com.svg" alt="GitHub Logo">
    </p>
    <p class="footer">
        <span class="footerTitle">Servizio Clienti</span> <br>
        <a href="help">
            Domande frequenti
        </a> <br>
        <a href="${serverPath}/login">
            Accedi
        </a><br>
        <a href="${serverPath}/registration">
            Crea un account
        </a><br>
        <a href="${serverPath}/login/moderator">
            Accedi come moderatore
        </a><br> <br>

        <span class="footerTitle">Città</span> <br>
        <a href="${serverPath}/search?lat=40.8563100&lon=14.2464100">
            Napoli
        </a><br>
        <a href="${serverPath}/search?lat=40.7021100&lon=14.4868500">
            Castellammare di Stabia
        </a><br>
        <a href="${serverPath}/search?lat=40.6417500&lon=15.8079400">
            Potenza
        </a>
    </p>
    <p class="footer">
        <span class="footerTitle">Teniamo alla tua privacy</span> <br>
        <a href="help">Informativa privacy</a><br>
        <a href="https://github.com/mariocosenza/savoreco?tab=readme-ov-file#authors" target="_blank" rel="noopener">Il nostro team</a><br><br>
        <span class="copyright">© 2024 Savoreco</span><br>
        <img class="footerLogo" src="${serverPath}/assets/icons/google-pay-primary-logo-logo-svgrepo-com.svg" alt="GPay Logo">
    </p>
</footer>
</html>
