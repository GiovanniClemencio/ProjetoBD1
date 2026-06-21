/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Campanha;
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

    // Aquisição de XP
    public void adicionarXP(String idPersonagem, double quantidadeXp) throws SQLException {
        String sql = "UPDATE personagem SET xp = xp + ? WHERE id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, quantidadeXp);
            stmt.setString(2, idPersonagem);

            stmt.executeUpdate();
        }
    }

    // Aquisição de um item
    public void adquirirItem(String idPersonagem, String idItem, int qtd) throws SQLException {
        // Se a combinação de id_personagem e id_item já existir, ele soma a nova quantidade a atual
        String sql = "INSERT INTO inventario (id_personagem, id_item, quantidade) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE quantidade = quantidade + ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.setString(2, idItem);
            stmt.setInt(3, qtd);
            stmt.setInt(4, qtd);

            stmt.executeUpdate();
        }
    }

    // Personagem gasta/perde um item do inventário
    public void removerItem(String idPersonagem, String idItem, int qtd) throws SQLException {
        String sqlSubtrair = "UPDATE inventario SET quantidade = quantidade - ? WHERE id_personagem = ? AND id_item = ?";
        String sqlLimparVazios = "DELETE FROM inventario WHERE id_personagem = ? AND id_item = ? AND quantidade <= 0";

        try (Connection conn = Conexao.conectar()) {
            // Desativa para garantir que ambos serão executados
            conn.setAutoCommit(false);

            try {
                // Subtrai a quantidade
                try (PreparedStatement stmtSub = conn.prepareStatement(sqlSubtrair)) {
                    stmtSub.setInt(1, qtd);
                    stmtSub.setString(2, idPersonagem);
                    stmtSub.setString(3, idItem);
                    stmtSub.executeUpdate();
                }

                // Se a quantidade zerou
                try (PreparedStatement stmtLimpar = conn.prepareStatement(sqlLimparVazios)) {
                    stmtLimpar.setString(1, idPersonagem);
                    stmtLimpar.setString(2, idItem);
                    stmtLimpar.executeUpdate();
                }

                conn.commit();

            } catch (SQLException e) {
                conn.rollback(); // Se der erro desfaz
                throw e;
            } finally {
                conn.setAutoCommit(true); // Restaura o padrão
            }
        }
    }

    // Buscar a quantidade de um item específico no inventário do personagem
    public int buscarQuantidadeItemInventario(String idPersonagem, String idItem) throws SQLException {
        String sql = "SELECT quantidade FROM inventario WHERE id_personagem = ? AND id_item = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.setString(2, idItem);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantidade"); // Retorna a quantidade 
                }
            }
        }
        return 0; // Se não encontrar nenhuma linha, significa que o personagem tem 0 unidades do item
    }

    // Campanhas que participa
    public ArrayList<Campanha> listarCampanhasParticipando(String idPersonagem) throws SQLException {
        ArrayList<Campanha> campanhas = new ArrayList<>();

        // JOIN intermediária e campanha
        String sql = "SELECT c.* FROM campanha c "
                + "JOIN campanha_personagem cp ON c.id_campanha = cp.id_campanha "
                + "WHERE cp.id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Campanha c = new Campanha(
                            rs.getString("nome"),
                            rs.getString("id_mestre")
                    );
                    c.setIdCampanha(rs.getString("id_campanha"));

                    campanhas.add(c);
                }
            }
        }

        return campanhas;
    }

}
