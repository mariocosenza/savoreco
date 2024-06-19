<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/components/header.jsp" %>
    <link href="assets/styles/home-page.css" rel="stylesheet">
    <script src="scripts/tooltip.js"></script>
    <script src="scripts/home.js"></script>
    <link href="https://js.radar.com/v4.3.0/radar.css" rel="stylesheet">
    <script src="https://js.radar.com/v4.3.0/radar.min.js"></script>
    <title>Savoreco</title>
</head>
<body>
    <jsp:include page="/components/navbar.jsp"/>

<main>
    <div class="overlay">
        <div class="center">
            <h1 id="searchBoxLeft" class="search">
                E CH'
            </h1>
            <h1 id="searchBoxRight" class="search">
                SAPORE
            </h1>
            <span class="tooltiptext" style="display: none">Indirizzo non trovato riprova</span>
            <search>
                <form role="search" action="search" method="get">
                    <div id="autocomplete">
                    </div>
                    <script>
                       autocomplete()
                    </script>
                </form>
            </search>
        </div>
    </div>

    <div class="center info">
        <img src="assets/images/center-info.webp" class="center">
        <p>
            Savoreco è una piattaforma di food delivery che promuove la sostenibilità ambientale, <br>
            incentivando scelte green con un sistema di punteggio e sconti basati sulle ordinazioni. <br>
            Per attrarre utenti, è essenziale sviluppare un sito web responsive compatibile con la maggior parte dei dispositivi.
        </p>
    </div>
    <div class="bottomInfo">
        <div class="left">
            <img src="assets/images/left-info.webp" class="left">
            <p>
                Potrai aggiungere prodotti al carrello, gestire le informazioni di consegna e completare l'ordine facilmente.
                Effettua scelte sostenibili per ottenere sconti, sottoscrivi un abbonamento per consegne gratuite e monitora il tuo ordine in tempo reale.
                Ricevi messaggi promozionali, cerca ristoranti e contatta il supporto clienti per assistenza.
            </p>
        </div>
        <div class="right">
            <img src="assets/images/right-info.webp" class="right">
            <p>
                I venditori possono registrare un nuovo ristorante, aggiungere e categorizzare il menù, specificare tag,
                definire i prezzi, personalizzare la pagina del ristorante, aggiungere una descrizione,
                promuovere attività eco-sostenibili e contattare l'assistenza della piattaforma
            </p>
        </div>
    </div>
</main>
<%@ include file="components/footer.jsp"%>
</body>
</html>