package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Editora;
import br.edu.ifpb.domain.Editoras;
import br.edu.ifpb.infra.EditorasEmJDBC;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ControladorDeEditoras {

    private Editora editora = new Editora();
    private String busca ="";
    private List<Editora> editorasFiltradas;//  = editoras.todas();

    private Editoras editoras = new EditorasEmJDBC();
    public String salvar(){
        editoras.nova(editora);
        return "listar";
    }

    public String filtrar(){
        if(null==busca || "".equals(busca.trim())){
            this.editorasFiltradas = editoras.todas(); //lazy
        }else{
            this.editorasFiltradas = this.editoras.porLocalDeOrigem(busca);
        }
        return null;
    }

    @PostConstruct
    public void init(){
        this.editorasFiltradas = editoras.todas(); //lazy
    }
    public List<Editora> getEditorasFiltradas() {
        return editorasFiltradas;
    }

    public List<Editora> todasAsEditoras(){
        return this.editoras.todas();
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

}
