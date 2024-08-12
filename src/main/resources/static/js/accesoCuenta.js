async function accesoCuenta() {
    let datos = {};
    let email = document.getElementById("Email").value;
    let contrasena = document.getElementById("Contraseña").value;
    datos.email = email;
    datos.password = contrasena;

    try {
        const respuesta = await fetch('api/login/user', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });



        const respuestaEnvio = await respuesta.text();
        console.log(respuestaEnvio);

    } catch (error) {
        // Maneja el error de forma más detallada
        alert("Algo salió mal: " + error.message);
        console.error("Detalles del error:", error);
    }
}
