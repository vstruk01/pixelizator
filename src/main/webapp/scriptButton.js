document.getElementById("pixelizate").addEventListener('click', async function () {
    if (document.getElementById('fileToUpload').value !== '' && document.getElementById("inputNumber").value !== '') {
        let formData = new FormData();
        formData.append("file", document.getElementById('fileToUpload').files[0]);

        let ss = document.getElementById('fileToUpload').value.split('.');

        let response = await fetch('http://localhost:9090/pixelizator/Serv',{
            method: 'POST',
            body: formData,
            enctype: "multipart/form-data",
            headers: {
                'size': document.getElementById("inputNumber").value,
                'format': ss[ss.length - 1]
            }
        });
        if (response.ok) {
            let blob = await response.blob();
            let img = document.getElementById("forOutput");
            let reader = new FileReader();
            reader.onload = function(event){
                img.src =   event.target.result;
                document.getElementById("download1").href = event.target.result;
                document.getElementById("download2").href = event.target.result;
                document.getElementById("download3").href = event.target.result;
            };
            reader.readAsDataURL(blob);
            img.style.display = 'block'
        } else {
            console.log("error post method");
        }
    }
});

document.getElementById("inputNumber").addEventListener('mousemove', function () {
    document.getElementById("outNumber").textContent = document.getElementById("inputNumber").value
})