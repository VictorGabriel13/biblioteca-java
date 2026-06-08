package org.main;
import org.main.controller.AdminController;
import org.main.controller.AlunoController;
import org.main.controller.LivrosController;
import org.main.controller.UsuariosController;
import org.main.entities.TipoUsuario;
import org.main.entities.Usuarios;
import org.main.view.BibliotecaView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        try {
            Scanner scan = new Scanner(System.in);
            UsuariosController usuariosController = new UsuariosController();


            Usuarios usuarioLogado = null;

            while(usuarioLogado == null) {
               BibliotecaView.menuInicial();
                int choose = scan.nextInt();
                scan.nextLine();

                if (choose == 0) {
                    System.out.println("Saindo...");
                    return;
                }

                switch (choose) {
                    case 1:
                        //Entrar na conta
                        usuarioLogado = usuariosController.entrar();
                        break;
                    case 2:
                        //Cadastrar Users
                        usuariosController.cadastrarUser();
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            }

            // Direciona o fluxo do sistema conforme o tipo de usuário logado
                if (usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN) {
                    AdminController adminController = new AdminController();
                    adminController.iniciar(usuarioLogado);
                } else {
                    AlunoController alunoController = new AlunoController();
                    alunoController.iniciar(usuarioLogado);
                }
        } catch (InputMismatchException e) {
            System.out.println("Digite apenas números!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}