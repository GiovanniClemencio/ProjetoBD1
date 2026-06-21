/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Missao;
import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luan
 */
public class MissaoDAO {

    // Inserir missão
    public void inserir(Missao missao) throws SQLException {
        String sql = "INSERT INTO missao (id_missao, nome, descricao, id_mestre, xp_bonus) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, missao.getIdMissao());
            stmt.setString(2, missao.getNome());
            stmt.setString(3, missao.getDescricao());
            // Pega o ID do jogador que está mestrando
            stmt.setString(4, missao.getMestre() != null ? missao.getMestre().getIdJogador() : null);
            stmt.setInt(5, missao.getXpBonus());

            stmt.executeUpdate();
        }
    }

    // Excluir missão
    public void excluir(String idMissao) throws SQLException {
        String sql = "DELETE FROM missao WHERE id_missao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMissao);
            stmt.executeUpdate();
        }
    }

    // Atualizar missão
    public void atualizar(Missao missao) throws SQLException {
        String sql = "UPDATE missao SET nome = ?, descricao = ?, id_mestre = ?, xp_bonus = ? WHERE id_missao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, missao.getNome());
            stmt.setString(2, missao.getDescricao());
            stmt.setString(3, missao.getMestre() != null ? missao.getMestre().getIdJogador() : null);
            stmt.setInt(4, missao.getXpBonus());
            stmt.setString(5, missao.getIdMissao());

            stmt.executeUpdate();
        }
    }

    // Vincular um personagem a missão
    public void adicionarPersonagem(String idMissao, String idPersonagem) throws SQLException {
        String sql = "INSERT INTO missao_personagem (id_missao, id_personagem) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMissao);
            stmt.setString(2, idPersonagem);
            stmt.executeUpdate();
        }
    }

    // Vincular uma certa quantidade de um monstro a missão
    public void adicionarMonstro(String idMissao, String idMonstro, int qtd) throws SQLException {
        String sql = "INSERT INTO missao_monstro (id_missao, id_monstro, quantidade) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantidade = quantidade + ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMissao);
            stmt.setString(2, idMonstro);
            stmt.setInt(3, qtd);
            stmt.setInt(4, qtd);
            stmt.executeUpdate();
        }
    }

    // Vincular a recompensa a missão
    public void adicionarRecompensa(String idMissao, String idItem, int qtd) throws SQLException {
        String sql = "INSERT INTO missao_item (id_missao, id_item, quantidade) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantidade = quantidade + ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMissao);
            stmt.setString(2, idItem);
            stmt.setInt(3, qtd);
            stmt.setInt(4, qtd);
            stmt.executeUpdate();
        }
    }
}
