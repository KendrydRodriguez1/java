package com.primerProyecto.primerProyecto.controllers;

import com.primerProyecto.primerProyecto.dao.UsuarioDao;
import com.primerProyecto.primerProyecto.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllers {
    @Autowired
    UsuarioDao usuarioDao;

    @RequestMapping(value = "api/login/user", method = RequestMethod.POST)
    public String ingresoUsuario(@RequestBody Usuario usuario){
        if(usuarioDao.verificarEmailPassword(usuario)){
            return "ok";
        }
        return "no se pudo";
    }
}
