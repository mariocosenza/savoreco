"use strict";

const regexName = /^[a-zA-Z][a-zA-Z0-9-_\s]{2,24}$/;
const regexDescription = /^.{2,2000}$/;
const regexCategory = /^[a-zA-Z\s]{2,25}$/;
const regexCost = /^\d+(\.\d{1,2})?$/;
const regexAllergens = /^[A-Za-z]+(?:,\s*[A-Za-z]+){0,49}$/;
const regexGreenPoints = /^\d{1,2}$/;
const regexQuantity = /^\d{1,5}$/;

async function submitFoodUpdate(foodId) {
    const form = document.getElementById(`form${foodId}`);
    if (validateFood(foodId)) {
        const imageFile = form.querySelector('input[type="file"]').files[0];
        if (imageFile) {
            const imageFormData = new FormData();
            const maxSizeInBytes = 10 * 1024 * 1024; // 10 MB

            if (imageFile.type !== "image/png") {
                alert("Please select a PNG image file.");
                formError(form);
                return;
            }

            if (imageFile.size > maxSizeInBytes) {
                alert("The file size should not exceed 10 MB.");
                formError(form);
                return;
            }

            const mode = "restaurant";
            imageFormData.append("mode", mode);
            imageFormData.append("image", imageFile);
            const imageResponse = await fetch("/fileUpload", {
                method: "POST",
                body: imageFormData
            });

            if (!imageResponse.ok) {
                throw new Error("Failed to upload image");
            }
            form.querySelector("#imageUrl").value = await imageResponse.text();
        }

        const formData = new FormData(form);
        try {
            formData.delete('image');
            formData.append("id", foodId);
            formData.append("mode", "saveFood");
            const response = await fetch("/seller/sellerRestaurant", {
                method: "POST",
                body: JSON.stringify(Object.fromEntries(formData)),
                contentType: "application/json"
            });

            if (!response.ok){
                formError(form);
            }
        } catch (e) {
            console.error("Error submitting form", e);
            formError(form);
        }
    } else {
        formError(form);
    }
}

function formError(foodId) {
    const form = document.getElementById(`form${foodId}`);
    form.querySelector("button.save").disabled = true;
    form.querySelectorAll("label").forEach(label => label.style.color = "var(--md-sys-color-error)");
}



function validateFood(foodId) {
    const form = document.getElementById(`form${foodId}`);
    form.querySelectorAll("label").forEach(label => label.style.color = "var(--md-sys-color-on-background)");
    let error = false;

    form.querySelectorAll("input, textarea").forEach(element => {
        const label = form.querySelector(`label[for="${element.id}"]`);
        if (!label) return;
        let isValid = true;

        if (element.id === "fname") {
            isValid = regexName.test(element.value);
        } else if (element.id === "fdescription") {
            isValid = regexDescription.test(element.value);
        } else if (element.id === "fcategory") {
            isValid = regexCategory.test(element.value);
        } else if (element.id === "price") {
            isValid = regexCost.test(element.value);
        } else if (element.id === "allergens") {
            isValid = regexAllergens.test(element.value);
        } else if (element.id === "greenPoints") {
            isValid = regexGreenPoints.test(element.value);
        } else if (element.id === "quantity") {
            isValid = regexQuantity.test(element.value);
        }

        if (isValid) {
            label.style.color = "var(--md-sys-color-primary)";
        } else {
            label.style.color = "var(--md-sys-color-error)";
            error = true;
        }
    });

    form.querySelector("button.save").disabled = error;

    return !error;
}











async function submitRestaurantUpdate() {
    const form = document.querySelector(`#formRest`);
    if (validateRestaurant()) {
        const address = searchResult();
        if(address !== undefined) {
            form.querySelector("#lat").value = address.latitude
            form.querySelector("#lon").value = address.longitude
            form.querySelector("#postal").value = address.postalCode + ` ${address.postalCode === undefined? "00042" : address.postalCode}`
            form.querySelector("#address").value = address.street + ` ${address.number === undefined? "" : address.number}`
            form.querySelector("#city").value = address.city
        }
        const imageFile = form.querySelector('input[type="file"]').files[0];
        if (imageFile) {
            const imageFormData = new FormData();
            const maxSizeInBytes = 10 * 1024 * 1024; // 10 MB

            if (imageFile.type !== "image/png") {
                alert("Please select a PNG image file.");
                formError('#formRest');
                return;
            }

            if (imageFile.size > maxSizeInBytes) {
                alert("The file size should not exceed 10 MB.");
                formError('#formRest');
                return;
            }

            const mode = "restaurant";
            imageFormData.append("mode", mode);
            imageFormData.append("image", imageFile);
            const imageResponse = await fetch("/fileUpload", {
                method: "POST",
                body: imageFormData
            });

            if (!imageResponse.ok) {
                throw new Error("Failed to upload image");
            }

            form.querySelector("#logoUrl").value = await imageResponse.text();
        }

        const formData = new FormData(form);
        try {
            formData.delete('logo');
            formData.append("mode", "modifyRestaurant");
            const response = await fetch("/seller/sellerRestaurant", {
                method: "POST",
                body: JSON.stringify(Object.fromEntries(formData)),
                contentType: "application/json"
            });

            if (!response.ok) {
                formError('Rest');
            }
        } catch (e) {
            console.error("Error submitting form", e);
            formError('Rest');
        }
    } else {
        formError('Rest');
    }
}


function validateRestaurant() {
    const form = document.querySelector(`#formRest`);
    form.querySelectorAll("label").forEach(label => label.style.color = "var(--md-sys-color-on-background)");
    let error = false;

    form.querySelectorAll("label").forEach(label => {
        const element = form.querySelector(`#${label.htmlFor}`);
        if (element.id === "name") {
            if (regexName.test(element.value)) {
                label.style.color = "var(--md-sys-color-primary)";
            } else {
                label.style.color = "var(--md-sys-color-error)";
                error = true;
            }
        } else if (element.id === "description") {
            if (regexDescription.test(element.value)) {
                label.style.color = "var(--md-sys-color-primary)";
            } else {
                label.style.color = "var(--md-sys-color-error)";
                error = true;
            }
        } else if (element.id === "category") {
            if (regexCategory.test(element.value)) {
                label.style.color = "var(--md-sys-color-primary)";
            } else {
                label.style.color = "var(--md-sys-color-error)";
                error = true;
            }
        } else if (element.id === "deliveryCost") {
            if (regexCost.test(element.value)) {
                label.style.color = "var(--md-sys-color-primary)";
            } else {
                label.style.color = "var(--md-sys-color-error)";
                error = true;
            }
        } else  if (element.value === "" && element.id !== "logo") {
            error = true;
        }
    });

    if (!error) {
        form.querySelector("button.modify").disabled = error;
    }

    return !error;
}


