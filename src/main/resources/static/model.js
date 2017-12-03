
function Blueprint(nombre,autor) {

    this.author=autor;
    this.name = nombre;
    this.points=[];
}


Blueprint.prototype.agregarPunto=function(x,y){
    this.points.push({"x":x,"y":y});
};


Blueprint.prototype.setPuntos=function(puntos){
    this.points=puntos;
};

$(document).ready(
        function () {
                        

        }
);

