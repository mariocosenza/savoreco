function countdown() {
    let seconds = 10;
    function tick() {
        const counter = document.getElementById("counter");
        seconds--;
        counter.innerHTML = "0:" + (seconds < 10 ? "0" : "") + String(seconds);
        if (seconds > 0) {
            setTimeout(tick, 1000);
        } else {
            window.location.href = "/home";
        }
    }
    tick();
}