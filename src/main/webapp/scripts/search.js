"use strict";

function toggleCategory(obj, className) {
    const divs = document.querySelectorAll(".filter");
    const elementCategory = document.querySelectorAll(".resultCategory" + className);
    const resultElements = document.querySelectorAll(".result");

    if(obj.style.borderColor === "var(--md-sys-color-secondary-container)" || obj.style.borderColor === "red") {
        for (const element of elementCategory) {
            element.style.display = "flex"
        }
        obj.style.borderColor = "green"
    } else {
        for (const element of elementCategory) {
            element.style.display = "none"
        }
        obj.style.borderColor = "red"
    }

    divs.forEach(d => {
        if(d !== obj && d.style.borderColor === "var(--md-sys-color-secondary-container)") {
            for (const element of document.querySelectorAll(".resultCategory" + d.id.replace('searchCategory',''))) {
                element.style.display = "none"
            }
            d.style.borderColor = "red"
        }
    })

    let red = false;
    let green = false;

    for (const element of divs) {
        if(element.style.borderColor === "red") {
            red = true;
        }
        else if(element.style.borderColor === "green") {
            green = true
        }
    }

    if(red && green)
        return;

    for (const element of resultElements) {
        element.style.display = "flex"
    }

    for (const element of divs) {
        element.style.borderColor = "var(--md-sys-color-secondary-container)"
    }
}


function toggleSelect(obj) {
    const className = obj.options[obj.selectedIndex].value
    const elementCategory = document.querySelectorAll(".resultCategory" + className);
    const resultElements = document.querySelectorAll(".result");

    for (const element of elementCategory) {
        element.style.display = "flex"
    }

    if(obj.options[obj.selectedIndex].value !== "noCategory") {
        for (const element of resultElements) {
            if(!element.classList.contains("resultCategory" + className))
                element.style.display = "none"
        }
    }
}