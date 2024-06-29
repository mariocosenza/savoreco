"use strict";

const regexId = /^\d+$/;

async function updateStatus(mode, purchId) {
    if ((!regexId.test(purchId))||((mode !== "sendRider")&&(mode !== "arrived")&&(mode !== "confirmed"))) {
        console.error('Errore di Input:');
        window.location.href = "/home";
        return;
    }

    const formData = new FormData();
    try {
        formData.append("id", purchId);
        formData.append("mode", mode);

        var path = (mode === "confirmed")? "/user/userOrders" : "/seller/restaurantOrders";

        const response = await fetch(path, {
            method: "POST",
            body: JSON.stringify(Object.fromEntries(formData)),
            contentType: "application/json"
        });

        if (response.ok) {
            location.reload();
        }
    } catch (e) {
        console.error("Error submitting form", e);
    }
}
