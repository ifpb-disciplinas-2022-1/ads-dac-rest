package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Emprestimo;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.domain.Usuarios;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Singleton
public class UsuariosEmMemoria implements Usuarios {

    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void novoUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    @Override
    public String logar(String cpf, String senha) {
        Usuario usuario = buscar(cpf, senha);
        return usuario != null ? usuario.getKey() : null;
    }

    @Override
    public void adicionarEmprestimo(String key,Emprestimo emprestimo) {
        Usuario usuario = buscar(key);
        usuario.adicionarEmprestimo(emprestimo);
    }

    @Override
    public boolean temEmprestimo(String key, String codigoEmprestimo) {
        Usuario usuario = this.buscar(key);
        if(usuario != null){
            Emprestimo emprestimo = usuario.buscarEmprestimo(codigoEmprestimo);
            return emprestimo != null;
        }
        return false;
    }

    @Override
    public Usuario buscar(String key) {
        for (Usuario usuario:usuarios){
            if(usuario.getKey().equals(key)){
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario buscar(String cpf, String senha) {
        return this.buscar(String.valueOf(Objects.hash(cpf,senha)));
    }

}
