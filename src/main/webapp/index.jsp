<html lang="en">
    <head>
        <title>VV_Teaching</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        <meta name="description" content="VV_Teaching">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            <input type="file" accept="image/*" name="file" class="underButton" id="fileToUpload" onchange="readURL(this)">
            <p id="outNumber">10</p>
            <label for="inputNumber"></label>
            <input type="range" class="choiceNumber" id="inputNumber" min="1" max="100"  value="10">
            <button class="underButton" id="pixelizate"> pixelizate</button>
        </div>
        <p class="info" id="name"></p>
        <p class="info" id="format"></p>
        <p class="info" id="size"></p>
        <a href="#" id="download1" download="image.png">png</a>
        <a href="#" id="download2" download="image.jpeg">jpeg</a>
        <a href="#" id="download3" download="image.webp">webp</a>

        <script src="scriptButton.js"></script>
    </body>
</html>