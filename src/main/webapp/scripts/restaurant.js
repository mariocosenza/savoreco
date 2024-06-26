"use strict";

const regexId = /^\d+$/;

document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.add-to-cart');

    buttons.forEach(button => {
        button.addEventListener('click', async function () {
            const foodId = this.getAttribute('data-food-id');
            const quantityElement = this.closest('.foodBox').querySelector('.foodInfo p strong + span');

            if (!window.isLoggedIn) {
                const tooltip = this.closest('div').querySelector('.tooltiptext');
                tooltip.style.display = "flex";
                setTimeout(() => {
                    tooltip.style.display = "none";
                }, 3000);
                return;
            }

            if (!regexId.test(foodId)) {
                console.error('Errore di ID:', foodId);
                window.location.href = "/home";
                return;
            }

            try {
                const response = await fetch("/user/cart", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                    },
                    body: new URLSearchParams({
                        foodId: foodId,
                        add: "true"
                    }),
                });
                if (response.ok) {
                    startEffect(this);
                    console.log('Aggiunto al carrello');
                } else {
                    console.error('Errore nell\'aggiungere al carrello');
                }
            } catch (e) {
                console.error('Errore di rete:', error);
                window.location.href = "/home";
            }
        });
    });
});

function startEffect(button) {
    const effect = document.createElement('div');
    const tooltipDiv = button.closest('.tooltipDiv');
    effect.classList.add('effect');
    tooltipDiv.appendChild(effect);

    setTimeout(() => {
        effect.remove();
    }, 1500);
}