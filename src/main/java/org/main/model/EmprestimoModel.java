package org.main.model;
import org.main.model.entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmprestimoModel {
    String url = "jdbc:mysql://localhost:3306/biblioteca";
    String user = "root";
    String password = "123456";

    public void inserir(Emprestimo emprestimo) throws SQLException {
        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DriverManager.getConnection(url,user,password);
            String insertSQL= """
                     INSERT INTO emprestimos (
                            id_usuario,
                            id_livro,
                            data_emprestimo,
                            data_devolucao_prevista,
                            status
                        ) VALUES (?, ?, ?, ?, ?);
                    """;
            psInsert = conn.prepareStatement(insertSQL);

            psInsert.setInt(1, emprestimo.getUsuarios().getId());
            psInsert.setInt(2, emprestimo.getLivros().getId());
            psInsert.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            psInsert.setDate(4, Date.valueOf(emprestimo.getDataDevolucaoPrevista()));
            psInsert.setString(5, emprestimo.getStatusEmprestimo().name());

            int linhasInseridas = psInsert.executeUpdate();

            String updateSQl = "UPDATE livros SET status = ? WHERE id = ?";

            preparedStatement = conn.prepareStatement(updateSQl);

            preparedStatement.setString(1, String.valueOf(StatusLivro.EMPRESTADO));
            preparedStatement.setInt(2, emprestimo.getLivros().getId());


            if (linhasInseridas > 0) {
                preparedStatement.executeUpdate();
                System.out.println("Emprestimo realizado com sucesso!!!");
            } else {
                System.out.println("Falha ao realizar emprestimo.");
            }


        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (psInsert != null) {
                psInsert.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void atualizar(Emprestimo emprestimo) throws SQLException{
        Connection conn = null;
        PreparedStatement psUpdate = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            String updateSQL= """
                     INSERT INTO emprestimos (
                            id_usuario,
                            id_livro,
                            data_emprestimo,
                            data_devolucao_prevista,
                            status
                        ) VALUES (?, ?, ?, ?, ?);
                    """;
            psUpdate = conn.prepareStatement(updateSQL);

            psUpdate.setInt(1, emprestimo.getUsuarios().getId());
            psUpdate.setInt(2, emprestimo.getLivros().getId());
            psUpdate.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            psUpdate.setDate(4, Date.valueOf(emprestimo.getDataDevolucaoPrevista()));
            psUpdate.setString(5, emprestimo.getStatusEmprestimo().name());

            int linhasAfetadas = psUpdate.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Dados atualizados com sucesso!!!");
            } else {
                System.out.println("Erro ao atualizar dados.");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());;
        } finally {
            if (psUpdate != null) {
                psUpdate.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public List<Emprestimo> listar() {

        List<Emprestimo> listaEmprestimo = new ArrayList<>();

        String selectSQL = """
    SELECT
        e.id AS emprestimo_id,
        e.data_emprestimo,
        e.data_devolucao_prevista,
        e.data_devolucao_real,
        e.status,

        u.id AS usuario_id,
        u.nome AS usuario_nome,
        u.matricula,
        u.telefone,
        u.senha,
        u.tipo_usuario,

        l.id AS livro_id,
        l.nome AS livro_nome,
        l.autor,
        l.status AS livro_status

    FROM emprestimos e
    INNER JOIN usuarios u ON e.id_usuario = u.id
    INNER JOIN livros l ON e.id_livro = l.id;
    """;

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement psSelect = conn.prepareStatement(selectSQL);
                ResultSet rs = psSelect.executeQuery()
        ) {

            while (rs.next()) {

                Usuarios usuario = new Usuarios(
                        rs.getInt("usuario_id"),
                        rs.getString("usuario_nome"),
                        rs.getString("matricula"),
                        rs.getString("telefone")
                );

                Livros livro = new Livros(
                        rs.getInt("livro_id"),
                        rs.getString("livro_nome"),
                        rs.getString("autor"),
                        StatusLivro.valueOf(rs.getString("livro_status"))
                );

                LocalDate dataDevolucaoReal = null;
                Date data = rs.getDate("data_devolucao_real");
                if (data != null) {
                    dataDevolucaoReal = data.toLocalDate();
                }

                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("emprestimo_id"),
                        usuario,
                        livro,
                        rs.getDate("data_emprestimo").toLocalDate(),
                        rs.getDate("data_devolucao_prevista").toLocalDate(),
                        dataDevolucaoReal,
                        StatusEmprestimo.valueOf(rs.getString("status"))
                );

                listaEmprestimo.add(emprestimo);
            }
            return listaEmprestimo;

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void deletar(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement psDelete = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            String deleteSQL = "DELETE FROM emprestimo WHERE id = ?";
            psDelete = conn.prepareStatement(deleteSQL);

            psDelete.setInt(1, id);
          int linhasAfetadas =  psDelete.executeUpdate();
          if (linhasAfetadas > 0) {
              System.out.println("Linhas deletadas com Sucesso!!!");
          } else  {
              System.out.println("Falha ao deletar emprestimo");
          }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (psDelete != null) {
                psDelete.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void devolucao(Emprestimo devolucao) throws SQLException {
        Connection conn = null;
        PreparedStatement psUpdate = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = DriverManager.getConnection(url,user,password);
            String returnSQL = """
                    UPDATE emprestimos
                    SET
                    data_devolucao_real = ?,
                    status = ?
                    WHERE id = ?;
                    """;
            psUpdate = conn.prepareStatement(returnSQL);

            psUpdate.setDate(1, Date.valueOf(devolucao.getDataDevolucaoReal()));
            psUpdate.setString(2, devolucao.getStatusEmprestimo().name());
            psUpdate.setInt(3, devolucao.getId());

            int linhasAfetadas = psUpdate.executeUpdate();

            String updateSQl = "UPDATE livros SET status = ? WHERE id = ?";

            preparedStatement = conn.prepareStatement(updateSQl);

            preparedStatement.setString(1, String.valueOf(StatusLivro.DISPONIVEL));
            preparedStatement.setInt(2, devolucao.getLivros().getId());


           if (linhasAfetadas > 0) {
               preparedStatement.executeUpdate();
               System.out.println("Devolução realizada com Sucesso!!!");
           } else {
               System.out.println("Falha ao realizar devolução.");
           }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally {
            if (psUpdate != null) {
                psUpdate.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }
    public int getIdEmprestimo(int idUsuario, int idLivro) throws SQLException {
        Connection conn = null;
        PreparedStatement psSelect = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            String selectSQL = """
                    SELECT *
                    FROM emprestimos
                    WHERE id_usuario = ?
                      AND id_livro = ?
                    ORDER BY id DESC
                    LIMIT 1;
                    """;

            psSelect = conn.prepareStatement(selectSQL);
            psSelect.setInt(1, idUsuario);
            psSelect.setInt(2, idLivro);

            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (psSelect != null) {
                psSelect.close();
            }
            if (conn != null) {
                conn.close();
                }
            }
        return -1;
        }
    }
