let xhr = new XMLHttpRequest();
document.getElementById("but").addEventListener("click", function () {
    if (document.getElementById("showOk").textContent === "not Ok click") {
        document.getElementById("showOk").textContent = "Ok click more"
        document.getElementById("im").src = "#";
        document.getElementById("im").style.display = 'none'

        printText();
        xhr.open('GET', 'http://localhost:9090/pixelizator/Serv');
        xhr.send();
    } else {
        document.getElementById("showOk").textContent = "not Ok click";
        document.getElementById("im").style.display = 'block'
        document.getElementById("im").src = 'http://localhost:9090/pixelizator/s.png';

        printText();
        xhr.open('GET', 'http://localhost:9090/pixelizator/Serv');
        xhr.send();
    }
});

function printText() {
    if (xhr.responseText !== '') {
        console.log('(' + xhr.responseText + ')');
    }
}
