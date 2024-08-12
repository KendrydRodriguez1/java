package com.primerProyecto.primerProyecto.controllers;

import com.primerProyecto.primerProyecto.dao.UsuarioDao;
import com.primerProyecto.primerProyecto.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioControllers {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable long id){
        Usuario usuario  = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Kendryd");
        usuario.setApellido("Rodriguez");
        usuario.setEmail("kendryd@unemi.edu.ec");
        usuario.setPassword("123");
        usuario.setTelefono("089787484");
        return usuario;
    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void AddUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1014, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.AddUsuario(usuario);
    }


    @RequestMapping(value = "api/editarUsuario", method = RequestMethod.PUT)
    public Usuario editarUsuario(){
        Usuario usuario  = new Usuario();
        usuario.setNombre("Kendryd");
        usuario.setApellido("Rodriguez");
        usuario.setEmail("kendryd@unemi.edu.ec");
        usuario.setPassword("123");
        return usuario;
    }


    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable long id){
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public void loginUsuario(@RequestBody Usuario usuario){

    }






}
