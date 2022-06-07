package br.edu.ifpb.api;

import br.edu.ifpb.domain.Emprestimo;
import br.edu.ifpb.domain.Emprestimos;
import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.domain.Livros;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// ../api/emprestimos/6017cf1b-ad15-457d/livros/
@Stateless
public class SubResourcesDeLivros {
    @Inject
    private Livros livros;
    @Inject
    private Emprestimos emprestimos;

    private Logger logger = Logger.getLogger(SubResourcesDeLivros.class.getName());
//
//    public SubResourcesDeLivros(Livros livros, Emprestimos emprestimos) {
//        this.livros = livros;
//        this.emprestimos = emprestimos;
//    }

    @PUT
    @Path("{idLivro}") // ../api/emprestimos/6017cf1b-ad15-457d/livros/2
    public Response incluirLivroEm(
            @PathParam("codigo") String codigo,
            @PathParam("idLivro") long idLivro){
        logger.log(Level.INFO, "Executando o método PUT, após a alteração");
        Livro livro = livros.buscarPorId(idLivro);
        Emprestimo emprestimo =  emprestimos.incluirLivro(codigo, livro);
        return  Response.ok().
                entity(emprestimo)
                .build();
    }
    @GET
//    @Path("{codigo}/livros") // ../api/emprestimos/6017cf1b-ad15-457d/livros/
    public Response listarLivrosDoEmprestimo(
            @PathParam("codigo") String codigo){
        logger.log(Level.INFO, "Executando o método GET");
        Emprestimo emprestimo =  emprestimos.localizarPorCodigo(codigo);
        List<Livro> list = emprestimo.getLivros();
        return  Response.ok().
                entity(list)
                .build();
    }
}
