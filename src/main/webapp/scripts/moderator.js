"use strict";

const regexId = /^\d+$/;

async function changeState(userId, mode) {
    if ((!regexId.test(userId))||((mode !== "delete")&&(mode !== "recover"))) {
        console.error('Errore di ID:', userId);
        window.location.href = "/home";
        return;
    }

    const formData = new FormData();
    try {
        formData.append("mode", mode);
        formData.append("id", userId);
        const response = await fetch("/moderator/moderatorPage", {
            method: "POST",
            body: JSON.stringify(Object.fromEntries(formData)),
            contentType: "application/json"
        });

        if (response.ok) {
            location.reload()
        }
    } catch (e) {
        console.error("Error submitting form", e);
    }
}
