package br.edu.ifpb.domain;

import java.time.LocalDate;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/04/2022, 10:48:28
 */
public class Livro {

    private long id;
    private String titulo;
    private LocalDate dataDeLancamento;
    //conversores e validadores
    private float preco;
    private ISBN isbn;// "85-359-0277-5"; //13 caracteres

    private Editora editora;

    public Livro(){}
    public Livro(String titulo, LocalDate dataDeLancamento) {
        this(0,titulo,dataDeLancamento);
    }
    public Livro(long id,String titulo,LocalDate dataDeLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.dataDeLancamento = dataDeLancamento;
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

    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", titulo=" + titulo + ", dataDeLancamento=" + dataDeLancamento + '}';
    }
}
