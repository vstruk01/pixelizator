let xhr = new XMLHttpRequest();
let formData;

xhr.onload = function (event) {
    console.log('(' + xhr.responseText + ')');
};

document.getElementById('sub').addEventListener('click', function () {
    console.log("hello submit");
    console.log(document.getElementById('fileToUpload').getAttribute("name"));
    console.log(document.getElementById('fileToUpload').getAttribute("type"));

    if (document.getElementById('fileToUpload').value !== '') {
        formData = new FormData(document.getElementById('myForm'));
        xhr.open('POST', 'http://localhost:9090/pixelizator/Serv', true);
        xhr.send(formData);
    }
});

document.getElementById("but").addEventListener("click", function () {
    if (document.getElementById("showOk").textContent === "not Ok click") {
        document.getElementById("showOk").textContent = "Ok click more"
        document.getElementById("im").src = "#";
        document.getElementById("im").style.display = 'none'

        xhr.open('GET', 'http://localhost:9090/pixelizator/Serv', true);
        xhr.send();
    } else {
        document.getElementById("showOk").textContent = "not Ok click";
        document.getElementById("im").style.display = 'block'
        document.getElementById("im").src = 'http://localhost:9090/pixelizator/s.png';

        xhr.open('GET', 'http://localhost:9090/pixelizator/Serv', true);
        xhr.send();
    }
});
