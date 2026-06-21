/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.ItemDropDTO;
import Classes.Monstro;
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
public class MonstroDAO {

    // Inserir novo monstro
    public void inserir(Monstro monstro) throws SQLException {
        String sql = "INSERT INTO monstro (id_monstro, nome, descricao, tipo, vida, "
                + "forca, destreza, constituicao, inteligencia, sabedoria, carisma, cr) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, monstro.getIdMonstro());
            stmt.setString(2, monstro.getNome());
            stmt.setString(3, monstro.getDescricao());
            stmt.setString(4, monstro.getTipo());
            stmt.setInt(5, monstro.getVida());
            stmt.setInt(6, monstro.getForca());
            stmt.setInt(7, monstro.getDestreza());
            stmt.setInt(8, monstro.getConstituicao());
            stmt.setInt(9, monstro.getInteligencia());
            stmt.setInt(10, monstro.getSabedoria());
            stmt.setInt(11, monstro.getCarisma());
            stmt.setInt(12, monstro.getCr());

            stmt.executeUpdate();
        }
    }

    // Excluir monstro
    public void excluir(String idMonstro) throws SQLException {
        String sql = "DELETE FROM monstro WHERE id_monstro = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMonstro);
            stmt.executeUpdate();
        }
    }

    // Atualizar monstro
    public void atualizar(Monstro monstro) throws SQLException {
        String sql = "UPDATE monstro SET nome = ?, descricao = ?, tipo = ?, vida = ?, forca = ?, destreza = ?, constituicao = ?, inteligencia = ?, sabedoria = ?, carisma = ?, cr = ? WHERE id_monstro = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, monstro.getNome());
            stmt.setString(2, monstro.getDescricao());
            stmt.setString(3, monstro.getTipo());
            stmt.setInt(4, monstro.getVida());
            stmt.setInt(5, monstro.getForca());
            stmt.setInt(6, monstro.getDestreza());
            stmt.setInt(7, monstro.getConstituicao());
            stmt.setInt(8, monstro.getInteligencia());
            stmt.setInt(9, monstro.getSabedoria());
            stmt.setInt(10, monstro.getCarisma());
            stmt.setInt(11, monstro.getCr());
            stmt.setString(12, monstro.getIdMonstro());

            stmt.executeUpdate();
        }
    }

    // Buscar monstro por ID
    public Monstro buscarPorId(String id) throws SQLException {
        String sql = "SELECT * FROM monstro WHERE id_monstro = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Monstro m = new Monstro(
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getString("tipo"),
                            rs.getInt("vida"),
                            rs.getInt("forca"),
                            rs.getInt("destreza"),
                            rs.getInt("constituicao"),
                            rs.getInt("inteligencia"),
                            rs.getInt("sabedoria"),
                            rs.getInt("carisma"),
                            rs.getInt("cr")
                    );
                    m.setIdMonstro(rs.getString("id_monstro"));
                    return m;
                }
            }
        }
        return null;
    }

    // Listar todos os monstros
    public ArrayList<Monstro> listarTodos() throws SQLException {
        ArrayList<Monstro> monstros = new ArrayList<>();
        String sql = "SELECT * FROM monstro ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Monstro m = new Monstro(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("tipo"),
                        rs.getInt("vida"),
                        rs.getInt("forca"),
                        rs.getInt("destreza"),
                        rs.getInt("constituicao"),
                        rs.getInt("inteligencia"),
                        rs.getInt("sabedoria"),
                        rs.getInt("carisma"),
                        rs.getInt("cr")
                );
                m.setIdMonstro(rs.getString("id_monstro"));
                monstros.add(m);
            }
        }
        return monstros;
    }

    // Adicionar drop ao monstro
    public void adicionarDrop(String idMonstro, String idItem, int quantidade) throws SQLException {
        // Se o monstro já tiver esse item na tabela de drops atualiza a quantidade senão insere
        String sql = "INSERT INTO monstro_drop (id_monstro, id_item, quantidade) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE quantidade = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMonstro);
            stmt.setString(2, idItem);
            stmt.setInt(3, quantidade);
            stmt.setInt(4, quantidade); // Atualiza a quantidade se já tiver o item

            stmt.executeUpdate();
        }
    }

    // Remover um item da lista de drops do monstro
    public void removerDrop(String idMonstro, String idItem) throws SQLException {
        String sql = "DELETE FROM monstro_drop WHERE id_monstro = ? AND id_item = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMonstro);
            stmt.setString(2, idItem);

            stmt.executeUpdate();
        }
    }

    // Método auxiliar no MonstroDAO para listar os itens que ele carrega
    public ArrayList<ItemDropDTO> buscarItensDoDrop(String idMonstro) throws SQLException {
        ArrayList<ItemDropDTO> lista = new ArrayList<>();
        String sql = "SELECT id_item, quantidade FROM monstro_drop WHERE id_monstro = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idMonstro);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ItemDropDTO(rs.getString("id_item"), rs.getInt("quantidade")));
                }
            }
        }
        return lista;
    }
}
