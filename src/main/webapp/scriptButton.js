document.getElementById("pixelizate").addEventListener('click', async function () {
    if (document.getElementById('fileToUpload').value !== '') {

        console.log(document.getElementById('fileToUpload').value);
        let formData = new FormData();
        formData.append("file", document.getElementById('fileToUpload').files[0]);

        let response = await fetch('http://localhost:9090/pixelizator/Serv', {
            method: 'POST',
            body: formData,
            enctype: "multipart/form-data"
        });
        if (response.ok) {
            let text = await response.text();
            let img = document.getElementById("forOutput");
            img.src = 'data:image/png;base64,'+text;
            img.style.display = 'block'
            console.log(text);
        } else {
            console.log("error post method");
        }
    }
});