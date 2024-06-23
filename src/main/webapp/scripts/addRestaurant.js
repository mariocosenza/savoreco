"use strict";

const regexName = /^[a-zA-Z][a-zA-Z0-9-_\s]{2,15}$/;
const regexDescription = /^.{2,255}$/;
const regexDeliveryCost = /^\d+(\.\d{1,2})?$/;
const regexCategory = /^[a-zA-Z\s]{2,50}$/;

async function submitRegistration() {
    if (validate()) {
        const address = searchResult()
        document.querySelector("#lat").value = address.latitude
        document.querySelector("#lon").value = address.longitude
        document.querySelector("#postal").value = address.postalCode
        document.querySelector("#address").value = address.street
        document.querySelector("#city").value = address.city
        const formData = new FormData(document.querySelector("#form"))
        try {
            const response = await fetch("/addRestaurant", {
                method: "POST",
                body: JSON.stringify(Object.fromEntries(formData)),
                contentType: "application/json"
            });
            if (response.ok) {
                window.location.href = "/home"
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
    document.querySelector(".alert").style.visibility = "visible"
}

function validate() {
    document.querySelectorAll("label").forEach(e => e.style.color = "var(--md-sys-color-on-background)")
    let error = false
    for (const arg of document.querySelectorAll("label")) {
        const element = document.getElementById(arg.htmlFor)
        if (element.id === "name") {
            if (regexName.test(element.value)) {
                arg.style.color = "var(--md-sys-color-primary)"
            } else {
                arg.style.color = "var(--md-sys-color-error)"
                error = true
            }
        } else if (element.id === "description") {
            if (regexDescription.test(element.value)) {
                arg.style.color = "var(--md-sys-color-primary)"
            } else {
                arg.style.color = "var(--md-sys-color-error)"
                error = true
            }
        } else if (element.id === "deliveryCost") {
            if (regexDeliveryCost.test(element.value)) {
                arg.style.color = "var(--md-sys-color-primary)"
            } else {
                arg.style.color = "var(--md-sys-color-error)"
                error = true
            }
        } else if (element.id === "category") {
            if (regexCategory.test(element.value)) {
                arg.style.color = "var(--md-sys-color-primary)"
            } else {
                arg.style.color = "var(--md-sys-color-error)"
                error = true
            }
        } else if (element.value === "") {
            error = true
        }
    }

    if (!error) {
        document.querySelector("button").disabled = false
    }

    return !error
}