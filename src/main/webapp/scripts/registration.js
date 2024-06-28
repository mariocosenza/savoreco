"use strict";

const regexUsername = /^[a-zA-Z][a-zA-Z0-9-_]{2,15}$/;
const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

async function submitRegistration() {
    if (validate()) {
        const formData = new FormData(document.querySelector("#form"))
        try {
            const response = await fetch("/registration", {
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
    if (document.querySelector("#seller").checked) {
        return validateSeller()
    } else {
        return validateUser()
    }
}

function validateUser() {
    document.querySelectorAll("label").forEach(e => e.style.color = "var(--md-sys-color-on-background)")
    let error = false
    if (document.querySelector("#user").checked) {
        for (const arg of document.querySelectorAll("label")) {
            const element = document.getElementById(arg.htmlFor)
            if (element.type !== "radio" && element.value !== "") {
                if (element.id === "name") {
                    if (regexUsername.test(element.value)) {
                        arg.style.color = "var(--md-sys-color-primary)"
                    } else {
                        arg.style.color = "var(--md-sys-color-error)"
                        error = true
                    }
                } else if (element.id === "surname") {
                    if (regexUsername.test(element.value)) {
                        arg.style.color = "var(--md-sys-color-primary)"
                    } else {
                        arg.style.color = "var(--md-sys-color-error)"
                        error = true
                    }
                } else if (element.id === "email") {
                    if (regexEmail.test(element.value)) {
                        arg.style.color = "var(--md-sys-color-primary)"
                    } else {
                        arg.style.color = "var(--md-sys-color-error)"
                        error = true
                    }
                } else if (element.id === "password") {
                    if (regexPassword.test(element.value)) {
                        arg.style.color = "var(--md-sys-color-primary)"
                    } else {
                        arg.style.color = "var(--md-sys-color-error)"
                        error = true
                    }
                    if (element.value === document.querySelector("#check_password").value) {
                        document.querySelector("#check_label").style.color = "var(--md-sys-color-primary)"
                    } else if (document.querySelector("#check_password").value !== "") {
                        arg.style.color = "var(--md-sys-color-error)"
                        document.querySelector("#check_label").style.color = "var(--md-sys-color-error)"
                        error = true
                    }
                } else if (element.id === "birthdate") {
                    if (element.value !== "") {
                        arg.style.color = "var(--md-sys-color-primary)"
                    } else {
                        arg.style.color = "var(--md-sys-color-error)"
                        error = true
                    }
                }
            } else if (element.value === "") {
                error = true
            }
        }
    }

    if (!error) {
        document.querySelector("button").disabled = false
    }

    return !error
}

function validateSeller() {
    document.querySelectorAll("label").forEach(e => e.style.color = "var(--md-sys-color-on-background)")
    let error = false
    for (const arg of document.querySelectorAll("label")) {
        const element = document.getElementById(arg.htmlFor)
        if (element.type !== "radio" && element.value !== "") {
            if (element.id === "name") {
                if (regexUsername.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
            } else if (element.id === "surname") {
                if (regexUsername.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
            } else if (element.id === "email") {
                if (regexEmail.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
            } else if (element.id === "password") {
                if (regexPassword.test(element.value)) {
                    arg.style.color = "var(--md-sys-color-primary)"
                } else {
                    arg.style.color = "var(--md-sys-color-error)"
                    error = true
                }
                if (element.value === document.querySelector("#check_password").value) {
                    document.querySelector("#check_label").style.color = "var(--md-sys-color-primary)"
                } else if (document.querySelector("#check_password").value !== "") {
                    arg.style.color = "var(--md-sys-color-error)"
                    document.querySelector("#check_label").style.color = "var(--md-sys-color-error)"
                    error = true
                }
            }
        } else if (element.value === "" && element.id !== "birthdate") {
            error = true
        }
    }

    if (!error) {
        document.querySelector("button").disabled = false
    }

    return !error
}

function radioChange() {
    const age = document.querySelector("#birthdate")
    const title = document.querySelector("#age_label")

    if (document.querySelector("#user").checked) {
        title.style.display = "flex"
        age.required = true
        age.style.display = "flex"
    } else {
        title.style.display = "none"
        age.required = false
        age.style.display = "none"
    }
}

window.onload = function () {
    document.querySelector("#birthdate").max = new Date().toLocaleDateString('it-IT')
}
