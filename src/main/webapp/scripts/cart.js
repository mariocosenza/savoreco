async function deleteProduct(obj, id, price) {
    try {
        const response = await fetch("/user/cart", {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            body: new URLSearchParams({
                foodId: id,
                delete: "true"
            }),
        });
        if (response.ok) {
            const sel =  document.querySelector("#price")
            const selPrice = parseFloat(sel.innerHTML)
            if(selPrice - parseFloat(price) <= 0) {
                location.reload();
            } else {
                sel.innerHTML = `${selPrice - parseFloat(price)}`
            }
            obj.closest("li").remove()
        } else {
            location.reload();
        }
    } catch (e) {
        window.location.href = "/home"
    }
}

async function addProduct (obj, id, price) {
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
            const sel =  document.querySelector("#price")
            const selPrice = parseFloat(sel.innerHTML)
            sel.innerHTML = `${selPrice + parseFloat(price)}`
        } else {
            location.reload();
        }
    } catch (e) {
        console.log(e)
        window.location.href = "/home"
    }
}


