"use strict";

const regexId = /^\d+$/;

function initializeMap(lon, lat) {
    Radar.initialize('prj_live_pk_bc9bb756fa7f36a4b5b2ce09cd0c587f58ada95c');
    // create a new map
    let map
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
        map = Radar.ui.map({
            container: 'map',
            style: 'radar-dark-v1',
            center: [lon, lat],
            language: 'local',
            zoom: 11,
        });
    } else {
        map = Radar.ui.map({
            container: 'map',
            style: 'radar-default-v1',
            center: [lon, lat],
            language: 'local',
            zoom: 11,
        });
    }
    // create marker and add to map
    const marker = Radar.ui.marker({color: '#14512DFF'})
        .setLngLat([lon, lat])
        .addTo(map);
}


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
