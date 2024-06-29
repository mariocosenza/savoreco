"use strict";

function startEffect(button) {
    const effect = document.createElement('div');
    const tooltipDiv = button.closest('.tooltipDiv');
    effect.classList.add('effect');
    tooltipDiv.appendChild(effect);

    setTimeout(() => {
        effect.remove();
    }, 1500);
}