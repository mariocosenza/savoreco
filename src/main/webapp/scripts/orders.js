"use strict";

const regexId = /^\d+$/;

async function updateStatus(mode, purchId) {
    if ((!regexId.test(purchId)) ||
        ((mode !== "payed") && (mode !== "delivering") && (mode !== "delivered") &&
            (mode !== "confirmed") && (mode !== "pending") && (mode !== "canceled"))) {
        console.error('Errore di Input:');
        window.location.href = "/home";
        return;
    }
    const formData = new FormData();
    try {
        formData.append("id", purchId);
        formData.append("mode", mode);

        const response = await fetch("/seller/restaurantOrders", {
            method: "POST",
            body: JSON.stringify(Object.fromEntries(formData)),
            contentType: "application/json"
        });

        if (response.ok) {
            document.getElementById(purchId).querySelector('p').textContent = mode;
            document.getElementById(purchId).querySelector('select').disabled = true;
        } else {
            document.getElementById(purchId).querySelector('strong').style.color = "var(--md-sys-color-error)";
        }
    } catch (e) {
        console.error("Error submitting form", e);
    }
}
