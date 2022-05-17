package br.edu.ifpb.web.jsf.converter;

import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.domain.Livros;
import br.edu.ifpb.infra.LivrosEmJDBC;
import br.edu.ifpb.usecases.CestaDeLivros;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converter.livro", forClass = Livro.class)
public class LivroConverter implements Converter<Livro> {

    private Livros livros = CDI.current()
            .select(Livros.class)
            .get();
    @Override
    public Livro getAsObject(FacesContext context, UIComponent component, String value) {
        if(value ==null)
            return null;
        return livros.buscarPorId(
                Long.valueOf(value)
        );
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Livro value) {
        if(value ==null)
            return null;
        return String.valueOf(
                value.getId()
        );
    }
}
