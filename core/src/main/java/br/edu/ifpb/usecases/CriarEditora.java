package br.edu.ifpb.usecases;

import br.edu.ifpb.domain.Editora;
import br.edu.ifpb.domain.Editoras;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;

@Stateless
public class CriarEditora { //lógica de negócio
    @Inject
    private Editoras editoras;
//    public CriarEditora(Editoras editoras) {
//        this.editoras = editoras;
//    }
    public void criarEditora(Editora editora){
        // nome e local não-vazios
        String localDeOrigem = editora.getLocalDeOrigem();
        String nomeFantasia = editora.getNomeFantasia();
        if(localDeOrigem == null || "".equals(localDeOrigem.trim())){
            return;
        }
        if(nomeFantasia == null || "".equals(nomeFantasia.trim())){
            return;
        }
        //nome duplicado
        boolean duplicado = editoras.nomeDuplicado(nomeFantasia);
        if(duplicado){
            return;
        }
        //data de criação
        editora.setCriadaEm(LocalDate.now());
        //salvar no banco
        editoras.nova(editora);
    }
}
