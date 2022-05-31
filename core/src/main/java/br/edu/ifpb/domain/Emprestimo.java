package br.edu.ifpb.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Emprestimo {
    private String codigo;
    private List<Livro> livros;
    private LocalDate criadoEm;
    private Status status;
    // atualizar
    private LocalDate finalizadoEm;
    private String cpf;

    public Emprestimo() {
        this.codigo = UUID.randomUUID().toString();
        this.criadoEm = LocalDate.now();
        this.status = Status.CRIADO;
        this.livros = new ArrayList<>();
    }
    private Emprestimo(String codigo){
        this.codigo = codigo;
    }
    public static Emprestimo vazio() {
        return new Emprestimo("vazio");
    }

    public void incluirCliente(String cpf){
        this.cpf = cpf;
    }
    public void adicionarLivro(Livro livro){
        this.livros.add(livro);
    }
    public void cancelar(){
        this.status = Status.CANCELADO;
    }
    public void finalizar(){
        this.status = Status.FINALIZADO;
        this.finalizadoEm = LocalDate.now();
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    public LocalDate getCriadoEm() {
        return criadoEm;
    }
    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDate getFinalizadoEm() {
        return finalizadoEm;
    }
    public void setFinalizadoEm(LocalDate finalizadoEm) {
        this.finalizadoEm = finalizadoEm;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(codigo, that.codigo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
