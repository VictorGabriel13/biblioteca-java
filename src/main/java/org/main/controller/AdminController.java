package org.main.controller;

import org.main.entities.Usuarios;
import org.main.view.BibliotecaView;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {
    public void iniciar(Usuarios usuarioLogado) throws SQLException {
        Scanner scan = new Scanner(System.in);
        LivrosController livrosController = new LivrosController();
        UsuariosController usuariosController = new UsuariosController();

        System.out.printf("Bem-vindo, Administrador %s!!!%n", usuarioLogado.getNome());

        while (true) {
        // Menu principal do adm aqui;
        BibliotecaView.menuAdministrador();
            int escolha = scan.nextInt();
            if (escolha == 0) {
                System.out.println("Saindo...");
                return;
            }
            switch (escolha) {
                case 1:
                    BibliotecaView.menuLivros();
                    escolha = scan.nextInt();
                    switch (escolha) {
                        case 1:
                            livrosController.inserirController();
                            break;
                        case 2:
                            livrosController.listarController();
                            break;
                    }
                case 2:
                    usuariosController.listarController();
                    break;
            }
        }

    }
}
