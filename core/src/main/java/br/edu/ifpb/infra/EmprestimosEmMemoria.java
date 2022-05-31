package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Emprestimo;
import br.edu.ifpb.domain.Emprestimos;
import br.edu.ifpb.domain.Livro;

import javax.ejb.Singleton;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Singleton
public class EmprestimosEmMemoria implements Emprestimos {
    private List<Emprestimo> emprestimos = new CopyOnWriteArrayList<>();
    @Override
    public void novo(Emprestimo emprestimo) {
         this.emprestimos.add(emprestimo);
    }

    @Override
    public Emprestimo localizarPorCodigo(String codigo) {
       return this.emprestimos
                .stream()
                .filter(e->e.getCodigo().equals(codigo))
                .findFirst()
               .orElse(Emprestimo.vazio());
    }

    @Override
    public Emprestimo incluirLivro(String codigo, Livro livro) {
        Emprestimo emprestimo = localizarPorCodigo(codigo);
        if(Emprestimo.vazio().equals(emprestimo)){ //inválido
            return emprestimo;
        }
        emprestimo.adicionarLivro(livro);
        return emprestimo;
    }
}
