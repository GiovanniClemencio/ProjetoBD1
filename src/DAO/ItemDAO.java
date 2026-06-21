/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Item;
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
public class ItemDAO {

    // Inserir item
    public void inserir(Item item) throws SQLException {
        String sql = "INSERT INTO item (id_item, nome, raridade, custo, peso, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getIdItem());
            stmt.setString(2, item.getNome());
            stmt.setString(3, item.getRaridade());
            stmt.setDouble(4, item.getCusto());
            stmt.setDouble(5, item.getPeso());
            stmt.setString(6, item.getDescricao());

            stmt.executeUpdate();
        }
    }
    
    // Excluir item
    public void excluir(String idItem) throws SQLException {
        String sql = "DELETE FROM item WHERE id_item = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idItem);
            stmt.executeUpdate();
        }
    }

    // Atualizar item
    public void atualizar(Item item) throws SQLException {
        String sql = "UPDATE item SET nome = ?, raridade = ?, custo = ?, peso = ?, descricao = ? " +
                     "WHERE id_item = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getRaridade());
            stmt.setDouble(3, item.getCusto());
            stmt.setDouble(4, item.getPeso());
            stmt.setString(5, item.getDescricao());
            stmt.setString(6, item.getIdItem()); // Filtro do WHERE

            stmt.executeUpdate();
        }
    }

    // Buscar item
    public Item buscarPorId(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE id_item = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item(
                        rs.getString("nome"),
                        rs.getString("raridade"),
                        rs.getDouble("custo"),
                        rs.getDouble("peso"),
                        rs.getString("descricao")
                    );
                    item.setIdItem(rs.getString("id_item"));
                    return item;
                }
            }
        }
        return null;
    }

    // Listar todos os itens
    public ArrayList<Item> listarTodos() throws SQLException {
        ArrayList<Item> itens = new ArrayList<>();
        String sql = "SELECT * FROM item ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item item = new Item(
                    rs.getString("nome"),
                    rs.getString("raridade"),
                    rs.getDouble("custo"),
                    rs.getDouble("peso"),
                    rs.getString("descricao")
                );
                item.setIdItem(rs.getString("id_item"));
                itens.add(item);
            }
        }
        return itens;
    }
}
