function changeColor()
{
    var lastColorIndex = localStorage.getItem('lastColorIndex') || -1;
  var randomColor = -1;
    while(lastColorIndex == randomColor || randomColor === -1) {        
  randomColor = Math.floor(Math.random() * color.length);
        console.log('LastIndex: ' + lastColorIndex + ',RandomColor: ' + randomColor);
    };
    localStorage.setItem('lastColorIndex',randomColor);
    //console.log(randomColor);
  console.log(color[randomColor]);
    document.getElementsByTagName("body")[0].style.backgroundColor=color[randomColor];
};
