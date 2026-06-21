/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Classe;
import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
    // Vincular classe a um personagem
    public void adicionarClasseAoPersonagem(String idPersonagem, String idClasse, int nivelInicial) throws SQLException {
        String sql = "INSERT INTO classe_personagem (id_personagem, id_classe, nivel_classe) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.setString(2, idClasse);
            stmt.setInt(3, nivelInicial);

            stmt.executeUpdate();
        }
    }
    
    // Aumentar nível da classe de um personagem
    public void uparNivelClasse(String idPersonagem, String idClasse) throws SQLException {
        String sql = "UPDATE classe_personagem SET nivel_classe = nivel_classe + 1 " +
                     "WHERE id_personagem = ? AND id_classe = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.setString(2, idClasse);

            stmt.executeUpdate();
        }
    }
    
    // Reduzir o nível de uma classe (Caso o usuário de um clique acidental de aumento de nivel)
    public void reduzirNivelClasse(String idPersonagem, String idClasse) throws SQLException {
        // WHERE garante que só altera se o nível atual for maior que 1
        String sql = "UPDATE classe_personagem SET nivel_classe = nivel_classe - 1 " +
                     "WHERE id_personagem = ? AND id_classe = ? AND nivel_classe > 1";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.setString(2, idClasse);

            stmt.executeUpdate();
        }
    }
}
