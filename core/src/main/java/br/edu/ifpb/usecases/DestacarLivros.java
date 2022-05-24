package br.edu.ifpb.usecases;

import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.domain.Livros;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DestacarLivros {
    private final static int quantidade = 5;
    private List<Livro> lista;
    @Inject
    private Livros livros;
    @PostConstruct
    public void init(){
        lista = livros.todos()
                .stream()
                .limit(quantidade)
                .collect(Collectors.toList());
    }
    @Lock(LockType.READ) // no problem with concurrency
    public List<Livro> todosOsLivrosEmDestaque(){
        return  lista;
    }
}
