package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.domain.Usuarios;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UsuariosEmMemoria implements Usuarios {

    private final List<Usuario> usuarios = new ArrayList<>();

    public void novoUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

}
