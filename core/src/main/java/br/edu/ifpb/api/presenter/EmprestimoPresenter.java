package br.edu.ifpb.api.presenter;

import br.edu.ifpb.api.EmprestimosResources;
import br.edu.ifpb.api.LivrosResources;
import br.edu.ifpb.domain.Emprestimo;
import br.edu.ifpb.domain.Livro;

//import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmprestimoPresenter implements Serializable {
    private String codigo;
    private LocalDate criadoEm;
    private String status;
    private String cliente; //cpf
    private List<Link> livros; //HATEOS
    //ações  possíveis para este recurso (semânticos)
    private Link finalizar;
    private UriInfo uriInfo;

    public EmprestimoPresenter() {
    }

    public EmprestimoPresenter(Emprestimo emprestimo, UriInfo uriInfo) {
         this.codigo = emprestimo.getCodigo();
         this.criadoEm = emprestimo.getCriadoEm();
         this.status = emprestimo.getStatus().name();
         this.cliente = emprestimo.getCpf();
         this.uriInfo = uriInfo;
         incluirLivros(emprestimo);
         incluirAcoes(emprestimo);
    }

    private void incluirLivros(Emprestimo emprestimo){
        this.livros = emprestimo.getLivros()
                    .stream() // Stream<Livro>
                    .map(this::toLink)
                    .collect(Collectors.toList());
    }
    private void incluirAcoes(Emprestimo emprestimo) {
        URI location = uriInfo.getBaseUriBuilder() // ../api/
                .path(EmprestimosResources.class) // ../api/emprestimos
                .path(emprestimo.getCodigo()) // ../api/emprestimos/1
                .path("finalizar") // ../api/emprestimos/1/finalizar
                .build();

        this.finalizar = new Link(
                "finalizar",
                location.toString()
        );
    }

    private Link toLink(Livro livro){
        // ../api/livro/1
        URI location = uriInfo.getBaseUriBuilder() // ../api/
                .path(LivrosResources.class) // ../api/livros
                .path(String.valueOf(livro.getId())) // ../api/livros/1
                .build();

        return new Link(
                livro.titulo(),
                location.toString()
        );
//                new LinkLink.fromUri(location)
//                .rel(livro.titulo()) // "rel": "c++", "href": "../api/livros/5"
//                .build();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Link> getLivros() {
        return livros;
    }

    public void setLivros(List<Link> livros) {
        this.livros = livros;
    }

    public Link getFinalizar() {
        return finalizar;
    }

    public void setFinalizar(Link finalizar) {
        this.finalizar = finalizar;
    }
}
