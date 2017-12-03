var autoractual;
var c;
var ctx;
var actual;
var nombreactual;
var modal;

//Función para guardar un nuevo blueprint
save=function(){
    var promesa= $.ajax({
        url: "/blueprints/"+autoractual+"/"+nombreactual,
        type: 'PUT',
        data: JSON.stringify(actual),
        contentType: "application/json"
    });
    promesa.then(
        function(){
            dibujar();
            getBlueprintsByAuthor();
        }
    );
    
};

//Función para crear un nuevo blueprint
crearnuevo = function () {
    modal.style.display = "none";
    var nombre = document.getElementById("newName").value;
    nuevo = new Blueprint(nombre, autoractual);
    var crear=$.ajax({
        url: "/blueprints",
        type: 'POST',
        data: JSON.stringify(nuevo),
        contentType: "application/json"
    });
    crear.then(
       function(){
           getBlueprintsByAuthor();
       }
    );
};



//función para mostrar la tabla de blueprints del autor seleccionado
getBlueprintsByAuthor = function () {
    autoractual = document.getElementById('authorName').value;
    var putPromise2 = $.get("/blueprints/" + autoractual, function (data) {
        $("#tablenum").empty();
        $("#tablablueprints").empty();
        $("#tablablueprints").append("<tr><th>bpname</th><th>size(points)</th></tr>");
        for (i = 0; i < data.length; i++) {
            $("#tablablueprints").append("<tr>");
            $("#tablablueprints").append("<td id=" + data[i].name + " class='draggable' >" + data[i].name + "</td>")
            $(".draggable").draggable({
                helper: 'clone'
            });
            $("#tablablueprints").append("<td  class='draggable2' >" + data[i].points.length + "</td>");


            $("#tablablueprints").append("</tr>");

        }

    });
    putPromise2.then(
            function () {
            },
            function () {
                $("#tablenum").empty();
                $("#tablablueprints").empty();
                $("#tablenum").append("No se ha seleccionado un autor valido");
            }

    );

};

//función para eliminar un blueprint
eliminarElemento = function () {

    var putPromise = $.ajax({
        url: '/blueprints/' + autoractual+"/"+nombreactual,
        type: 'DELETE'
    });
    putPromise.then(
            function () {
                getBlueprintsByAuthor();
            }

    );
};


//función para dibujar un elemento arrastrado
dibujar = function () {
    
    $("#titleofcanvas").empty();
    $("#titleofcanvas").append("Editing: "+nombreactual);
    //borrar el canvas
    c.width = c.width;
    var puntos;
    var getPromise = $.get("/blueprints/" + autoractual + "/" + nombreactual, function (data) {
        puntos=data.points;
        if(data.points.length>0){
            ctx.moveTo(data.points[0].x, data.points[0].y);
            for (i = 1; i < data.points.length; i++) {
                ctx.lineTo(data.points[i].x, data.points[i].y);
            }
            ctx.stroke();
        }
    });
    getPromise.then(
            function () {
                actual= new Blueprint(nombreactual,autoractual);
                actual.setPuntos(puntos);
            }
            
            );
    

};


$(document).ready(
        function () {

            c = document.getElementById("Canvas");
            ctx = c.getContext("2d");
            $("#basura").droppable({
                drop: function (event, ui) {
                    nombreactual = ui.draggable.attr("id");

                    eliminarElemento();
                }

            });

            $("#Canvas").droppable({
                drop: function (event, ui) {
                    nombreactual = ui.draggable.attr("id");
                    dibujar();

                }});
            function getMousePos(canvas, evt) {
                var rect = canvas.getBoundingClientRect();
                return {
                    x: evt.clientX - rect.left,
                    y: evt.clientY - rect.top
                };
            }
            
            c.addEventListener('mousedown', function (evt) {
                var mousePos = getMousePos(c, evt);
                var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y;
                x = mousePos.x;
                y = mousePos.y;
                ctx.lineTo(x,y);
                ctx.stroke();
                actual.agregarPunto(x,y);
            }, false);
            
            //funciones del modal
            modal = document.getElementById('myModal');
            var btn = document.getElementById("btn");
            var span = document.getElementsByClassName("close")[0];
            btn.onclick = function () {
                modal.style.display = "block";
            }
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }


        }


);

