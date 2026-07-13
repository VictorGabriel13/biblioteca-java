package org.main.controller;

import org.main.model.entities.Usuarios;
import org.main.view.BibliotecaView;

import java.sql.SQLException;
import java.util.Scanner;

public class AlunoController {
    public void iniciar(Usuarios usuarioLogado) throws SQLException {
        Scanner scan = new Scanner(System.in);
        LivrosController livrosController = new LivrosController();
        UsuariosController usuariosController = new UsuariosController();
        EmprestimoController emprestimoController = new EmprestimoController();

            System.out.printf("Bem-vindo, Aluno %s!!!%n", usuarioLogado.getNome());
        while (true) {
            //Menu principal do aluno aqui;
            BibliotecaView.menuAlunos();
            int choose = scan.nextInt();
            if (choose == 6) {
                System.out.println("Saindo...");
                return;
            }

            switch (choose) {
                case 1:
                    System.out.println(usuarioLogado.exibirPerfil());
                    break;
                case 2:
                    livrosController.listarController();
                    break;
                    //ADD opção de pegar Livro
                case 3:
                    emprestimoController.fazerEmprestimo(usuarioLogado);
                    break;
                case 4:
                    emprestimoController.devolverLivro(usuarioLogado);
                    break;
                case 5:
                    usuariosController.deletarController();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

}
