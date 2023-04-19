/* El codigo js solo se carga en el momento que el documento se carge completamente. */
window.onload = function () {

  /*1)*/
  document.getElementById("btnColor").addEventListener("click", cambia_color);

  /* Con la siguiente función, guardo en una variable el contenido del texto del input y hago la conversión castellano/inglés en función del color recogido.*/
  function cambia_color() {
    let color_elegido = document.getElementById("txtColor").value;

    switch (color_elegido) {
      case "rojo":
        color_elegido = "red";
        break;
      case "verde":
        color_elegido = "green";
        break;
      case "azul":
        color_elegido = "blue";
        break;
      case "amarillo":
        color_elegido = "yellow";
        break;

      /* Apartado 2 del ejercicio. Creo la alerta en caso de que no se escriba uno de los 4 colores solicitados por el enunciado. También reseteo el color previamente, ya que en caso de no hacerlo, escribir cualquier color escrito en inglés hará que este se modifique igualmente*/
      default:
        color_elegido = "";
        alert("color elegido no soportado");
    }

    /* Guardo en una varable una lista con los elementos del DOM seleccionados por la clase */
    let elementos_a_cambiar =
      document.getElementsByClassName("color_submarino");

    /* Recorro esa lista de elementos y a cada uno de ellos le asigno el color que hemos introducido.*/
    for (let i = 0; i < elementos_a_cambiar.length; i++) {
      elementos_a_cambiar[i].style.color = color_elegido;
    }
  }

  /*----------------------------------------------------------------------------------------*/

  /* 2) */
  /* Guardo en variables tanto el h4 como el div*/
  let pinchame = document.querySelector("#heaCumbres");
  let miTabla = document.querySelector("#cumbres");

  /* Guardo en variable un array de las propiedades de la primera posición del objeto cumbres.(podría elegir cualquier posición ya que tendrán las mismas llaves a mi entender(Nombre,Altura y país)) */
  let encabezados = Object.keys(cumbres[0]);

  /* Añado el manejador correspondiente al pulsar el botón izquierdo del ratón */
  pinchame.addEventListener("click", obtenerTabla);

  /* Creo una tabla y una fila */
  function obtenerTabla() {
    let tabla = document.createElement("table");
    let filaEncabezado = document.createElement("tr");

    /* Por cada propiedad del array encabezados (declarado en la linea 47) ejecuto una función flecha, la cual va a crear un encabezado de tabla junto a su texto, cuyo valor se corresponde al valor de cada propiedad. Tabién añado en el arbol de nodos cada uno de los elementos creados.*/
    encabezados.forEach((textoEncabezado) => {
      let encabezado = document.createElement("th");
      let textNode = document.createTextNode(textoEncabezado);
      encabezado.appendChild(textNode);
      filaEncabezado.appendChild(encabezado);
    });

    tabla.appendChild(filaEncabezado);

    /* Por cada cumbre en el array cumbres creo una fila*/
    cumbres.forEach((cumbre) => {
      let fila = document.createElement("tr");
      /* Por cada valor de las propiedades de cumbre, creo una celda de la tabla con su texto correspondiente. Añado los elementos creados al arbol de nodos. */
      Object.values(cumbre).forEach((text) => {
        let celda = document.createElement("td");
        let textNode = document.createTextNode(text);
        celda.appendChild(textNode);
        fila.appendChild(celda);
      });
      tabla.appendChild(fila);
    });
    miTabla.appendChild(tabla);

    /* Elimino el manejador para que al pulsar el boton por segunda vez, no se vuelva a ejecutar el código.*/
    this.removeEventListener("click", obtenerTabla);
  }

  /* ------------------------------------------------------------------------------------ */

  /* 3) */

  document.querySelector("#btnAnadir").addEventListener("click", añadir_tarea);
  document.querySelector("#btnBorrar").addEventListener("click", borrame);
  
  /* En la siguiente función recojo el valor del campo de texto y creo un elemento li que será añadido como nodo hijo al <ul> lista de actividades  */
  function añadir_tarea() {
    let texto_tarea = document.querySelector("#txtAnadir").value;
    let lista_actividades = document.querySelector("#listaActividades");
    let elemento_li = document.createElement("li");

    /*A continuación, condiciono el añadir una activadad a la lista de tareas siempre y cuando el cuadro de texto no esté vacío*/
    if (texto_tarea != "") {
      elemento_li.innerHTML = texto_tarea;
      lista_actividades.appendChild(elemento_li);
    }
    /* Aunque no lo pide explicitamente el ejercicio, por funcionalidad, pongo en blanco el recuadro del input*/
    document.querySelector("#txtAnadir").value = "";
  }

  /* La siguiente función va a recorrer cada uno los elementos li, que será borrado en caso de que el texto de ese li, coincida con el texto recogido del botoón,  */
  function borrame() {
    let texto_borrar = document.querySelector("#txtBorrar").value;
    let tareas = document.querySelectorAll("li");
    tareas.forEach((tarea) => {
      if (tarea.textContent == texto_borrar) {
        tarea.remove();
      }
      document.querySelector("#txtBorrar").value="";
    });
  }

  /*-------------------------------------------------------------------------------------------------------------*/

  /* 4) */

  let euro = document.getElementById("txtEuro");
  let dollar = document.getElementById("txtDolar");

  /* Al levantar cualquier tecla pulsada se ejecuta la siguientes función*/
  euro.addEventListener("keyup", euroToDollar => {
    /*Solo se ejecuta si es un número */
    if (!isNaN(euro.value)) {
      /*Guardamos en una variable el valor del texto introducido en el input del euro, lo multiplicamos por 1.12 y se lo asignamos al input del dollar. Añadimos toFixed(2) para que no devuelva un resultado con mas de dos decimales. */
      let euro_value = euro.value;
      let dollar_value = euro_value * 1.12;
      dollar.value = dollar_value.toFixed(2);

      /*Si no es un número, actualizamos el valor del input text a una cadena vacía*/
    } else {
      dollar.value = "";
    }
  });

  /*Repetimos todo lo anterior pero cambiando los nombres para pasar de dolar a euro y realizando una operación de división en lugar de multiplicación.*/
  dollar.addEventListener("keyup", dollarToEuro => {
    if (!isNaN(dollar.value)) {
      let dollar_value = dollar.value;
      let euro_value = dollar_value / 1.12;
      euro.value = euro_value.toFixed(2);
    } else {
      euro.value = "";
    }
  });
};
