package org.main.model.entities;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private final Usuarios usuarios;
    private final Livros livros;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private static final int DIAS_EMPRESTIMO = 7;
    private final StatusEmprestimo statusEmprestimo;


    public Emprestimo(int id, Usuarios usuarios, Livros livros, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal, StatusEmprestimo statusEmprestimo) {
        this.id = id;
        this.usuarios = usuarios;
        this.livros = livros;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.statusEmprestimo = statusEmprestimo;
    }

    public Emprestimo(Usuarios usuarios, Livros livros, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista,StatusEmprestimo statusEmprestimo) {
        this.usuarios = usuarios;
        this.livros = livros;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.statusEmprestimo = statusEmprestimo;
    }

    public Emprestimo(int id,Usuarios usuarios, Livros livros, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista,StatusEmprestimo statusEmprestimo) {
        this.id = id;
        this.usuarios = usuarios;
        this.livros = livros;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.statusEmprestimo = statusEmprestimo;
    }

    public Emprestimo(int id, Usuarios usuarios, Livros livros, LocalDate dataDevolucaoReal,StatusEmprestimo statusEmprestimo) {
        this.id = id;
        this.usuarios = usuarios;
        this.livros = livros;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.statusEmprestimo = statusEmprestimo;
    }


    public Usuarios getUsuarios() {
        return usuarios;
    }

    public Livros getLivros() {
        return livros;
    }

    public int getId() {
        return id;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista.plusDays(DIAS_EMPRESTIMO);
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public static int getDiasEmprestimo() {
        return DIAS_EMPRESTIMO;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", usuarios=" + usuarios +
                ", livros=" + livros +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevistaDevolucao=" + dataDevolucaoPrevista +
                ", dataRealDevolucao=" + dataDevolucaoReal +
                '}';
    }
}
