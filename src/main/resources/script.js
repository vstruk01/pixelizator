let xhr = new XMLHttpRequest();
document.getElementById("but").addEventListener("click", function () {
    if (document.getElementById("showOk").innerHTML === "not Ok click") {
        document.getElementById("showOk").innerHTML = "Ok click more"
        document.getElementById("im").src = "#";
        document.getElementById("im").style = "display: none;"
    } else {
        document.getElementById("showOk").innerHTML = "not Ok click";
        document.getElementById("im").style = "display: block;"
        document.getElementById("im").src = "s.png";
    }
    xhr.open('GET', '/', false);
    xhr.send();
    console.log(xhr.responseText);
});
