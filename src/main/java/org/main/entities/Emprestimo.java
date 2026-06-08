package org.main.entities;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private Usuarios usuarios;
    private Livros livros;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private StatusEmprestimo status;
}
