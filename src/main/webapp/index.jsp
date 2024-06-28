<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/components/header.jsp" %>
    <link href="assets/styles/home-page.css" rel="stylesheet">
    <script src="scripts/tooltip.js"></script>
    <script src="scripts/home.js"></script>
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
                <div id="autocomplete">
                </div>
                <script>
                    autocomplete()
                </script>
            </search>
        </div>
    </div>

    <div class="center info">
        <img src="assets/images/center-info.webp" class="center" alt="Info center">
        <p>
            Savoreco si distingue nel panorama del food delivery per il suo impegno nella promozione di uno stile di
            vita sostenibile.
            La piattaforma incoraggia attivamente i propri utenti a fare scelte ecologiche attraverso un innovativo
            sistema di punteggio che converte le ordinazioni in sconti e vantaggi,
            premiando così coloro che optano per opzioni più rispettose dell’ambiente.
            Savoreco si impegna nello sviluppo di un sito web responsive e intuitivo, progettato per essere
            perfettamente fruibile su una vasta gamma di dispositivi,
            dai telefoni cellulari ai tablet, fino ai computer desktop.
        </p>
    </div>
    <div class="bottomInfo">
        <div class="left">
            <img src="assets/images/left-info.webp" class="left" alt="Info left">
            <p>
                Potrai aggiungere prodotti al carrello, gestire le informazioni di consegna e completare l'ordine
                facilmente.
                Effettua scelte sostenibili per ottenere sconti, sottoscrivi un abbonamento per consegne gratuite e
                monitora il tuo ordine in tempo reale.
                Ricevi messaggi promozionali, cerca ristoranti e contatta il supporto clienti per assistenza.
            </p>
        </div>
        <div class="right">
            <img src="assets/images/right-info.webp" class="right" alt="Info right">
            <p>
                I venditori possono registrare un nuovo ristorante, aggiungere e categorizzare il menù, specificare tag,
                definire i prezzi, personalizzare la pagina del ristorante, aggiungere una descrizione,
                promuovere attività eco-sostenibili e contattare l'assistenza della piattaforma
            </p>
        </div>
    </div>
</main>
<%@ include file="components/footer.jsp" %>
</body>
</html>