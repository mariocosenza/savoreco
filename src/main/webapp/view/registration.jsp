<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione - Savoreco</title>
    <link href="../assets/styles/access.css" rel="stylesheet">
    <%@ include file="/components/header.jsp"%>
    <script src="../scripts/registration.js"></script>
    <script src="../scripts/tooltip.js"></script>
</head>
<body>
<a href="home">
    <img src="../assets/images/savoreco-logo.webp" alt="page logo" class="accessLogo">
</a>
<div class="alert">
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    <strong>Errore!</strong> Email o password errati
</div>
<main>
    <div class="center">
        <form method="post" id="form" onsubmit="submitRegistration(); return false" onchange="validate()">
            <h1>
                Registrati nella famiglia Savoreco
            </h1>
            <div>
                <label for="user">Sei un utente?</label>
                <input type="radio" id="user" name="profile_type" value="user" checked onchange="radioChange()">
                <label for="seller">Sei un venditore?</label>
                <input type="radio" id="seller" name="profile_type" value="seller" onchange="radioChange()">
            </div>
            <label for="name" id="name_label">Nome</label>
            <input type="text" id="name" name="name" placeholder="Inserisci il tuo nome" required maxlength="48">
            <label for="surname" id="surname_label">Cognome</label>
            <input type="text" id="surname" name="surname" placeholder="Inserisci il tuo cognome" required maxlength="48">
            <label for="email" id="email_label">Email</label>
            <input type="email" id="email" name="email" placeholder="Inserisci la tua email" required maxlength="128">
            <label for="password">Password</label>
            <span class="tooltiptext">La password deve avere 8 caratteri almeno un numero e un carattere speciale</span>
            <input type="password" id="password" name="password" placeholder="Inserisci la tua password" required onfocusin="showTooltip()" onfocusout="hideTooltip()">
            <label id="check_label" for="check_password">Conferma password</label>
            <input type="password" id="check_password" name="check_password" placeholder="Conferma la tua password" required>
            <label for="birthdate" id="age_label">Data nascita</label>
            <input type="date" id="birthdate" name="birthdate" min="1900-01-01">
            <button disabled>
                Crea account
            </button>
            <p>
                Sei gia iscritto? <a href="login">Accedi</a>
            </p>
            <p class="policy">
                Creando un account, accetti i nostri Termini di Servizio e confermi di aver letto la nostra Privacy Policy.
                La tua privacy è garantita e i dati saranno trattati nel rispetto delle normative vigenti.
            </p>
        </form>
    </div>
</main>
<%@ include file="../components/footer.jsp"%>
</body>
</html>
