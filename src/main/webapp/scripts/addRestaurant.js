"use strict";

const regexName = /^[a-zA-Z][a-zA-Z0-9-_\s]{2,15}$/;
const regexStreet = /^[a-zA-Z0-9\s,.-]{2,100}$/;
const regexZipcode = /^\d{5}$/;
const regexCity = /^[a-zA-Z\s]{2,50}$/;
const regexCountryCode = /^[A-Z\s]{2}$/;
const regexDescription = /^.{2,255}$/;
const regexDeliveryCost = /^\d+(\.\d{1,2})?$/;
const regexCategory = /^[a-zA-Z\s]{2,50}$/;

async function submitRegistration() {
    if (validate()) {
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
    document.querySelector(".alert").style.visibility ="visible"
}

function validate() {
    document.querySelectorAll("label").forEach(e => e.style.color = "var(--md-sys-color-on-background)")
    let error = false
    for (const arg of document.querySelectorAll("label")) {
        const element = document.getElementById(arg.htmlFor)
        if (element.type !== "radio" && element.value !== "") {
            if (element.id === "name") {
                if (regexName.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
            } else if (element.id === "street") {
                if (regexStreet.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
            } else if (element.id === "zipcode") {
                if (regexZipcode.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
            } else if (element.id === "city") {
                if (regexCity.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
            } else if (element.id === "countryCode") {
                if (regexCountryCode.test(element.value)) {
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
            }
        } else if(element.value === "" && element.id !== "age") {
            error = true
        }
    }

    if (!error) {
        document.querySelector("button").disabled = false
    }

    return !error
}