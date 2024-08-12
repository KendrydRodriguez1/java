// Call the dataTables jQuery plugin

$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
});


async function cargarUsuarios() {
    const respuesta = await fetch('api/usuarios', {
        method: 'GET', // Corregido 'mathod' a 'method'
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        // body: JSON.stringify({a: 1, b: 'Textual content'}) // Comentado ya que no es necesario para un método GET
    });

    const usuarios = await respuesta.json();

    let listado = '';
    for(const usuarioHTML of usuarios){
        let botomEliminar = `<a href="#" onclick="eliminarUsuario(${usuarioHTML.id})" class="btn btn-danger btn-circle btn-sm">`


        let usuarioData = `
        <tr>
                 <td>${usuarioHTML.id}</td>
                 <td>${usuarioHTML.apellido}</td>
                 <td>${usuarioHTML.email}</td>
                 <td>${usuarioHTML.telefono}</td>
                 <td>
                     ${botomEliminar}
                 </td>
        </tr>`;
        listado += usuarioData
    }
    document.querySelector('#usuarios tbody').outerHTML = listado
}


async function eliminarUsuario(id) {
    if(!confirm('Desea eliminar este usuarios')){
        return;
    }
    try {
        const respuesta = await fetch('api/usuarios/' + id, {
            method: 'DELETE', // Corregido 'mathod' a 'method'
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            // body: JSON.stringify({a: 1, b: 'Textual content'}) // Comentado ya que no es necesario para un método DELETE
        });

        if (!respuesta.ok) {
            throw new Error('Error en la solicitud: ' + respuesta.status);
        }


    } catch (error) {
        console.error('Error al eliminar usuario:', error);
    }

    location.reload()


}

