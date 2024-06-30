"use strict";

const regexId = /^\d+$/;

async function changeState(userId, mode, element) {
    if ((!regexId.test(userId)) || ((mode !== "delete") && (mode !== "recover"))) {
        console.error('Errore di Input:');
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
            const line = document.getElementById(`${userId}`);
            element.style.display = "none"
            line.className = ((mode === "delete") ? 'deleted-users' : 'active-users');
        }
    } catch (e) {
        console.error("Error submitting form", e);
    }
}
