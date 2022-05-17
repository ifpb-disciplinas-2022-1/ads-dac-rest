package br.edu.ifpb.usecases;

import br.edu.ifpb.domain.Livro;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
public class CestaDeLivros implements Serializable {
    private List<Livro> livros = new ArrayList<>();
    public void adicionar(Livro livro){
        livros.add(livro);
    }
    public List<Livro> livrosNaCesta(){
        return this.livros;
    }
    @Remove // concluindo as interações com este objeto
    public void finalizar(){
        System.out.println("----Lista de Livros-----");
        livros.forEach(livro-> System.out.println(livro.titulo()));
        this.livros = new ArrayList<>();
    }
    public void remover(Livro livro){
        this.livros.remove(livro);
    }
}
