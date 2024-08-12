package com.primerProyecto.primerProyecto.dao;

import com.primerProyecto.primerProyecto.models.Usuario;
import java.util.List;

public interface UsuarioDao {


    void eliminar(long id);

    List<Usuario> getUsuarios();


    void AddUsuario(Usuario usuario);


    boolean verificarEmailPassword(Usuario usuario);
}
