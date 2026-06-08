package org.main.entities;

public class Livros {
    private int id;
    private String nome;
    private String autor;

    public Livros() {

    }

    public Livros(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

    public Livros(int id, String nome, String autor) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
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
}
