package br.edu.ifpb.domain;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/04/2022, 10:48:28
 */
@XmlRootElement
public class Livro implements Serializable {

    private long id; //uuid
    private String titulo;
    private LocalDate dataDeLancamento;
    //conversores e validadores
    private float preco;
    private ISBN isbn;// "85-359-0277-5"; //13 caracteres

    private Editora editora;
    private String capa;

    public Livro(){}
    public Livro(String titulo, LocalDate dataDeLancamento) {
        this(0,titulo,dataDeLancamento);
    }
    public Livro(long id,String titulo,LocalDate dataDeLancamento) {
        this(id, titulo, dataDeLancamento, "");
    }
    public Livro(long id,String titulo,LocalDate dataDeLancamento, String capa) {
        this.id = id;
        this.titulo = titulo;
        this.dataDeLancamento = dataDeLancamento;
        this.capa = capa;
    }
    public String titulo(){
        return this.titulo;
    }
    public LocalDate dataLancamento() {
        return this.dataDeLancamento;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }
    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public ISBN getIsbn() {
        return isbn;
    }
    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }
    public Editora getEditora() {
        return editora;
    }
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    public String getCapa() {
        return capa;
    }
    public void setCapa(String capa) {
        this.capa = capa;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id && Float.compare(livro.preco, preco) == 0 && Objects.equals(titulo, livro.titulo) && Objects.equals(dataDeLancamento, livro.dataDeLancamento) && Objects.equals(isbn, livro.isbn) && Objects.equals(editora, livro.editora);
    }
    public int hashCode() {
        return Objects.hash(id, titulo, dataDeLancamento, preco, isbn, editora);
    }
    public String toString() {
        return "Livro{" + "id=" + id + ", titulo=" + titulo + ", dataDeLancamento=" + dataDeLancamento + '}';
    }
}
