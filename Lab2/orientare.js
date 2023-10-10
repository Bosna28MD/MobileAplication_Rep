//Comment...
window.addEventListener("deviceorientation", 
on_device_orientation);
function on_device_orientation(evt)
{
var alpha = evt.alpha;
var beta = evt.beta;
var gamma = evt.gamma;
document.getElementById("a").innerHTML = "alpha = "+alpha;
document.getElementById("b").innerHTML = "beta = "+beta;
document.getElementById("c").innerHTML = "gamma = "+gamma;
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
var centru = {x:canvas.width/2, y:canvas.height/2}
var latura_patrat = 50;
//context.setTransform(1, 0, 0, 1, 0, 0);
context.clearRect(0,0,canvas.width, canvas.height);
//context.translate(centru.x/2, centru.y/2);
//context.rotate(alpha * Math.PI / 180);
context.beginPath();
//context.fillStyle = "#FF0000";
//context.rect(centru.x,centru.y,latura_patrat*Math.abs(gamma/180),latura_patrat*Math.abs(gamma/180));
context.rect(canvas.width/2,canvas.height/2,
300,300);
/* context.rect(centru.x/2,centru.y/2, 
latura_patrat*(+Math.abs(gamma/180))%canvas.width/2, latura_patrat*(+Math.abs(gamma/180))%canvas.height/2 ); */

context.stroke();
}
