window.onload = function(){
    var galleryArrows = document.querySelectorAll('.galleryArrows');
    var UpButton = document.getElementById('UpButton');
    galleryArrows.forEach(element => {
        element.addEventListener('click', movingImages)
    });

    UpButton.addEventListener('click', scrollToTop)
}

var actualImageId = 0;
var nextImageId = 0;
var imageMax = 5;
var imageMin = 1;

function movingImages(event){
    var actualImage = document.getElementById('actualImage');
    actualImageId = extraerNumeroDeArchivo(actualImage.src);

    if(event.target.id === 'nextImage'){
        if(actualImageId < imageMax){
            nextImageId = actualImageId + 1;
            actualImage.src = '/image/paisaje' + nextImageId + '.jpg';
        }
        else if (actualImageId === imageMax){
            actualImage.src = '/image/paisaje' + imageMin + '.jpg';
        }
    }
    else if (event.target.id === 'previousImage'){
        if(actualImageId > imageMin){
            nextImageId = actualImageId - 1;
            actualImage.src = '/image/paisaje' + nextImageId + '.jpg';
        }
        else if (actualImageId === imageMin){
            actualImage.src = '/image/paisaje' + imageMax + '.jpg';
        }
    }
}

function extraerNumeroDeArchivo(nombreArchivo) {
    return parseInt(nombreArchivo.replace("http://localhost:8080/image/paisaje", "").replace(".jpg", ""));
}

function scrollToTop() {
    document.body.scrollTop = 0; 
    document.documentElement.scrollTop = 0; 
}
