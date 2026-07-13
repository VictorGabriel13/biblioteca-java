package org.main.controller;

import org.main.model.EmprestimoModel;
import org.main.model.entities.Usuarios;
import org.main.view.BibliotecaView;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {
    public void iniciar(Usuarios usuarioLogado) throws SQLException {
        Scanner scan = new Scanner(System.in);
        LivrosController livrosController = new LivrosController();
        UsuariosController usuariosController = new UsuariosController();
        EmprestimoController emprestimoController = new EmprestimoController();

        System.out.printf("Bem-vindo, Administrador %s!!!%n", usuarioLogado.getNome());

        while (true) {
        // Menu principal do adm aqui;
        BibliotecaView.menuAdministrador();
            int escolha = scan.nextInt();
            if (escolha == 0) {
                System.out.println("""
                    =================================
                    Obrigado por utilizar o sistema!
                    =================================
            
                         Encerrando a aplicação...
            
                              Até a próxima!
                           Processo finalizado.
                    """);
                return;
            }
            switch (escolha) {
                //Primeiro Menu do adm
                case 1:
                    BibliotecaView.menuLivros();
                    escolha = scan.nextInt();


                    // Segundo Menu do adm
                    switch (escolha) {
                        case 1:
                            livrosController.inserirController();
                            BibliotecaView.menuLivros();
                            break;
                        case 2:
                            livrosController.listarController();
                            BibliotecaView.menuLivros();
                            break;
                        case 3:
                            livrosController.atualizarController();
                            BibliotecaView.menuLivros();
                            break;
                        case 4:
                            livrosController.deletarController();
                            BibliotecaView.menuLivros();
                            break;
                        case 5:
                            BibliotecaView.menuAdministrador();
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
                case 2:
                    usuariosController.listarController();
                    break;
                case 3:
                    usuariosController.cadastrarUser();
                    break;
                case 4:
                    usuariosController.atualizarController();
                    break;
                case 5:
                    usuariosController.deletarController();
                    break;
                case 6:
                    emprestimoController.listarController();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
