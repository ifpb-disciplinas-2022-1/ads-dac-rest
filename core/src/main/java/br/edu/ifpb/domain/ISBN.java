package br.edu.ifpb.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ISBN { //Value Object
    private String numero;

    public ISBN(String value) {
        this.numero = value;
    }

    public static ISBN of(String value){ //criar um ISBN
        if(value==null || "".equals(value.trim())) return null;
        return  new ISBN(value);
    }
    public String simple(){
        return this.numero;
    }
    public String toFormatted(){
//       8535902775 -> "85-359-0277-5"; //13 caracteres
        return String.format(
                "%s-%s-%s-%s",
                numero.substring(0,2),
                numero.substring(2,5),
                numero.substring(5,9),
                numero.substring(9,10)
        );
    }

    public boolean valid() {
        return this.numero.trim().length() == 10;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISBN isbn = (ISBN) o;
        return numero.equals(isbn.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
