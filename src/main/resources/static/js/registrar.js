async function registrarUsuario() {
    let datos = {};
    let nombre = document.getElementById("txtNombre").value;
    let apellido = document.getElementById("txtApellido").value;
    let email = document.getElementById("txtEmail").value;
    let celular = document.getElementById("txtCelular").value;
    let contrasena = document.getElementById("txtContraseña1").value;
    let contrasena2 = document.getElementById("txtContraseña2").value;

    if (contrasena !== contrasena2) {
        alert("Las contraseñas no coinciden. Por favor, repítelas correctamente.");
        return;
    }

        datos.nombre = nombre;
        datos.apellido = apellido;
        datos.email = email;
        datos.telefono = celular;
        datos.password = contrasena;

        const respuesta = await fetch('api/usuarios', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (!respuesta.ok) {
            throw new Error(`HTTP error! Status: ${respuesta.status}`);
        }

        const resultado = await respuesta.json();
        console.log("Usuario registrado exitosamente", resultado);

}




