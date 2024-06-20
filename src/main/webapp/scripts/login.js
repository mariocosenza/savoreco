"use strict";
const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;


function validate() {
    document.querySelectorAll("label").forEach(e => e.style.color = "var(--md-sys-color-on-background)")

    let error = false
        for (const arg of document.querySelectorAll("label")) {
            const element = document.getElementById(arg.htmlFor)
            if (element.type !== "radio" && element.value !== "") {
                if (element.id === "email") {
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

                }
            }
            else if(element.value === "") {
                error = true
            }
        }

    if (!error) {
        document.querySelector("button").disabled = false
    }

    return !error
}

