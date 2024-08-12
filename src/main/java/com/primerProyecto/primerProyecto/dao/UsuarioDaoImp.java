package com.primerProyecto.primerProyecto.dao;

import com.primerProyecto.primerProyecto.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository //dice que la clase se va a a encargar de la conexion con la bd
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    EntityManager entityManager;




    @Override
    public List<Usuario> getUsuarios() {
        String query = "from Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void AddUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }


    @Override
    public boolean verificarEmailPassword(Usuario usuario) {
        String query = "from Usuario where email = :mail";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("mail", usuario.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return  false;
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String passwordHashed = lista.get(0).getPassword();
        return argon2.verify(passwordHashed, usuario.getPassword());

    }


    @Override
    public void eliminar(long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }
}
