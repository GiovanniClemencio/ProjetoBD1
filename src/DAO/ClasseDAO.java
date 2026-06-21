/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Classe;
import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ClasseDAO {
    // Nova classe
    public void inserir(Classe classe) throws SQLException {
        String sql = "INSERT INTO classe (id_classe, nome, descricao) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, classe.getIdClasse());
            stmt.setString(2, classe.getNome());
            stmt.setString(3, classe.getDescricao());

            stmt.executeUpdate();
        }
    }
    

    // Excluir classe
    public void excluir(String idClasse) throws SQLException {
        String sql = "DELETE FROM classe WHERE id_classe = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idClasse);
            stmt.executeUpdate();
        }
    }

    // Atualizar dados da classe
    public void atualizar(Classe classe) throws SQLException {
        String sql = "UPDATE classe SET nome = ?, descricao = ? WHERE id_classe = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, classe.getNome());
            stmt.setString(2, classe.getDescricao());
            stmt.setString(3, classe.getIdClasse());

            stmt.executeUpdate();
        }
    }

    // Listar todas as classes cadastradas
    public ArrayList<Classe> listarTodos() throws SQLException {
        ArrayList<Classe> classes = new ArrayList<>();
        String sql = "SELECT * FROM classe ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Classe c = new Classe(
                        rs.getString("nome"),
                        rs.getString("descricao")
                );
                c.setIdClasse(rs.getString("id_classe"));
                classes.add(c);
            }
        }
        return classes;
    } 
}
