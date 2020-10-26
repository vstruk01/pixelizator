let xhr = new XMLHttpRequest();
let formData = new FormData();

xhr.onload = function (event) {
    console.log('(' + xhr.responseText + ')');
};



document.getElementById("pixelizate").addEventListener('click', async function () {
    if (document.getElementById('fileToUpload').value !== '') {

        console.log(document.getElementById('fileToUpload').value);
        let response = await fetch('http://localhost:9090/pixelizator/Serv', {
            method: 'POST',
            // headers: {
            //     'Content-Type': 'image'
            // },
            body: formData
        })
        if (response.ok) {
            let text = await response.text();
            let img = document.getElementById("forOutput");
            img.src = text;
            img.style.display = 'block'
            console.log(text);
        } else {
            console.log("error post method");
        }
        // xhr.open('POST', 'http://localhost:9090/pixelizator/Serv', true);
        // xhr.send(formData);
    }
});