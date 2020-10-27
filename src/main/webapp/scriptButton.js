document.getElementById("pixelizate").addEventListener('click', async function () {
    if (document.getElementById('fileToUpload').value !== '') {

        console.log(document.getElementById('fileToUpload').value);
        let formData = new FormData();
        formData.append("file", document.getElementById('fileToUpload').files[0]);

        let response = await fetch('http://localhost:9090/pixelizator/Serv', {
            method: 'POST',
            body: formData,
            enctype: "multipart/form-data",
        });
        if (response.ok) {
            // let text = await response.text();
            let blob = await response.arrayBuffer();
            let len = blob.byteLength;
            let img = document.getElementById("forOutput");
            let img2 = document.getElementById("forInput");
            // img.src = 'data:image/png;base64,' + text;
            console.log(len);
            // img.src = 'data:image/png;base64,'
            let stringForSrc = 'data:image/png;base64,';
            for (let i = 0; i < len; i += 100000) {
                if (i + 100000 < len) {
                    console.log('if' + i);
                    stringForSrc = stringForSrc + btoa(String.fromCharCode.apply(null, new Uint8Array(blob.slice(i, i + 100000))));
                } else {
                    console.log('else' + i);
                    stringForSrc = stringForSrc + btoa(String.fromCharCode.apply(null, new Uint8Array(blob.slice(i, len))));
                }
            }
            // img.src = text;
            img.src = stringForSrc;
            console.log(img.src.length);
            console.log(img2.src.length);
            img.style.display = 'block'
        } else {
            console.log("error post method");
        }
    }
});