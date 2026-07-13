package org.main.controller;

import org.main.model.EmprestimoModel;
import org.main.model.LivrosModel;
import org.main.model.entities.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmprestimoController {
    public void fazerEmprestimo(Usuarios usuarioLogado) throws SQLException {
        Scanner scan = new Scanner(System.in);
        EmprestimoModel emprestimoModel = new EmprestimoModel();
        LocalDate dataEmprestimo = LocalDate.now();
        LivrosModel livro = new LivrosModel();

        System.out.print("Informe o nome do livro: ");
        String nome = scan.nextLine();

        int diasEmprestimo = Emprestimo.getDiasEmprestimo();
        for (Livros lv : livro.buscarLivro(nome)){
            if (lv.getStatusLivro() == StatusLivro.DISPONIVEL) {

                LocalDate dataPrevistaDevolucao = dataEmprestimo.plusDays(diasEmprestimo);
                Emprestimo emprestimo = new Emprestimo(usuarioLogado,lv,dataEmprestimo,dataPrevistaDevolucao, StatusEmprestimo.EMPRESTADO);

                emprestimoModel.inserir(emprestimo);
            } else {
                System.out.println("Livro não disponivel para emprestimo");
            }
        }
    }
    public void devolverLivro(Usuarios usuarioLogado) throws SQLException {
        Scanner scan = new Scanner(System.in);
        EmprestimoModel emprestimoModel = new EmprestimoModel();
        LocalDate dataDevolucao = LocalDate.now();
        LivrosModel livros = new LivrosModel();

        System.out.print("Informe o nome do livro: ");
        String nome = scan.nextLine();

        for (Livros livro : livros.buscarLivro(nome)) {
            if (livro.getStatusLivro() != StatusLivro.DISPONIVEL) {
               // int id = emprestimoModel.getIdEmprestimo(usuarioLogado.getId(),livro.getId());
                System.out.println("Informe o ID do emprestimo: ");
                int id = scan.nextInt();

                Emprestimo devolucao = new Emprestimo(id,usuarioLogado, livro, dataDevolucao, StatusEmprestimo.DEVOLVIDO);

                emprestimoModel.devolucao(devolucao);
            } else {
                System.out.println("Livro não disponivel para Devolução");
            }
        }
    }
    public void listarController() {
        EmprestimoModel emprestimoModel = new EmprestimoModel();

        List<Emprestimo> lista = emprestimoModel.listar();
        System.out.println("Tabela de Emprestimo: ");
        for (Emprestimo listar : lista) {
            System.out.printf("ID: %d - ID USER: %d - ID LIVRO: %d - Data de Emprestimo: %s - Data de Devolução Prevista: %s - Data de Devolução Real: %s%n", listar.getId(),listar.getUsuarios().getId(),listar.getLivros().getId(),listar.getDataEmprestimo(),listar.getDataDevolucaoPrevista(), listar.getDataDevolucaoReal());
        }
    }
}
