package org.main.model.entities;

public class Livros {
    private int id;
    private String nome;
    private String autor;
    private StatusLivro statusLivro;

    public Livros() {

    }

    public Livros(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

    public Livros(int id, String nome, String autor, StatusLivro statusLivro) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.statusLivro = statusLivro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public StatusLivro getStatusLivro() {
        return statusLivro;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", status=" + statusLivro +
                '}';
    }
}
