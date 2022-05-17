package br.edu.ifpb.usecases;

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
    private List<String> livros = new ArrayList<>();
    public void adicionar(String livro){
        livros.add(livro);
    }
    public List<String> livrosNaCesta(){
        return this.livros;
    }
    @Remove // concluindo as interações com este objeto
    public void finalizar(){
        System.out.println("----Lista de Livros-----");
        livros.forEach(System.out::println);
        this.livros = new ArrayList<>();
    }
    public void remover(String livro){
        this.livros.remove(livro);
    }
}
