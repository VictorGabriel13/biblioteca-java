package org.main.model;

import org.main.entities.TipoUsuario;
import org.main.entities.Usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioModel {
    String url = "jdbc:mysql://localhost:3306/biblioteca";
    String user = "root";
    String password = "123456";

    public void inserir(Usuarios usuarios) throws SQLException {

        Connection conn = null;
        PreparedStatement psInsert = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            String insertSQL =
                    "INSERT INTO usuarios (nome, senha, telefone, tipo_usuario) VALUES (?, ?, ?, ?)";

            psInsert = conn.prepareStatement(
                    insertSQL,
                    Statement.RETURN_GENERATED_KEYS
            );

            psInsert.setString(1, usuarios.getNome());
            psInsert.setString(2, usuarios.getSenha());
            psInsert.setString(3, usuarios.getTelefone());
            psInsert.setString(4, usuarios.getTipoUsuario().name());

            psInsert.executeUpdate();

            try (ResultSet rs = psInsert.getGeneratedKeys()) {

                if (rs.next()) {
                    int idGerado = rs.getInt(1);

                    gerarMatricula(conn, idGerado);
                }
            }

        } finally {

            if (psInsert != null) {
                psInsert.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Usuarios> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement psSelect = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            String selectSQL = "SELECT * FROM usuarios";
            psSelect = conn.prepareStatement(selectSQL);
            ResultSet rs = psSelect.executeQuery();

            List<Usuarios> lista = new ArrayList<>();

            while(rs.next()) {

               int id = rs.getInt("id");
               String nome =  rs.getString("nome");
               String matricula = rs.getString("matricula");
               String telefone = rs.getString("telefone");
               String senha = rs.getString("senha");
               TipoUsuario tipoUsuario = TipoUsuario.valueOf(rs.getString("tipo_usuario"));

               Usuarios usuarios = new Usuarios(id,nome,matricula,senha,telefone,tipoUsuario);
                lista.add(usuarios);

            }
               return lista;

        } catch (SQLException e) {
            System.out.printf("""
                    
                    Falha ao listar usuários!!!
                    Erro: %s
                    
                    """, e.getMessage());
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

    public void atualizar(Usuarios usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement psUpdate = null;

        try{
            conn = DriverManager.getConnection(url,user,password);
            String updateSQL = "UPDATE usuarios SET nome = ? telefone = ? senha = ? WHERE id = ?";
            psUpdate = conn.prepareStatement(updateSQL);

            psUpdate.setString(1, usuario.getNome());
            psUpdate.setString(2, usuario.getTelefone());
            psUpdate.setString(3, usuario.getSenha());
            psUpdate.setInt(4, usuario.getId());

            int linhasAfetadas = psUpdate.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.printf("""
                    
                    Dados atualizados com sucesso!
                    Quantidade de linhas Atualizadas %d%n
                    
                    """,linhasAfetadas);
            } else {
                System.out.println("Erro ao Atualizar");
            }


        } catch (SQLException e) {
            System.out.printf("""
                    
                    Falha ao atualizar matricula!!!
                    Erro: %s%n
                    """, e.getMessage());
        } finally {
            if (psUpdate != null) {
                psUpdate.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private void gerarMatricula(Connection conn, int idUsuario) throws SQLException {

        String matricula = Usuarios.getMatricula(idUsuario);

        String updateSQL =
                "UPDATE usuarios SET matricula = ? WHERE id = ?";

        try (PreparedStatement psUpdate = conn.prepareStatement(updateSQL)) {

            psUpdate.setString(1, matricula);
            psUpdate.setInt(2, idUsuario);

            psUpdate.executeUpdate();
        }
    }
    public Usuarios login(String matricula, String senha) throws SQLException {

        String sql = """
        SELECT *
        FROM usuarios
        WHERE matricula = ? AND senha = ?
    """;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Usuarios(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("matricula"),
                            TipoUsuario.valueOf(rs.getString("tipo_usuario"))
                    );
                }
                return null;
            }
        }
    }
}
