package br.edu.ifpb.api;

import br.edu.ifpb.domain.Emprestimo;
import br.edu.ifpb.domain.Emprestimos;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.domain.Usuarios;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResources {

    @Context
    private UriInfo uriInfo;

    @Context
    ResourceContext resourceContext;
    @Inject
    private Usuarios usuarios;

    @POST
    public Response criar(JsonObject json, @Context UriInfo uriInfo){
        Usuario usuario = new Usuario(
                json.getString("nome"),
                json.getString("cpf"),
                json.getString("senha")
                );
        usuarios.novoUsuario(usuario);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(usuario.getCpf()))
                .build();
        return Response.created(location) //201
                .entity(usuario) // emprestimo
                .build();
    }

}
