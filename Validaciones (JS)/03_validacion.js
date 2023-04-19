//Hacemos que todo el exript se ejecute al cargar por completo el html.
window.onload = function () {


  function error(element, mensaje) {
    let contenedor = document.getElementById("errores");
    let parrafo = document.createElement("p");
    parrafo.innerHTML = mensaje;
    parrafo.setAttribute("id", element);
    contenedor.appendChild(parrafo);
    document.body.appendChild(contenedor);
  }


  function borrarMensajes(id) {
    if (document.getElementById(id)) {
      let parrafo1 = document.getElementById(id);
      parrafo1.remove();
    }
  }


  /*De aquí en adelante, seguiré el mismo patrón para todas las funciones encargadas de validar un input. 
    En primer lugar, hago las validaciones correspientes o bien mediante expresiones regulares o bien mediante estructuras if.Si dichas 
    expresiones devuelven un false, llamo a la función error() ,arriba declarada(linea5), en ese determinado momento. Error()  creará un parrafo con una id que me permitirá 
    identificar de que función de validación viene cada error. /////......12..../////// Si el error se repite dos veces, se vuelve a llamar a la función de validación
    que corresponda, lo que ejecutará la función borrarMensaje(), a la que se le pasa como parámetro el id y lo borrará si es que existe. */

  //1. Validar el nombre, recojo el el id del input fuera de la función para poder usarlo en el siguiente punto.
  let inputNombre = document.getElementById("txtNombre");
  function validaNombre() {
    borrarMensajes("nombre");
    if (inputNombre.value == "" || inputNombre.value.match(/\s/g)) {
      error("nombre", "El nombre es obligatorio o su formato está mal escrito");
      return false;
    }
    return true;
  }

  //2.Texto del campo nombre pasa a mayusculas al perder el foco;
  inputNombre.addEventListener("blur", (mayus) => {
    inputNombre.value = inputNombre.value.toUpperCase();
  });

  //3.función para validar la edad.
  function validaEdad() {
    borrarMensajes("edad");
    let inputEdad = document.getElementById("txtEdad");
    if (
      inputEdad.value < 18 ||
      inputEdad.value > 120 ||
      inputEdad.value == ""
    ) {
      error("edad", "La edad debe esta comprendida entre 18 y 120");
      return false;
    } else {
      return true;
    }
  }

  //4.Función para validar la contraseña.

  function validaPass() {
    borrarMensajes("pass");
    let inputPass1 = document.getElementById("txtPass1");
    let pass1 = inputPass1.value;
    if (pass1.length < 6) {
      error("pass", "La contraseña debe tener al menos 6 caracteres.");
      return false;
    } else {
      return true;
    }
  }
  //5. Valudación para comprobar que las dos contraseñas sean iguales.
  function validaPass2() {
    borrarMensajes("pass2");
    let inputPass1 = document.getElementById("txtPass1");
    let inputPass2 = document.getElementById("txtPass2");
    let pass1 = inputPass1.value;
    let pass2 = inputPass2.value;
    if (pass1 != pass2 || pass2 == "") {
      error("pass2", "Las dos contraseñas deben ser iguales.");
      return false;
    } else {
      return true;
    }
  }

  //6. Función para validad el DNI mediante expresión regular.
  function validaDNI() {
    borrarMensajes("dni");
    let inputNIF = document.getElementById("txtNIF");
    let dni = inputNIF.value;
    if (dni.match(/(\d{8})([A-Z]{1})/)) {
      return true;
    } else {
      error("dni", "El NIF debe contener 8 digitos seguidos de una letra mayúscula.");
      return false;
    }
  }

  //7. Función para validar el correo electrónico.
  function validaDominio() {
    borrarMensajes("dominio");
    let inputEmail = document.getElementById("txtEmail");
    let email = inputEmail.value;
    if (email.match(/\@ciclosmontecastelo.com$/)) {
      return true;
    } else {
      error("dominio", "El dominio debe pertenecer a ciclosmontecastelo.com.");
      return false;
    }
  }

  //8.Función para validar los inputRadio.
  function validaCheck() {
    borrarMensajes("check");
    let genero = document.getElementsByName("genero");
    if (genero[0].checked || genero[1].checked) {
      return true;
    } else {
      error("check", "Debe seleccionar un género.");
      return false;
    }
  }

  //9. Función para validar el teléfono
  function validaTelefono() {
    borrarMensajes("telefono");
    let inputTelefono = document.getElementById("txtTelf");
    let telefono = inputTelefono.value;
    if (telefono.match(/^[0-9]{9}$/)) {
      return true;
    } else {
      error("telefono", "Debe introducir un teléfono de 9 cifras.");
      return false;
    }
  }

  //10. Función para validar frutas
  function validaFrutas() {
    let contador = 0;
    let frutas = document.getElementById("frutas");
    for (let i = 0; i < frutas.length; i++) {
      if (frutas[i].selected) {
        contador++;
      }
      borrarMensajes("frutas1");
    }
    if (contador == 2 || contador == 3) {
      return true;
    } else {
      error("frutas1", "Debe seleccionar de dos a tres frutas");
      return false;
    }
  }

  // Esta función será la encargada de evaluar si todas las pruebas realizadas para cada input son válidas.

  //Con contador2 voy a recoger el número de veces que envío el formulario.
  var contador2 = 0;

  function validar(e) {
    let contador = 0; 
    if (validaNombre()) {
      contador++;
    }
    if (validaEdad()) {
      contador++;
    }
    if (validaPass()) {
      contador++;
    }
    if (validaPass2()) {
      contador++;
    }
    if (validaDNI()) {
      contador++;
    }
    if (validaDominio()) {
      contador++;
    }
    if (validaCheck()) {
      contador++;
    }
    if (validaTelefono()) {
      contador++;
    }
    if (validaFrutas()) {
      contador++;
    }
      
      //Por cada función que devuelva un true, se sumará una unidad a la variable contador. Al ser 9 validaciones las que tenemos que hacer, continúo con lo siguiente:
    if (contador == 9) {
      // 11 .Me encargo de pedir confirmación tras el envío.
      confirm("Desea enviar el formulario?");
      return true;
    } else {
      //14 Aprovecho esta parte del código para recoger los intentos de la variable contador2, y añadirlos en el div correspondiente.
      contador2++;
      let intentos = document.getElementById("intentos");
      intentos.innerHTML =
        "Se ha enviado el formulario " + contador2 + " veces";
      e.preventDefault();
      return false;
    }
  }

  //13.Dehabilito el botón de enviar
  document.getElementById("btnEnviar").disabled = true;

  //Y cuando activo los términos de privacidad, se activa el boton de enviar.

  document
    .getElementById("chkPrivacidad")
    .addEventListener("click", activarBoton);

  function activarBoton() {
    if (document.getElementById("chkPrivacidad").checked) {
      document.getElementById("btnEnviar").disabled = false;
    } else {
      document.getElementById("btnEnviar").disabled = true;
    }
  }

  document.getElementById("btnEnviar").addEventListener("click", validar);
};