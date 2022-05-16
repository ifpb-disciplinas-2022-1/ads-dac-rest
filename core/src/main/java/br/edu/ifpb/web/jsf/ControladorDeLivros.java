package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.ISBN;
import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.domain.Livros;
import br.edu.ifpb.infra.LivrosEmJDBC;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class ControladorDeLivros {

    private Livros livros = new LivrosEmJDBC();
    private Livro livro = new Livro();

    public String salvar(){
        return null;
    }
    //TODO: apenas para exemplificar o uso do selectOneMenu
    public List<ISBN> isbns(){
        return Arrays.asList(
                new ISBN("1234567890"),
                new ISBN("1234567891"),
                new ISBN("1234567892")
        );
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
