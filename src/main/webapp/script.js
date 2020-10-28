function readURL(input) {
    if (input.files && input.files[0]) {
        // formData.append("file", input.files[0]);
        let reader = new FileReader();
        reader.onloadend = function (e) {
            let img = document.getElementById("forInput");
            img.src = reader.result;
            img.style.display = 'block'
        };
        reader.readAsDataURL(input.files[0]);
        console.log(input.files[0]);
    }
}