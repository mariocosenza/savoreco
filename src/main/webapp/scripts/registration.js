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
                body: formData,
            });
            if (response.ok) {
                window.location.href = "/home"
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
}

function validate() {
    if (document.querySelector("#seller").checked) {
        return validateSeller()
    } else {
        return validateUser()
    }
}

function validateUser() {
    document.querySelector("label").style.color = "var(--md-sys-color-on-background)"
    let error = false

    if (document.forms["form"]["user"].checked) {
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
                        if(element.value === document.querySelector("#check_password").value) {
                            document.querySelector("#check_label").style.color = "var(--md-sys-color-primary)"
                        } else if(document.querySelector("#check_password").value !== "") {
                            arg.style.color = "var(--md-sys-color-error)"
                            document.querySelector("#check_label").style.color = "var(--md-sys-color-error)"
                            error = true
                        }
                    } else if (element.id === "age") {
                        if(element.value !== "") {
                            arg.style.color = "var(--md-sys-color-primary)"
                        }
                        else {
                            arg.style.color = "var(--md-sys-color-error)"
                            error = true
                        }
                    }
                }
                else if(element.value === "") {
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

}

function radioChange() {
    const age = document.querySelector("#age")
    const title = document.querySelector("#age_label")
    const surname = document.querySelector("#surname")
    const nameLabel = document.querySelector("#name_label")
    const surnameLabel = document.querySelector("#surname_label")
    const name = document.querySelector("#name")

    if (document.querySelector("#user").checked) {
        name.placeholder = "Inserisci il tuo nome";
        surnameLabel.style.display = "flex";
        nameLabel.innerHTML = "Nome"
        surname.style.display = "flex";
        title.style.display = "flex"
        age.required = true
        age.style.display = "flex"
    } else {
        name.placeholder = "Inserisci nome ristorante";
        nameLabel.innerHTML = "Nome ristorante";
        surnameLabel.style.display = "none";
        surname.style.display = "none";
        title.style.display = "none"
        age.required = false
        age.style.display = "none"
    }
}

