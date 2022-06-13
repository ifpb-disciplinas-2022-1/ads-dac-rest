package br.edu.ifpb.api;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.domain.Usuarios;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
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

    @GET
    @Path("{key}")
    public Response buscar(@PathParam("key") String key){
        Usuario usuario = usuarios.buscar(key);
        if(usuario == null){
            return Response.noContent()
                    .build();
        }
        return Response.ok(usuario).build();
    }

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

    @POST
    @Path("login")
    public Response logar(JsonObject json){
        String key = usuarios.logar(
                json.getString("cpf"),
                json.getString("senha"));

        if("".equals(key.trim())){
            return Response.noContent()
                    .build();
        }
        return Response.accepted(key).build();
    }

}
