async function deleteProduct(obj, id) {
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
            console.log("ok")
        } else {

        }
    } catch (e) {
        window.location.href = "/home"
    }
}

function addProduct (obj, id) {

}


function lessProduct (obj, id) {

}
