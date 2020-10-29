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
            <img class="im" id="forInput" src="#" alt="imageInput">
            <img class="im" id="forOutput" src="" alt="imageOutput">
        </div>
        <script src="script.js"></script>
        <div id="buttons">
            <input type="file" accept="image/*" name="file" class="underButton" id="fileToUpload" onchange="readURL(this)">
            <p id="outNumber">10</p>
            <label for="inputNumber"></label>
            <input type="range" class="choiceNumber" id="inputNumber" min="1" max="100"  value="10">
            <button class="underButton" id="pixelizate"> pixelizate</button>
        </div>
        <div id="foot">
            <div id="infoBlock">
                <p class="info" id="name"></p>
                <p class="info" id="format"></p>
                <p class="info" id="size"></p>
            </div>
            <div>
                <label for="rectangle">rectangle</label>
                <input type="radio" name="shape" id="rectangle" checked>
                <label for="Triangle">triangle</label>
                <input type="radio" name="shape" id="Triangle">
            </div>
            <div id="downloads">
                <p>Download</p>
                <button id="download1" >png</button>
                <button id="download2" >jpeg</button>
                <button id="download3" >webp</button>
            </div>
        </div>
        <script src="scriptButton.js"></script>
    </body>
</html>