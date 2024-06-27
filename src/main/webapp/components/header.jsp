<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="serverPath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<html lang="it">
<meta charset="UTF-8">
<meta name="theme-color" content="#97d5a5">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Cibo, Ecologico, Saporito" name="keywords">
<meta content="Savoreco" name="author">
<link href="${serverPath}/assets/icons/favicon-48x48.ico" rel="icon" sizes="48x48" type="image/png">
<link href="${serverPath}/assets/icons/favicon-192x192.ico" rel="icon">
<link href="https://js.radar.com/v4.3.0/radar.css" rel="stylesheet">
<script defer data-cfasync='false' src='https://s.clickiocdn.com/t/236815_wv.js'
        integrity="sha384-hCFg6iAESE26AABi68WLCAOvVgXcT49X0pikPltnyUnclKsFrs4pRcdnqIB3F+Sn"
        crossorigin="anonymous"></script>
<script src="https://js.radar.com/v4.3.0/radar.min.js"></script>
<script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    (function(){
        gtag('consent', 'default', {
            'ad_storage': 'granted',
            'analytics_storage': 'granted',
            'functionality_storage': 'granted',
            'personalization_storage': 'granted',
            'security_storage': 'granted',
            'ad_user_data': 'granted',
            'ad_personalization': 'granted',
            'wait_for_update': 1500
        });
        gtag('consent', 'default', {
            'region': ['AT', 'BE', 'BG', 'HR', 'CY', 'CZ', 'DK', 'EE', 'FI', 'FR', 'DE', 'GR', 'HU', 'IS', 'IE', 'IT', 'LV', 'LI', 'LT', 'LU', 'MT', 'NL', 'NO', 'PL', 'PT', 'RO', 'SK', 'SI', 'ES', 'SE', 'GB', 'CH'],
            'ad_storage': 'denied',
            'analytics_storage': 'denied',
            'functionality_storage': 'denied',
            'personalization_storage': 'denied',
            'security_storage': 'denied',
            'ad_user_data': 'denied',
            'ad_personalization': 'denied',
            'wait_for_update': 1500
        });
        gtag('set', 'ads_data_redaction', true);
        gtag('set', 'url_passthrough', false);
        const s={adStorage:{storageName:"ad_storage",serialNumber:0},analyticsStorage:{storageName:"analytics_storage",serialNumber:1},functionalityStorage:{storageName:"functionality_storage",serialNumber:2},personalizationStorage:{storageName:"personalization_storage",serialNumber:3},securityStorage:{storageName:"security_storage",serialNumber:4},adUserData:{storageName:"ad_user_data",serialNumber:5},adPersonalization:{storageName:"ad_personalization",serialNumber:6}};let c=localStorage.getItem("__lxG__consent__v2");if(c){c=JSON.parse(c);if(c&&c.cls_val)c=c.cls_val;if(c)c=c.split("|");if(c&&c.length&&typeof c[14]!==undefined){c=c[14].split("").map(e=>e-0);if(c.length){let t={};Object.values(s).sort((e,t)=>e.serialNumber-t.serialNumber).forEach(e=>{t[e.storageName]=c[e.serialNumber]?"granted":"denied"});gtag("consent","update",t)}}}
        if(Math.random() < 0.05) {if (window.dataLayer && (window.dataLayer.some(e => e[0] === 'js' && e[1] instanceof Date) || window.dataLayer.some(e => e['event'] === 'gtm.js' && e['gtm.start'] == true ))) {document.head.appendChild(document.createElement('img')).src = "//clickiocdn.com/utr/gtag/?sid=236815";}}
    })();
</script>

<!-- Clickio Consent Main tag -->
<script async type="text/javascript" src="//clickiocmp.com/t/consent_236815.js"></script>
</html>
