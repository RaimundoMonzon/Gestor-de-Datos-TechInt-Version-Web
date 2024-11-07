window.onload = function () {
    const showPopUpFlag = document.getElementById("showPopUpFlag");

    if (showPopUpFlag && showPopUpFlag.value === "true") {
        showPopup(3000); // Tiempo de duración del popup en milisegundos.
    }
}

function showPopup(duration) {
    const popup = document.getElementById("popup");

    popup.style.display = "block";

    const loadingBarFill = popup.querySelector(".loading-bar");

    loadingBarFill.style.width = '0%';

    loadingBarFill.offsetHeight;

    loadingBarFill.style.transition = `width ${duration}ms linear`;

    loadingBarFill.style.width = '100%';

    setTimeout(() => {
        popup.remove();
    }, duration);
}
