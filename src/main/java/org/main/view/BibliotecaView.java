package org.main.view;

import static org.main.utils.Cores.*;

public class BibliotecaView {

    private BibliotecaView() {}

    public static void menuInicial() {
        System.out.print(
                ANSI_CIANO + ANSI_NEGRITO +
                        """
                        +---------------------------+
                        |   SISTEMA DE BIBLIOTECA   |
                        +---------------------------+
                        """ + ANSI_RESET +

                        ANSI_AZUL +
                        """
                          1 - Fazer Login
                          2 - Criar Conta
                          0 - Sair
                        +---------------------------+
                        """ + ANSI_RESET +

                        ANSI_VERDE +
                        "Digite uma opção: " + ANSI_RESET
        );
    }

    public static void menuAdministrador() {
        System.out.print(
                ANSI_CIANO + ANSI_NEGRITO +
                        """
                        +----------------------------------+
                        |      MENU ADMINISTRADOR          |
                        +----------------------------------+
                        """ + ANSI_RESET +

                        ANSI_AZUL +
                        """
                          1 - Gerenciar Livros
                          2 - Listar Alunos
                          3 - Cadastrar Aluno
                          4 - Atualizar Aluno
                          5 - Remover Aluno
                          6 - Empréstimos
                          7 - Relatórios
                          0 - Sair
                        +----------------------------------+
                        """ + ANSI_RESET +

                        ANSI_VERDE +
                        "Digite uma opção: " + ANSI_RESET
        );
    }

    public static void menuAlunos() {
        System.out.print(
                ANSI_CIANO + ANSI_NEGRITO +
                        """
                        +---------------------------+
                        |      MENU ALUNOS          |
                        +---------------------------+
                        """ + ANSI_RESET +

                        ANSI_AZUL +
                        """
                          1 - Ver Perfil
                          2 - Listar Livros
                          3 - Pegar Livro
                          4 - Deletar a conta
                          0 - Voltar
                        +---------------------------+
                        """ + ANSI_RESET +

                        ANSI_VERDE +
                        "Digite uma opção: " + ANSI_RESET
        );
    }

    public static void menuLivros() {
        System.out.print(
                ANSI_CIANO + ANSI_NEGRITO +
                        """
                        +---------------------------+
                        |      MENU LIVROS          |
                        +---------------------------+
                        """ + ANSI_RESET +

                        ANSI_AZUL +
                        """
                          1 - Inserir Livro
                          2 - Listar Livros
                          3 - Atualizar Livro
                          4 - Deletar Livro
                          0 - Voltar
                        +---------------------------+
                        """ + ANSI_RESET +

                        ANSI_VERDE +
                        "Digite uma opção: " + ANSI_RESET
        );
    }
}