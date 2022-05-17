package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.domain.Livros;
import br.edu.ifpb.infra.LivrosEmJDBC;
import br.edu.ifpb.usecases.CestaDeLivros;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class ControladorDeReservas implements Serializable {
    private Livro livro;
    @Inject //no intancição do objeto (ControladorDeReservas)
    private CestaDeLivros cesta;
    @Inject
    private Livros livros;// = new LivrosEmJDBC();
    public String adicionar(){
        cesta.adicionar(livro); //session-key: 90ac0300181f-ffffffffd231423f-0
        return null;
    }
    public List<Livro> livrosDisponiveis(){
        return this.livros.todos();
    }
    public List<Livro> livrosNaCesta(){
        return this.cesta.livrosNaCesta();
    }
    public String finalizar(){
        this.cesta.finalizar();
//        this.cesta = new CestaDeLivros();
        // recuperando uma nova instâcia do objeto
        this.cesta = CDI.current()
                .select(CestaDeLivros.class)
                .get();
        return null;
    }
    public String remover(Livro livro){
        this.cesta.remover(livro);
        return null;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
