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

    // Busca
    public Campanha buscarPorId(String idCampanha) throws SQLException {
        String sql = "SELECT * FROM campanha WHERE id_campanha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Campanha c = new Campanha(
                            rs.getString("nome")
                    );
                    c.setIdCampanha(rs.getString("id_personagem"));
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

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idPersonagem);

            stmt.executeUpdate();
        }
    }

    // Vincular um mestre a campanha
    public void adicionarMestre(String idCampanha, String idMestre) throws SQLException {
        String sql = "INSERT INTO campanha_mestre (id_campanha, id_Mestre) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idMestre);

            stmt.executeUpdate();
        }
    }

    // Desvincula um mestre a campanha
    public void removerMestre(String idCampanha, String idMestre) throws SQLException {
        String sql = "DELETE FROM campanha_mestre WHERE id_campanha = ? AND id_personagem = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCampanha);
            stmt.setString(2, idMestre);

            stmt.executeUpdate();
        }
    }
}
