/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.ItemDTO;
import Classes.Missao;
import Classes.Personagem;
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
            stmt.setString(4, missao.getMestre());
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
            stmt.setString(3, missao.getMestre());
            stmt.setInt(4, missao.getXpBonus());
            stmt.setString(5, missao.getIdMissao());

            stmt.executeUpdate();
        }
    }

    // Vincular um personagem a missão
    public void adicionarPersonagem(String idCampanha, String idMissao, String idPersonagem) throws SQLException {
        String sql = "INSERT INTO campanha_missao_personagem (id_campanha, id_missao, id_personagem) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idCampanha);
            stmt.setString(2, idMissao);
            stmt.setString(3, idPersonagem);
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

    // Retornar o XP da missão
    public int buscarXpBonusDaMissao(String idMissao) throws SQLException {
        String sql = "SELECT xp_bonus FROM missao WHERE id_missao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idMissao);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("xp_bonus");
                }
            }
        }
        return 0;
    }

    // Listar personagens participantes da missão em uma campanha
    public ArrayList<Personagem> listarParticipantesDaMissao(String idCampanha, String idMissao) throws SQLException {
        ArrayList<Personagem> participantes = new ArrayList<>();
        
        // SQL com JOIN para buscar os dados direto da tabela personagem
        String sql = "SELECT p.* FROM personagem p " +
                     "JOIN campanha_missao_personagem cmp ON p.id_personagem = cmp.id_personagem " +
                     "WHERE cmp.id_campanha = ? AND cmp.id_missao = ? " +
                     "ORDER BY p.nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idMissao);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Personagem p = new Personagem(
                        rs.getString("nome"),
                        rs.getDouble("carga_maxima"),
                        rs.getDouble("xp"),
                        rs.getInt("vida"),
                        rs.getInt("forca"),
                        rs.getInt("destreza"),
                        rs.getInt("constituicao"),
                        rs.getInt("inteligencia"),
                        rs.getInt("sabedoria"),
                        rs.getInt("carisma"),
                        rs.getString("id_jogador")
                    );
                    
                    p.setIdPersonagem(rs.getString("id_personagem"));
                    participantes.add(p);
                }
            }
        }
        return participantes;
    }

    // Listar todas as recompensas de uma missão
    public ArrayList<ItemDTO> listarRecompensasDaMissao(String idMissao) throws SQLException {
        ArrayList<ItemDTO> recompensas = new ArrayList<>();
        String sql = "SELECT id_item, quantidade FROM missao_item WHERE id_missao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMissao);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemDTO drop = new ItemDTO(
                        rs.getString("id_item"),
                        rs.getInt("quantidade")
                    );
                    recompensas.add(drop);
                }
            }
        }
        return recompensas;
    }

    // Atualizar o status da missão
    public void atualizarStatusMissao(String idCampanha, String idMissao, boolean concluido) throws SQLException {
        String sql = "UPDATE campanha_missao SET concluido = ? WHERE id_campanha = ? AND id_missao = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, concluido);
            stmt.setString(2, idCampanha);
            stmt.setString(3, idMissao);

            stmt.executeUpdate();
        }
    }

    // Listar todas as missões cadastradas
    public ArrayList<Missao> listarTodos() throws SQLException {
        ArrayList<Missao> missoes = new ArrayList<>();
        String sql = "SELECT * FROM missao ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Missao m = new Missao(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("id_mestre"),
                        rs.getInt( "xp_bonus")
                );
                m.setIdMissao(rs.getString("id_missao"));
                missoes.add(m);
            }
        }
        return missoes;
    } 
}
