package br.edu.ifpb.domain;

public interface Emprestimos {
    public void novo(Emprestimo emprestimo);
    public Emprestimo localizarPorCodigo(String codigo);
    public Emprestimo incluirLivro(String codigo, Livro livro);
}
