package br.edu.ifpb.api.security;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

@Provider
public class AuthorizationHeaderFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String headerString = requestContext.getHeaderString("Authorization");

        if (headerString == null || headerString.trim().isEmpty()) {
            //TODO: padronizar a menssagem de erro
            JsonObject mensagem = createMessageError("heard não informado");
            requestUnauthorized(requestContext, mensagem);
        } else {
            //Basic YWRtaW46MTIzNDU2
            String heard = headerString.replaceAll("Basic ", "");
            String usuarioSenha = new String(Base64.getDecoder().decode(heard));
            StringTokenizer tokenizer = new StringTokenizer(usuarioSenha, ":");
            String usuario = tokenizer.nextToken();
            String senha = tokenizer.nextToken();
            //TODO: verificar as informações passadas no token
            if ("admin".equals(usuario) && "123456".endsWith(senha)) {
                System.out.println("valido");
            } else {
                JsonObject mensagem = createMessageError("usuario/senha não confere");
                requestUnauthorized(requestContext, mensagem);
            }
        }
    }

    private JsonObject createMessageError(String message) {
        JsonObject mensagem = Json.createObjectBuilder()
                .add("msg", message)
                .build();
        return mensagem;
    }

    private void requestUnauthorized(ContainerRequestContext requestContext, JsonObject mensagem) {
        Response response = Response
                .status(Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .entity(mensagem)
                .build();

        requestContext.abortWith(response);
    }

}
