/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Campanha;
import Classes.Jogador;
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
public class CampanhaDAO {

    // Inserção
    public void inserir(Campanha campanha) throws SQLException {
        String sql = "INSERT INTO campanha (id_campanha, nome) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, campanha.getIdCampanha());
            stmt.setString(2, campanha.getNome());

            stmt.executeUpdate();
        }
    }

    // Excluir campanha
    public void excluir(String idCampanha) throws SQLException {
        String sql = "DELETE FROM campanha WHERE id_campanha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.executeUpdate();
        }
    }

    // Atualizar campanha
    public void atualizar(Campanha campanha) throws SQLException {
        String sql = "UPDATE campanha SET nome = ?, id_mestre = ? WHERE id_campanha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, campanha.getNome());
            stmt.setString(2, campanha.getIdMestre());
            stmt.setString(3, campanha.getIdCampanha());

            stmt.executeUpdate();
        }
    }

    // Busca
    public Campanha buscarPorId(String idCampanha) throws SQLException {
        String sql = "SELECT * FROM campanha WHERE id_campanha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Campanha c = new Campanha(
                            rs.getString("nome"),
                            rs.getString("id_mestre")
                    );
                    c.setIdCampanha(rs.getString("id_campanha"));
                    return c;
                }
            }
        }
        return null;
    }

    // Personagens participantes
    public ArrayList<Personagem> listarPersonagensParticipantes(String idCampanha) throws SQLException {
        ArrayList<Personagem> participantes = new ArrayList<>();

        // JOIN
        String sql = "SELECT p.* FROM personagem p "
                + "JOIN campanha_personagem cp ON p.id_personagem = cp.id_personagem "
                + "WHERE cp.id_campanha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Instancia
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
                    // ID
                    p.setIdPersonagem(rs.getString("id_personagem"));

                    // Adiciona o personagem na lista de participantes
                    participantes.add(p);
                }
            }
        }

        return participantes;
    }

    // Vincular um personagem a campanha
    public void adicionarPersonagem(String idCampanha, String idPersonagem) throws SQLException {
        String sql = "INSERT INTO campanha_personagem (id_campanha, id_personagem) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idPersonagem);

            stmt.executeUpdate();
        }
    }

    // Desvincula um personagem de uma campanha
    public void removerPersonagem(String idCampanha, String idPersonagem) throws SQLException {
        String sql = "DELETE FROM campanha_personagem WHERE id_campanha = ? AND id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idPersonagem);

            stmt.executeUpdate();
        }
    }

    // Vincular um mestre a campanha
    public void adicionarMestreACampanha(String idCampanha, String idMestre) throws SQLException {
        String sql = "UPDATE campanha SET id_mestre = ? WHERE id_campanha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMestre);
            stmt.setString(2, idCampanha);

            stmt.executeUpdate();
        }
    }

    // Desvincula um mestre a campanha
    public void removerMestreDaCampanha(String idCampanha, String idMestre) throws SQLException {
        String sql = "UPDATE campanha SET id_mestre = NULL WHERE id_campanha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMestre);
            stmt.setString(2, idCampanha);

            stmt.executeUpdate();
        }
    }

    // Vincular uma missão a uma campanha
    public void adicionarMissaoAcampanha(String idCampanha, String idMissao) throws SQLException {
        String sql = "INSERT INTO campanha_missao (id_campanha, id_missao, concluido) VALUES (?, ?, false)" + "ON DUPLICATE KEY UPDATE concluido = false";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idMissao);

            stmt.executeUpdate();
        }
    }

    // Desvincular uma missão da campanha
    public void removerMissaoDaCampanha(String idCampanha, String idMissao) throws SQLException {
        String sql = "DELETE FROM campanha_missao WHERE id_campanha = ? AND id_missao = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idMissao);

            stmt.executeUpdate();
        }
    }

    // Listar todos os jogadores únicos que possuem personagem na campanha
    public ArrayList<Jogador> listarJogadoresPorCampanha(String idCampanha) throws SQLException {

        ArrayList<Jogador> jogadores = new ArrayList<>();

        String sql = "SELECT DISTINCT j.id_jogador, j.nome FROM jogador j "
                + "JOIN personagem p ON j.id_jogador = p.id_jogador "
                + "JOIN campanha_personagem cp ON p.id_personagem = cp.id_personagem "
                + "WHERE cp.id_campanha = ? ORDER BY j.nome ASC";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Jogador j = new Jogador(rs.getString("nome"));
                    j.setIdJogador(rs.getString("id_jogador"));
                    jogadores.add(j);
                }
            }
        }
        return jogadores;
    }

    // Listar todas as campanhas
    public ArrayList<Campanha> listarTodos() throws SQLException {
        ArrayList<Campanha> campanhas = new ArrayList<>();
        String sql = "SELECT * FROM campanha ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Campanha c = new Campanha(
                        rs.getString("nome"),
                        rs.getString("id_mestre")
                );
                c.setIdCampanha(rs.getString("id_campanha"));
                campanhas.add(c);
            }
        }
        return campanhas;
    }

    // Listar todas as missões vinculadas a campanha
    public ArrayList<Missao> listarMissoesPorCampanha(String idCampanha) throws SQLException {
        ArrayList<Missao> missoes = new ArrayList<>();
        String sql = "SELECT m.* FROM missao m " +
                     "JOIN campanha_missao cm ON m.id_missao = cm.id_missao " +
                     "WHERE cm.id_campanha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Missao m = new Missao(rs.getString("nome"), rs.getString("descricao"), rs.getString("id_mestre"), rs.getInt("xp_bonus"));
                    m.setIdMissao(rs.getString("id_missao"));

                    missoes.add(m);
                }
            }
        }
        return missoes;
    }
}
