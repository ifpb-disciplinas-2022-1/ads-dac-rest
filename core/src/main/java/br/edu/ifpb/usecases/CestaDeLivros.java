package br.edu.ifpb.usecases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CestaDeLivros implements Serializable {
    private List<String> livros = new ArrayList<>();
    public void adicionar(String livro){
        livros.add(livro);
    }
    public List<String> livrosNaCesta(){
        return this.livros;
    }
    public void finalizar(){
        this.livros = new ArrayList<>();
    }
    public void remover(String livro){
        this.livros.remove(livro);
    }
}
