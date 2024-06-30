"use strict";


function deleteProduct(obj, id, price, quantity) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/user/cart", true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const sel = document.querySelector("#price");
                const selPrice = parseFloat(sel.innerHTML);
                if (selPrice - (price * quantity) <= 0) {
                    location.reload();
                } else {
                    sel.innerHTML = `${selPrice - price * quantity}`;
                }
                obj.closest("li").remove();
            } else {
                location.reload();
            }
        }
    };
    xhr.onerror = function () {
        window.location.href = "/home";
    };
    xhr.send(new URLSearchParams({
        foodId: id,
        delete: "true"
    }).toString());

    setTimeout(() => window.location.href = "/home", 15000)
}

async function addProduct(obj, id, price) {
    try {
        const response = await fetch("/user/cart", {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            body: new URLSearchParams({
                foodId: id,
                add: "true"
            }),
        });
        if (response.ok) {
            let num = parseInt(obj.parentElement.children[0].innerHTML)
            num++
            obj.parentElement.children[0].innerHTML = `${num}`
            const sel = document.querySelector("#price")
            const selPrice = parseFloat(sel.innerHTML)
            sel.innerHTML = `${selPrice + parseFloat(price)}`
        } else {
            location.reload();
        }
    } catch (e) {
        window.location.href = "/home"
    }
}


