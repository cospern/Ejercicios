//Método abreviado para ejecutar el código después de cargar la página.
$(function () {
  /*Ejercicio 1: con la función toggleClass, añado la clase ,si no existe, para el elemento seleccionado. 
    En caso de tenerla, la elimina.*/
  $("li.country").click(function () {
    $(this).toggleClass("enabled");
  });

  /*Ejercicio 2: con el selector $("a" , this) selecciono los hijos "a" del segundo parámetro */
  $("#Spain , #USA").click(function () {
    alert($("a", this).text());
  });

  /*Ejercicio 3: Creo dos funciones diferentes. Una para cada botón en función de si el elemento boton es hijo
  de un continente u otro.*/
  $("#Europe .remove").click(function () {
    $("#Europe .enabled").remove();
  });

  $("#NorthAmerica .remove").click(function () {
    $("#NorthAmerica .enabled").remove();
  });

  /*Ejercicio 4: 
  Se seleccionan todos los elementos que no contienen el valor actual del elemento de entrada. 
  La parte $(this).val() se utiliza para obtener el valor actual del elemento de entrada que desencadenó el evento.
  Con hide() escondo todos esos elementos que no cunmple la busqueda realizada con el :not(:contains)*/

  $(".q").keyup(function(){
    $(".country a:not(:contains("+$(this).val()+"))").hide();
  })

  /*Ejercicio 5: La función "reloadCountries" se activa cada vez que se hace clic en el botón "reload" para un continente específico. jQuery para realizar una solicitud GET al archivo "getCountries.php". Una vez que se recibe la respuesta, se parsea y si el resultado es exitoso, se vacía la lista de países existente en el continente seleccionado y se añaden los nuevos países recibidos en la respuesta. */

  function reloadCountries(continent) {
    $.get(`http://localhost/getCountries.php?continent=${continent}`)
      .then(data => {
        let parsedData = JSON.parse(data);
        if (parsedData.result) {
            $(`#${continent} .countryList`).empty();
            parsedData.result.forEach((country) => {
                var template = $(`
                    <li class="country inline-block">
                        <a href="#" class="inline-block">${country}</a>
                    </li>
                `);
                var el = $(`#${continent} .countryList`);
                el.append(template)
            });
        }
        $("li.country").click(function (e) {
            $(e.target).closest("li").toggleClass("enabled")
        });
      });
  }
  
  $("#NorthAmerica .reload").click(() => {
    reloadCountries("NorthAmerica");
  });
  
  $("#Europe .reload").click(() => {
    reloadCountries("Europe");
  });

});







