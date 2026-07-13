package org.main.controller;

import org.main.model.entities.Livros;
import org.main.model.LivrosModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.main.utils.Cores.ANSI_RESET;
import static org.main.utils.Cores.ANSI_VERDE;

public class LivrosController {

    public void inserirController() throws SQLException {
        Scanner scan = new Scanner(System.in);
        LivrosModel livrosModel = new LivrosModel();
        String nome, autor;

        System.out.println("Inserir Livros");
        System.out.print("Informe o nome do livro: ");
        nome = scan.nextLine();
        System.out.print("Informe o autor do livro: ");
        autor = scan.nextLine();

        Livros novos = new Livros(nome,autor);
        livrosModel.inserir(novos);
    }


    public void listarController() throws SQLException {
        LivrosModel livrosModel = new LivrosModel();

        System.out.println(ANSI_VERDE + "Listando Livros... " + ANSI_RESET);
        List<Livros> Livros = livrosModel.listar();

        for (Livros listaDeLivros : Livros) {

            System.out.printf("ID: %d - Nome: %s - Autor: %s - Status: %s%s%s%n",listaDeLivros.getId(),listaDeLivros.getNome(), listaDeLivros.getAutor(), ANSI_VERDE, listaDeLivros.getStatusLivro(), ANSI_RESET);
        }
    }
    public void atualizarController() throws SQLException {
        Scanner scan = new Scanner(System.in);
        LivrosModel livrosModel = new LivrosModel();
        Livros atualizar = new Livros();
        String nome, autor;
        int id;

        System.out.println("Atualizar Cadastro de Livro");
        System.out.print("Informe o ID do Livro: ");
        id = scan.nextInt();
        scan.nextLine();
        System.out.print("Informe o Nome do Livro: ");
        nome = scan.nextLine();
        System.out.print("Informe o Autor do Livro: ");
        autor = scan.nextLine();

        atualizar.setId(id);
        atualizar.setNome(nome);
        atualizar.setAutor(autor);
        livrosModel.atualizar(atualizar);
    }

    public void deletarController() throws SQLException {
        Scanner scan = new Scanner(System.in);
        LivrosModel livrosModel = new LivrosModel();

        System.out.println("Deletar Livros do Sistema: ");
        System.out.print("Informe a quantidade de Livros a serem Deletados: ");
        int qtd = scan.nextInt();
        int[] ids = new int[qtd];

        for (int i = 0; i < ids.length; i++ ) {
            System.out.printf("Informe o %d° ID a ser deletado: ",i + 1);
            ids[i] = scan.nextInt();
        }
        for (int del : ids) {
            livrosModel.deletar(del);
        }
    }
}
