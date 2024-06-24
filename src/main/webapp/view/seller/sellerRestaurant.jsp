<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ristorante Seller</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="ristorante-seller">
        <div class="div-2">
            <img class="logo" alt="Logo" src="logo-1.png" />
            <img class="line" alt="Line" src="line-1.svg" />
            <div class="container-2">
                <div class="punto-wrapper">
                    <img class="punto" alt="Punto" src="icons8-punto-interrogativo-24-1-1.png" />
                </div>
            </div>
            <p class="p">Benvenuto ${nomeRistorante}, gestisci qui la tua attività:</p>
            <div class="elenco-prodotti">
                <div class="text-wrapper-2">GESTISCI I TUOI PRODOTTI</div>
                <div class="frame">
                    <div class="overlap">
                        <div class="text-field-instance overlap">
                            <label class="indirizzo-4" for="descrizione">Descrizione</label>
                            <input type="text" id="descrizione" class="indirizzo" value="Inserisci qui una descrizione">
                        </div>
                        <img class="write-icon" alt="Write Icon" src="write-icon.png" />
                        <img class="img" alt="Line" src="line-4.svg" />
                    </div>
                    <div class="frame-nome">
                        <div class="overlap">
                            <div class="text-field-instance overlap">
                                <label class="indirizzo-4" for="nomeProdotto">Nome prodotto</label>
                                <input type="text" id="nomeProdotto" class="indirizzo" value="Inserisci qui il nome">
                            </div>
                            <img class="write-icon" alt="Write Icon" src="write-icon.png" />
                            <img class="img" alt="Line" src="image.svg" />
                        </div>
                        <div class="text-wrapper-3">2.</div>
                        <div class="rimuovi-button">
                            <div class="overlap-group">
                                <div class="group">
                                    <div class="vector-wrapper">
                                        <img class="vector" alt="Vector" src="image.png" />
                                    </div>
                                </div>
                                <img class="vector-2" alt="Vector" src="vector-2.png" />
                            </div>
                        </div>
                    </div>
                    <div class="prezzo">
                        <div class="overlap-2">
                            <div class="text-field-instance indirizzo-5">
                                <label class="indirizzo-4" for="prezzo">Prezzo</label>
                                <input type="text" id="prezzo" class="indirizzo" value="prezzo">
                            </div>
                            <img class="write-icon-instance" alt="Write Icon" src="write-icon.png" />
                            <img class="line-2" alt="Line" src="line-4-2.svg" />
                        </div>
                    </div>
                    <div class="allergeni">
                        <div class="overlap-2">
                            <div class="text-field-instance indirizzo-6">
                                <label class="indirizzo-4" for="allergeni">Allergeni</label>
                                <input type="text" id="allergeni" class="indirizzo" value="Inserisci gli allergeni">
                            </div>
                            <img class="icon-instance-node" alt="Write Icon" src="write-icon.png" />
                            <img class="line-3" alt="Line" src="line-4-3.svg" />
                        </div>
                    </div>
                    <div class="green-point">
                        <div class="overlap-2">
                            <div class="text-field-instance indirizzo-5">
                                <label class="indirizzo-4" for="greenPoint">Green Point</label>
                                <input type="text" id="greenPoint" class="indirizzo" value="punti">
                            </div>
                            <img class="write-icon-instance" alt="Write Icon" src="write-icon.png" />
                            <img class="line-2" alt="Line" src="line-4-4.svg" />
                        </div>
                    </div>
                </div>
                <div class="frame-2">
                    <div class="overlap">
                        <div class="text-field-instance overlap">
                            <label class="indirizzo-4" for="descrizione2">Descrizione</label>
                            <input type="text" id="descrizione2" class="indirizzo" value="Pizza">
                        </div>
                        <img class="write-icon" alt="Write Icon" src="write-icon.png" />
                        <img class="img" alt="Line" src="line-4-9.svg" />
                    </div>
                    <div class="frame-nome">
                        <div class="overlap">
                            <div class="text-field-instance overlap">
                                <label class="indirizzo-4" for="nomeProdotto2">Nome prodotto</label>
                                <input type="text" id="nomeProdotto2" class="indirizzo" value="Margherita">
                            </div>
                            <img class="write-icon" alt="Write Icon" src="write-icon.png" />
                            <img class="img" alt="Line" src="line-4-10.svg" />
                        </div>
                        <div class="text-wrapper-3">1.</div>
                        <div class="rimuovi-button">
                            <div class="overlap-group">
                                <div class="group">
                                    <div class="img-wrapper">
                                        <img class="vector" alt="Vector" src="vector-4.png" />
                                    </div>
                                </div>
                                <img class="vector-2" alt="Vector" src="vector-5.png" />
                            </div>
                        </div>
                    </div>
                    <div class="prezzo">
                        <div class="overlap-2">
                            <div class="text-field-instance indirizzo-5">
                                <label class="indirizzo-4" for="prezzo2">Prezzo</label>
                                <input type="text" id="prezzo2" class="indirizzo" value="4.50">
                            </div>
                            <img class="write-icon-instance" alt="Write Icon" src="write-icon.png" />
                            <img class="line-2" alt="Line" src="line-4-11.svg" />
                        </div>
                    </div>
                    <div class="allergeni">
                        <div class="overlap-2">
                            <div class="text-field-instance indirizzo-6">
                                <label class="indirizzo-4" for="allergeni2">Allergeni</label>
                                <input type="text" id="allergeni2" class="indirizzo" value="Glutine">
                            </div>
                            <img class="icon-instance-node" alt="Write Icon" src="write-icon.png" />
                            <img class="line-3" alt="Line" src="line-4-12.svg" />
                        </div>
                    </div>
                    <div class="green-point">
                        <div class="overlap-2">
                            <div class="text-field-instance indirizzo-5">
                                <label class="indirizzo-4" for="greenPoint2">Green Point</label>
                                <input type="text" id="greenPoint2" class="indirizzo" value="5">
                            </div>
                            <img class="write-icon-instance" alt="Write Icon" src="write-icon.png" />
                            <img class="line-2" alt="Line" src="line-4-13.svg" />
                        </div>
                    </div>
                </div>
                <div class="check-out-instance check-out-2">
                    <button class="check-out-button" type="submit">SALVA LE MODIFICHE</button>
                </div>
            </div>
            <div class="recapiti-guadagni">
                <div class="text-wrapper-4">CONTROLLA I TUOI GUADAGNI</div>
                <div class="text-wrapper-5">GESTISCI I TUOI RECAPITI</div>
                <div class="recapiti">
                    <div class="overlap-group-2">
                        <div class="text-field-instance indirizzo-7">
                            <label class="indirizzo-13" for="indirizzo">Indirizzo della tua attività</label>
                            <input type="text" id="indirizzo" class="indirizzo-8" value="Indirizzo">
                        </div>
                        <img class="write-icon-2" alt="Write Icon" src="write-icon.png" />
                        <img class="line-4" alt="Line" src="line-4-5.svg" />
                    </div>
                </div>
                <div class="text-wrapper-6">GUADAGNO TOTALE: 104€</div>
                <div class="text-wrapper-7">DETTAGLI DELL’ATTIVITÀ</div>
                <div class="overlap-3">
                    <div class="recapiti-2">
                        <div class="overlap-4">
                            <div class="text-field-instance indirizzo-7">
                                <label class="indirizzo-13" for="indirizzo2">Indirizzo della tua attività</label>
                                <input type="text" id="indirizzo2" class="indirizzo-8" value="Indirizzo">
                            </div>
                            <img class="write-icon-2" alt="Write Icon" src="write-icon.png" />
                            <img class="line-4" alt="Line" src="line-4-6.svg" />
                        </div>
                        <div class="overlap-group-wrapper">
                            <div class="overlap-group-2">
                                <div class="text-field-instance indirizzo-14">
                                    <label class="indirizzo-13" for="categoria">Categoria</label>
                                    <input type="text" id="categoria" class="indirizzo-8" value="categoria">
                                </div>
                                <img class="write-icon-3" alt="Write Icon" src="write-icon.png" />
                                <img class="line-5" alt="Line" src="line-4-7.svg" />
                            </div>
                        </div>
                    </div>
                    <div class="text-field-instance indirizzo-15">
                        <label class="indirizzo-21" for="prezzoConsegna">Prezzo Consegna</label>
                        <input type="text" id="prezzoConsegna" class="indirizzo-16" value="prezzo">
                    </div>
                    <img class="line-6" alt="Line" src="line-4-8.svg" />
                    <img class="write-icon-4" alt="Write Icon" src="write-icon.png" />
                </div>
                <div class="check-out-instance check-out-2">
                    <button class="check-out-button" type="submit">SALVA LE MODIFICHE</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
