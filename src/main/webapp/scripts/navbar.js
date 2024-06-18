function toggleMenu(user) {
    if(user) {
        document.querySelector("#userDropDown").style.display = "block";
    } else {
        document.querySelector("#sellerDropDown").style.display = "block";
    }

}


window.onclick = function(event) {
    if (!event.target.matches(".dropbtn")) {
        const dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            const openDropdown = dropdowns[i];
            if (openDropdown.style.display === "block") {
                openDropdown.style.display = "none";
            }
        }
    }
}