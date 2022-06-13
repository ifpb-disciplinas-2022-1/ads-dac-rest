package br.edu.ifpb.domain;

public interface Usuarios {

    void novoUsuario(Usuario usuario);

    String logar(String cpf, String senha);

    Usuario buscar(String key);
    Usuario buscar(String cpf, String senha);

    void adicionarEmprestimo(String key ,Emprestimo emprestimo);

    boolean temEmprestimo(String key, String codigoEmprestimo);
}
