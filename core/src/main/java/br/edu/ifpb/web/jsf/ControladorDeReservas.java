package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Livro;
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
    private String livro;
    @Inject //no intancição do objeto (ControladorDeReservas)
    private CestaDeLivros cesta;// = new CestaDeLivros(); //session-key: 90ac0300181f-ffffffffd231423f-0
    public String adicionar(){
        cesta.adicionar(livro); //session-key: 90ac0300181f-ffffffffd231423f-0
        livro = "";
        return null;
    }
    public List<String> livrosDisponiveis(){
        return Arrays.asList(
            "livro 1",
            "livro 2",
            "livro 3",
            "livro 4",
            "livro 5"
        );
    }
    public List<String> livrosNaCesta(){
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
    public String remover(String livro){
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
