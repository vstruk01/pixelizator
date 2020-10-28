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
            let blob = await response.blob();
            let img = document.getElementById("forOutput");
            let img2 = document.getElementById("forInput");
            let reader = new FileReader();
            reader.onload = function(event){
                img.src =   event.target.result
            };
            reader.readAsDataURL(blob);

            console.log(img.src.length);
            console.log(img2.src.length);
            if (img.src === img2.src) {
                console.log("yes")
            }
            console.log(img.src);
            img.style.display = 'block'
        } else {
            console.log("error post method");
        }
    }
});