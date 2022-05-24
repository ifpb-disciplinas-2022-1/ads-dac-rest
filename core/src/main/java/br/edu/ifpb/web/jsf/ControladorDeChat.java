package br.edu.ifpb.web.jsf;

import br.edu.ifpb.usecases.ChatOnline;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ControladorDeChat {
    private String mensagem;
    @Inject
    private ChatOnline chat;
    public String adicionar() {
        chat.adicionarMensagem(mensagem);
        this.mensagem="";
//        this.mensagens = chat.mensagens();
        return null;
    }
    public List<String> todasAsMensagens() {
        return this.chat.mensagens();
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
