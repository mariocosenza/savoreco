"use strict";

const regexName = /^[a-zA-Z][a-zA-Z0-9-_\s]{2,24}$/;
const regexDescription = /^.{2,2000}$/;
const regexDeliveryCost = /^\d+(\.\d{1,2})?$|^\d+(,\d{1,2})?$/;
const regexCategory = /^[a-zA-Z\s]{2,25}$/;

async function submitRegistration() {
    if (validate()) {
        const address = searchResult()
        document.querySelector("#lat").value = address.latitude
        document.querySelector("#lon").value = address.longitude
        document.querySelector("#postal").value = ` ${address.postalCode === undefined ? "" : address.postalCode}`
        document.querySelector("#address").value = address.street + ` ${address.number === undefined ? "" : address.number}`
        document.querySelector("#city").value = address.city

        const imageFile = document.querySelector('input[type="file"]').files[0];
        if (imageFile) {
            const imageFormData = new FormData();
            const maxSizeInBytes = 10 * 1024 * 1024; //10 MB

            if (imageFile.type !== "image/png") {
                alert("Please select a PNG image file.");
                formError();
                return;
            }

            if (imageFile.size > maxSizeInBytes) {
                alert("The file size should not exceed 10 MB.");
                formError();
                return;
            }

            const mode = "restaurant";
            imageFormData.append("mode", mode)
            imageFormData.append("image", imageFile);
            const imageResponse = await fetch("/fileUpload", {
                method: "POST",
                body: imageFormData
            });

            if (!imageResponse.ok) {
                throw new Error("Failed to upload image");
            }

            document.querySelector("#imageUrl").value = await imageResponse.text();
        }

        const formData = new FormData(document.querySelector("#form"))
        try {
            formData.delete('image');
            const response = await fetch("/seller/addRestaurant", {
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
        } else if (element.value === "" && element.id !== "image") {
            error = true
        }
    }

    if (!error) {
        document.querySelector("button").disabled = false
    }

    return !error
}