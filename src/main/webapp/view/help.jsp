<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="/components/header.jsp" %>
    <title>Help Page</title>
    <link href="../assets/styles/help.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="../components/navbar.jsp"/>
<main>
    <header>
        <h1>Assistenza Clienti Savoreco</h1>
        <p>Se hai bisogno di aiuto o hai domande, sei nel posto giusto.</p>
    </header>

    <article>
        <h2 id="question">Domande Frequenti (FAQ)</h2>
        <div>
            <h3>Come posso cancellare il mio ordine?</h3>
            <p>Se desideri cancellare il tuo ordine, puoi farlo entro prima dalla conferma dell'ordine. Vai alla
                <strong>sezione Ordini</strong> e seleziona l'ordine che desideri modificare.</p>
        </div>
        <div>
            <h3>Come posso trovare il mio ordine?</h3>
            <p>Accedi alla <strong>sezione Ordini</strong> del nostro sito per visualizzare lo stato del tuo ordine. Se
                hai problemi, contattaci all'indirizzo <strong>info@savoreco.it</strong>.</p>
        </div>
        <div>
            <h3>Quali sono le opzioni di pagamento disponibili?</h3>
            <p>Accettiamo tutte le principali carte di credito, pagamenti tramite GPay e, in alcune aree, il pagamento
                alla consegna.</p>
        </div>
        <div>
            <h3>Offrite opzioni di cibo vegano o vegetariano?</h3>
            <p>Sì, offriamo una varietà di opzioni vegane e vegetariane.</p>
        </div>
        <div>
            <h3>Come posso lasciare un feedback sul mio ordine?</h3>
            <p>Apprezziamo il tuo feedback! Invia il tuo feedback <a href="mailto:feedback@savoreco.it"><strong>feedback@savoreco.it</strong></a>
            </p>
        </div>
        <div>
            <h3>Qual è la vostra politica di sostenibilità?</h3>
            <p>La sostenibilità è al centro della nostra missione. Utilizziamo imballaggi ecologici, collaboriamo con
                fornitori locali e ci impegniamo a ridurre gli sprechi alimentari.</p>
        </div>
    </article>

    <article>
        <h2>Contattaci</h2>
        <p>Non hai trovato quello che cercavi nelle FAQ? Mandaci una mail a <a href="mailto:info@savoreco.it"><strong>info@savoreco.it</strong></a>
            e ti risponderemo il prima possibile.</p>
    </article>

    <article>
        <h2>Avviso sulla Privacy</h2>
        <p>La privacy dei nostri utenti è di fondamentale importanza per Savoreco. Questa informativa sulla privacy
            descrive come raccogliamo, utilizziamo e proteggiamo le vostre informazioni personali.</p>

        <h3>Dati raccolti</h3>
        <p>Raccogliamo informazioni che ci fornite direttamente quando utilizzate i nostri servizi, come nome, indirizzo
            email e dettagli dell'ordine. Raccogliamo anche dati tecnici quando visitate il nostro sito, come il tipo di
            browser e l'indirizzo IP.</p>

        <h3>Utilizzo dei dati</h3>
        <p>Utilizziamo le vostre informazioni per gestire gli ordini, migliorare i nostri servizi e comunicare con voi.
            Potremmo anche utilizzare i dati per fini analitici interni.</p>

        <h3>Condivisione dei dati</h3>
        <p>Non condividiamo le vostre informazioni personali con terzi, tranne che in caso di necessità per fornire i
            nostri servizi o se richiesto dalla legge.</p>

        <h3>Sicurezza</h3>
        <p>Adottiamo misure di sicurezza appropriate per proteggere contro l'accesso non autorizzato o la perdita di
            dati.</p>

        <h3>Diritti degli utenti</h3>
        <p>Avete il diritto di accedere, correggere o cancellare le vostre informazioni personali. Potete anche opporvi
            al trattamento dei vostri dati.</p>

        <h3>Modifiche a questa informativa</h3>
        <p>Potremmo aggiornare questa informativa sulla privacy di tanto in tanto. Vi incoraggiamo a rivederla
            regolarmente.</p>

        <h3>Contatti</h3>
        <p>Per qualsiasi domanda relativa alla privacy, potete contattarci all'indirizzo
            <strong>info@savoreco.it</strong>.</p>


    </article>

    <article>
        <h3>Progetto Open Source</h3>
        <p>Questo sito è parte di un progetto per tecnologie software per il web realizzato da Mario Cosenza, Giuseppe
            Cavallaro e Mario Fasolino. Il progetto è open source sotto la licenza AGPL-3.0. Il codice sorgente è
            disponibile su GitHub al seguente indirizzo: <a href="https://github.com/mariocosenza/savoreco"
                                                            rel="noopener" target="_blank"><strong>github.com/mariocosenza/savoreco</strong></a>.
        </p>
    </article>


    <article>
        <h2>Politica sui Cookie</h2>
        <p>Savoreco utilizza i cookie per migliorare l'esperienza degli utenti sul nostro sito di food delivery. I
            cookie sono piccoli file di testo che vengono salvati sul tuo dispositivo quando visiti il nostro sito.</p>

        <h3>Tipi di Cookie Utilizzati</h3>
        <p>Utilizziamo diversi tipi di cookie:</p>
        <ul>
            <li><strong>Cookie Essenziali:</strong> Questi sono necessari per il funzionamento del sito e non possono
                essere disattivati. Includono, ad esempio, cookie che ti permettono di accedere in aree sicure del
                nostro sito.
            </li>
            <li><strong>Cookie Analitici:</strong> Ci aiutano a capire come i visitatori interagiscono con il sito,
                fornendo informazioni anonime sulla navigazione.
            </li>
        </ul>

        <h3>Gestione dei Cookie</h3>
        <p>Puoi scegliere di accettare o rifiutare i cookie. La maggior parte dei browser web accetta automaticamente i
            cookie, ma di solito puoi modificare le impostazioni del tuo browser per rifiutare i cookie se lo
            preferisci. Questo potrebbe impedirti di sfruttare appieno il sito.</p>

        <h3>Modifiche alla Politica sui Cookie</h3>
        <p>Questa politica sui cookie può essere aggiornata di tanto in tanto. Ti invitiamo a rivederla regolarmente per
            essere informato su come utilizziamo i cookie.</p>

        <h3>Contatti</h3>
        <p>Se hai domande sulla nostra politica sui cookie, contattaci all'indirizzo <strong>info@savoreco.it</strong>.
        </p>
    </article>


</main>
<%@ include file="/components/footer.jsp" %>
</body>
</html>
