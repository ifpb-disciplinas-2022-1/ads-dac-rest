package br.edu.ifpb.web.jsf.converter;

import br.edu.ifpb.domain.ISBN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converter.isbn",forClass = ISBN.class)
public class ISBNConverter implements Converter<ISBN> {
    @Override
    public ISBN getAsObject(FacesContext context, UIComponent component, String value) {
        return ISBN.of(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ISBN value) {
        if(value ==null) return null;
        return value.simple();
    }
}
