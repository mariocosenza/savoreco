"use strict";


const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

async function submitRegistration() {
    if (validate() && searchResult() !== undefined) {
        const address = searchResult()
        document.querySelector("#lat").value = address.latitude
        document.querySelector("#lon").value = address.longitude
        document.querySelector("#postal").value = address.postalCode
        document.querySelector("#address").value = address.street + ` ${address.number}`
        document.querySelector("#city").value = address.city
        const formData = new FormData(document.querySelector("#form"))
        try {
            const response = await fetch("/user/preference", {
                method: "POST",
                body: JSON.stringify(Object.fromEntries(formData)),
                contentType: "application/json"
            });
            if (response.status === 201) {
                window.location.href = "/home"
            } else if (response.ok) {
                formOk()
            } else {
                formError()
            }
        } catch (e) {
            window.location.href = "/home"
        }
    } else {
        formError()
    }
}

function formError() {
    document.querySelector("button").disabled = true
    document.querySelector("label").style.color = "var(--md-sys-color-on-background)"
    const alert = document.querySelector(".alert")
    alert.style.backgroundColor = "var(--md-sys-color-error-container)"
    alert.style.visibility = "visible"
    alert.style.color = "var(--md-sys-color-on-error-container)"
    document.querySelector("#textAlert").innerHTML = "Errore nell'aggiornamento"
}

function formOk() {
    document.querySelector("button").disabled = true
    document.querySelector("label").style.color = "var(--md-sys-color-on-background)"
    const alert = document.querySelector(".alert")
    alert.style.visibility = "visible"
    alert.style.backgroundColor = "var(--md-sys-color-primary)"
    alert.style.color = "var(--md-sys-color-on-background)"
    document.querySelector("#textAlert").innerHTML = "Aggiornato con successo"
}

function validate() {
    document.querySelectorAll("label").forEach(e => e.style.color = "var(--md-sys-color-on-background)")
    let error = false
    let count = 0;
    for (const arg of document.querySelectorAll("label")) {
        const element = document.getElementById(arg.htmlFor)
        if (element.id === "email") {
            if (element.value === "") {
                count++
            } else if (regexEmail.test(element.value)) {
                arg.style.color = "var(--md-sys-color-primary)"
            } else {
                arg.style.color = "var(--md-sys-color-error)"
                error = true
            }
        } else if (element.id === "password") {
            if (element.value === "") {
                count++
            } else if (regexPassword.test(element.value)) {
                arg.style.color = "var(--md-sys-color-primary)"
            } else {
                arg.style.color = "var(--md-sys-color-error)"
                error = true
            }
            if (element.value === document.querySelector("#check_password").value) {
                if (element.value === "") {
                    count++
                } else {
                    document.querySelector("#check_label").style.color = "var(--md-sys-color-primary)"
                }
            } else if (document.querySelector("#check_password").value !== "") {
                arg.style.color = "var(--md-sys-color-error)"
                document.querySelector("#check_label").style.color = "var(--md-sys-color-error)"
                error = true
            }
        }
    }

    if (count === 3)
        error = false

    if (!error) {
        document.querySelector("button").disabled = false
    }

    return !error
}


function noScroll() {
    document.querySelector("body").style.overflowY = "hidden"
}

function revertScroll() {
    document.querySelector("body").style.overflowY = "auto"
}

