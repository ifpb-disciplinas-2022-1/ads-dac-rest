package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.usecases.CestaDeLivros;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ControladorDeReservas implements Serializable {
    private String livro;
//    private List<String> livros = new ArrayList<>();
    private CestaDeLivros cesta = new CestaDeLivros();
    public String adicionar(){
//        livros.add(livro);
        cesta.adicionar(livro);
        livro = "";
        return null;
    }
    public List<String> livrosNaCesta(){
        return this.cesta.livrosNaCesta();
    }
    public String finalizar(){
        this.cesta.finalizar();
        this.cesta = new CestaDeLivros();
        return null;
    }
    public String remover(String livro){
//        this.livros.remove(livro);
        this.cesta.remover(livro);
        return null;

    }
    public String getLivro() {
        return livro;
    }
    public void setLivro(String livro) {
        this.livro = livro;
    }
}
