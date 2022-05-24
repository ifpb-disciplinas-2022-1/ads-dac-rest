package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.usecases.DestacarLivros;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("controladorDestaque")
@RequestScoped
public class ControladorDestaqueDeLivros {
    @Inject
    private DestacarLivros destacarLivros;

    public List<Livro> livrosEmDestaque() {
        return destacarLivros.todosOsLivrosEmDestaque();
    }
}
