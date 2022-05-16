package br.edu.ifpb.web.jsf.validator;

import br.edu.ifpb.domain.ISBN;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@FacesValidator(value = "validator.isbn")
public class ISBNValidator implements Validator<ISBN> {
    @Override
    public void validate(FacesContext context, UIComponent component, ISBN value)
            throws ValidatorException {
    if  (value ==null) {
        throw new ValidatorException(
                new FacesMessage("ISBN inválido")
        );
    }
    if(!value.valid()){
        throw new ValidatorException(
                new FacesMessage("ISBN mal-formatado")
        );
    }
    }
}
