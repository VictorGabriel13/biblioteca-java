package org.main.controller;

import org.main.entities.TipoUsuario;
import org.main.entities.Usuarios;
import org.main.model.UsuarioModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UsuariosController {
    public void  cadastrarUser() {
        Scanner scan = new Scanner(System.in);

        try {

            UsuarioModel usuarioModel = new UsuarioModel();
            Usuarios usuarios;

            System.out.print("Informe o Nome: ");
            String nome = scan.nextLine();
            System.out.print("Informe a senha: ");
            String senha = scan.nextLine();
            System.out.print("Deseja informa o seu telefone? (S/N): ");
            char escolha = scan.nextLine().charAt(0);

            if (Character.toUpperCase(escolha) != 'N') {
                System.out.print("Informe o seu Telefone: ");
                String telefone = scan.nextLine();

                usuarios = new Usuarios(nome, senha, telefone, TipoUsuario.ALUNO);

            } else {
                usuarios = new Usuarios(nome, senha, TipoUsuario.ALUNO);
            }

            usuarioModel.inserir(usuarios);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public Usuarios entrar() throws SQLException {
        Scanner scan = new Scanner(System.in);
        UsuarioModel usuarioModel = new UsuarioModel();


            System.out.print("Informe a sua Matricula: ");
            String matricula = scan.next();
            System.out.print("Informe a sua Senha: ");
            String senha = scan.next();

            Usuarios usuario = usuarioModel.login(matricula, senha);

            if (usuario == null) {
                System.out.println("Login Inválido!!!");
                return null;
            }

            return usuario;
    }

    public void listarController() throws SQLException {
        UsuarioModel usuarioModel =  new UsuarioModel();

        List<Usuarios> lista = usuarioModel.listar();
        for (Usuarios listar : lista) {

            int id = listar.getId();
            String nome = listar.getNome();
            String matricula = Usuarios.getMatricula(id);
            String telefone = listar.getTelefone();
            String tipoUser = String.valueOf(listar.getTipoUsuario());

            System.out.printf("""
                    ID: %d - Nome: %s - Matricula: %s - Telefone: %s - Tipo de Usuário: %s%n
                    """, id, nome, matricula, telefone, tipoUser);
        }
    }

    public void atualizarController() throws SQLException {
        Scanner scan = new Scanner(System.in);
        UsuarioModel usuarioModel = new UsuarioModel();
        Usuarios atualizar;

        System.out.print("Informe o seu ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.print("Informe o seu nome: ");
        String nome = scan.nextLine();
        System.out.print("Informe a sua senha: ");
        String senha = scan.nextLine();

        System.out.print("Deseja informa o seu telefone? (S/N): ");
        char escolha = scan.nextLine().charAt(0);

        if (Character.toUpperCase(escolha) != 'N') {
            System.out.print("Informe o seu Telefone: ");
            String telefone = scan.nextLine();

            atualizar = new Usuarios(id, nome, senha, telefone);
        } else {
            atualizar = new Usuarios(id, nome, senha);
        }
        usuarioModel.atualizar(atualizar);
    }

    public void deletarController() throws SQLException {
        Scanner scan = new Scanner(System.in);
        UsuarioModel usuarioModel = new UsuarioModel();
        System.out.print("Informe a quantidade de IDs a serem deletados: ");
        int qtd = scan.nextInt();

        int[] ids = new int[qtd];
        for (int i = 0; i < ids.length; i++) {
            System.out.printf("Informe o ID do %d° Usuário a ser deletado: ", i + 1);
            ids[i] = scan.nextInt();
        }
        for (int id : ids) {
            usuarioModel.deletar(id);
        }
    }
}
