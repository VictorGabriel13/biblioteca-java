package org.main.model;
import org.main.model.entities.Livros;
import org.main.model.entities.StatusLivro;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LivrosModel {
    String url = "jdbc:mysql://localhost:3306/biblioteca";
    String user = "root";
    String password = "123456";

    public void inserir(Livros livros) throws SQLException {
        Connection conn = null;
        PreparedStatement psInsert = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            String insertSQL = "INSERT INTO livros (nome, autor) VALUES (?, ?);";

            psInsert = conn.prepareStatement(insertSQL);

            psInsert.setString(1, livros.getNome());
            psInsert.setString(2, livros.getAutor());


            // Atualiza e fecha a conexão com o banco;
            psInsert.executeUpdate();
        }
        catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (psInsert != null) {
                psInsert.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    public List<Livros> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement psSelect = null;

        try {

        conn = DriverManager.getConnection(url, user, password);

        ArrayList<Livros> lista = new ArrayList<>();

        String selectSQL = "SELECT * FROM livros";

        psSelect = conn.prepareStatement(selectSQL);
        ResultSet rs = psSelect.executeQuery();

        System.out.println("\nLista de Livros:");

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String autor = rs.getString("autor");
             StatusLivro status = StatusLivro.valueOf(rs.getString("status"));

            Livros livros = new Livros(id, nome, autor, status);
            lista.add(livros);
        }
           return lista;
    } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());

            // ou eu poderia colocar return List.of();
        return Collections.emptyList();
        } finally {
            if (psSelect != null) {
                psSelect.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void atualizar(Livros livros) throws SQLException {

        Connection conn = null;
        PreparedStatement psUpdate = null;


        try {
            conn = DriverManager.getConnection(url,user,password);

            String updateSQL = "UPDATE livros SET nome = ?, autor = ? WHERE id = ?";
            psUpdate = conn.prepareStatement(updateSQL);

            psUpdate.setString(1, livros.getNome());
            psUpdate.setString(2, livros.getAutor());
            psUpdate.setInt(3, livros.getId());

            int linhasAfetadas = psUpdate.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.printf("""
                    
                    Dados atualizados com sucesso!
                    Quantidade de linhas Atualizadas %d%n
                    
                    """,linhasAfetadas);
            } else {
                System.out.println("Erro ao Atualizar");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (psUpdate != null) {
                psUpdate.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void deletar(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement psDelete = null;

        try {
            conn = DriverManager.getConnection(url,user,password);

            String deleteSQL = "DELETE FROM livros WHERE id = ?";
            psDelete = conn.prepareStatement(deleteSQL);

            psDelete.setInt(1,id);
            int linhasAfetadas = psDelete.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.printf("%d IDs deletados com Sucesso!!!%n", linhasAfetadas);
            } else {
                System.out.println("Falha ao deletar Livros.%n");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (psDelete != null){
                conn.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Livros> buscarLivro(String nomeLivro) throws SQLException {

        Connection conn = null;
        PreparedStatement psSelect = null;

        try {

            conn = DriverManager.getConnection(url, user, password);

            ArrayList<Livros> lista = new ArrayList<>();

            String selectSQL = "SELECT * FROM livros WHERE nome = ?";

            psSelect = conn.prepareStatement(selectSQL);
            psSelect.setString(1, nomeLivro);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String autor = rs.getString("autor");
                StatusLivro status = StatusLivro.valueOf(rs.getString("status"));

                Livros livros = new Livros(id, nome, autor, status);
                lista.add(livros);
            }
            return lista;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());

            // ou eu poderia colocar return List.of();
            return Collections.emptyList();
        } finally {
            if (psSelect != null) {
                psSelect.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }
}
