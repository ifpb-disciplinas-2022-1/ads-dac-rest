package br.edu.ifpb.usecases;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Startup
public class ChatOnline implements Serializable {
    private List<String> mensagens = new CopyOnWriteArrayList<>(); //Thread Safe
    private Object lock = new Object();

//    public synchronized void adicionarMensagem(String mensagem) {
//        System.out.println("LOG: " + mensagem);
//        this.mensagens.add(mensagem);
//    }
//    public void adicionarMensagem(String mensagem) {
//        System.out.println("LOG: " + mensagem);
//        synchronized (this){
//                this.mensagens.add(mensagem);
//        }
//    }
//    public void adicionarMensagem(String mensagem) {
//        System.out.println("LOG: " + mensagem);
//        synchronized (mensagens){
//            this.mensagens.add(mensagem);
//        }
//    }
    public void adicionarMensagem(String mensagem) {
        System.out.println("LOG: " + mensagem);
        synchronized (lock){
            this.mensagens.add(mensagem);
        }
    }
    public List<String> mensagens() {
        return Collections.unmodifiableList( //num bula
                this.mensagens
        );
    }
}
