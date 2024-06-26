"use strict";

const regexId = /^\d+$/;

document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.add-to-cart');

    buttons.forEach(button => {
        button.addEventListener('click', function() {
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

            startEffect(this);

            fetch('/restaurant', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ foodId: foodId })
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Aggiunto al carrello');
                        let quantity = parseInt(quantityElement.textContent);
                        quantity -= 1;
                        quantityElement.textContent = quantity;
                        if (quantity <= 0) {
                            this.disabled = true;
                        }
                    } else {
                        console.error('Errore nell\'aggiungere al carrello');
                    }
                })
                .catch(error => {
                    console.error('Errore di rete:', error);
                    window.location.href = "/home";
                });
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