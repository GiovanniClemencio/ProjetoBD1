/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Personagem;
import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luan
 */
public class PersonagemDAO {

    // Inserção
    public void salvar(Personagem personagem) throws SQLException {
        String sql = "INSERT INTO personagem (id_personagem, nome, carga_maxima, xp, vida, forca, destreza, constituicao, inteligencia, sabedoria, carisma, id_jogador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, personagem.getIdPersonagem());
            stmt.setString(2, personagem.getNome());
            stmt.setDouble(3, personagem.getCargaMaxima());
            stmt.setDouble(4, personagem.getXp());
            stmt.setInt(5, personagem.getVida());
            stmt.setInt(6, personagem.getForca());
            stmt.setInt(7, personagem.getDestreza());
            stmt.setInt(8, personagem.getConstituicao());
            stmt.setInt(9, personagem.getInteligencia());
            stmt.setInt(10, personagem.getSabedoria());
            stmt.setInt(11, personagem.getCarisma());
            stmt.setString(12, personagem.getIdJogador());

            stmt.executeUpdate();
        }
    }

    // Busca
    public Personagem buscarPorId(String idPersonagem) throws SQLException {
        String sql = "SELECT * FROM personagem WHERE id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
                    return p;
                }
            }
        }
        return null;
    }

    // Atualizar dados
    public void atualizar(Personagem personagem) throws SQLException {
        String sql = "UPDATE personagem SET nome = ?, carga_maxima = ?, xp = ?, vida = ?, forca = ?, destreza = ?, constituicao = ?, inteligencia = ?, sabedoria = ?, carisma = ? WHERE id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, personagem.getNome());
            stmt.setDouble(2, personagem.getCargaMaxima());
            stmt.setDouble(3, personagem.getXp());
            stmt.setInt(4, personagem.getVida());
            stmt.setInt(5, personagem.getForca());
            stmt.setInt(6, personagem.getDestreza());
            stmt.setInt(7, personagem.getConstituicao());
            stmt.setInt(8, personagem.getInteligencia());
            stmt.setInt(9, personagem.getSabedoria());
            stmt.setInt(10, personagem.getCarisma());
            stmt.setString(11, personagem.getIdPersonagem());

            stmt.executeUpdate();
        }
    }

    // Deletar personagem
    public void deletar(String idPersonagem) throws SQLException {
        String sql = "DELETE FROM personagem WHERE id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.executeUpdate();
        }
    }

    // Aquisição de XP
    public void adicionarXP(String idPersonagem, double quantidadeXp) throws SQLException {
        String sql = "UPDATE personagem SET xp = xp + ? WHERE id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, quantidadeXp);
            stmt.setString(2, idPersonagem);

            stmt.executeUpdate();
        }
    }
}
