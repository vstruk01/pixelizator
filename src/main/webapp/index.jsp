<html lang="en">
    <head>
        <title>VV_Teaching</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        <meta name="description" content="VV_Teaching">
        <link rel="stylesheet" href="style.css">
        <link rel="icon" type="image/x-icon" href="s.png">
    </head>
    <body>
        <div id="master">Pixelizator</div>
        <div class="contentImg">
            <img class="im" id="forInput" src="#">
            <img class="im" id="forOutput" src="#">
        </div>
        <script src="script.js"></script>
        <div id="buttons">
            <input type="file" class="underButton" id="fileToUpload" onchange="readURL(this)">
            <button class="underButton" id="pixelizate"> pixelizate</button>
        </div>
        <script src="scriptButton.js"></script>
    </body>
</html>