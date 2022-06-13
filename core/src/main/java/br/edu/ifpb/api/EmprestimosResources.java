package br.edu.ifpb.api;

import br.edu.ifpb.api.presenter.EmprestimoPresenter;
import br.edu.ifpb.domain.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("emprestimos")
@Produces(MediaType.APPLICATION_JSON)
public class EmprestimosResources {

    @Context
    private UriInfo uriInfo;
    @Context
    ResourceContext resourceContext;
    @Inject
    private Emprestimos emprestimos;
    @Inject
    private Livros livros;
    @Inject
    private Usuarios usuarios;
    @POST
    public Response criar(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimos.novo(emprestimo);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(emprestimo.getCodigo()))
                .build();
        return Response.created(location) //201
                .entity(emprestimo) // emprestimo
                .build();
    }
    @GET
    @Path("{id}") // ../api/emprestimos/6017cf1b-ad15-457d-9e8a-e46bfd2748c8
    public Response recuperarPorCodigo(@PathParam("id") String codigo){
        Emprestimo emprestimo = emprestimos.localizarPorCodigo(codigo);
        if(Emprestimo.vazio().equals(emprestimo)){
            return Response.noContent()
                    .build();
        }
        EmprestimoPresenter presenter = new EmprestimoPresenter(emprestimo, uriInfo);
        return Response.ok()
                .entity(presenter)
                .build();
    }

//    @PUT
//    @Path("{codigo}/livros/{idLivro}") // ../api/emprestimos/6017cf1b-ad15-457d/livros/2
//    public SubResourcesDeLivros incluirLivroEm(
//            @PathParam("codigo") String codigo,
//            @PathParam("idLivro") long idLivro){
////        Livro livro = livros.buscarPorId(idLivro);
////        Emprestimo emprestimo =  emprestimos.incluirLivro(codigo, livro);
////        return  Response.ok().
////                entity(emprestimo)
////                .build();
//        return new SubResourcesDeLivros(livros,emprestimos);
//    }
//    @GET
    @Path("{codigo}/livros") // ../api/emprestimos/6017cf1b-ad15-457d/livros/
    public SubResourcesDeLivros incluirLivroEm(
            @PathParam("codigo") String codigo){
        return resourceContext.getResource(SubResourcesDeLivros.class);
    }
    @PUT
    @Path("{codigo}/cliente/{key}") // ../api/emprestimos/6017cf1b-ad15-457d/cliente/2
    public Response incluirClienteEm(
            @PathParam("codigo") String codigo,
            @PathParam("key") String key){
        Emprestimo emprestimo =  emprestimos.localizarPorCodigo(codigo);
        Usuario usuario = usuarios.buscar(key);
        if(Emprestimo.vazio().equals(emprestimo)){
            return Response.notModified()
                    .build();
        }
        if(usuario == null){
            return Response.notModified()
                    .build();
        }
        emprestimo.setKey(key);
        usuario.adicionarEmprestimo(emprestimo);
        return Response.ok()
                .entity(emprestimo)
                .build();
    }
    @PUT
    @Path("{codigo}")
    public Response cancelar(@PathParam("codigo") String codigo){
        Emprestimo emprestimo =  emprestimos.localizarPorCodigo(codigo);
        if(Emprestimo.vazio().equals(emprestimo)){
            return Response.notModified()
                    .build();
        }
        emprestimo.cancelar();
        return Response.ok()
                .entity(emprestimo)
                .build();
    }
    @PUT
    @Path("{codigo}/finalizar/cliente/{key}")
    public Response finalizar(
            @PathParam("codigo") String codigo,
            @PathParam("key") String key){
        Emprestimo emprestimo =  emprestimos.localizarPorCodigo(codigo);
        if(Emprestimo.vazio().equals(emprestimo)){
            return Response.notModified()
                    .build();
        }
        if(usuarios.temEmprestimo(key,codigo)){
            emprestimo.finalizar();
            return Response.ok()
                    .entity(emprestimo)
                    .build();
        }
        return Response.notModified()
                .build();
    }
}

// Extract the token from the HTTP Authorization header
//String token = authorizationHeader.substring("Basic ".length()).trim();
//String usuarioSenha = new String(Base64.getDecoder().decode(token));
//StringTokenizer tokenizer = new StringTokenizer(usuarioSenha, ":");
//String usuario = tokenizer.nextToken();
//String senha = tokenizer.nextToken();
//Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())