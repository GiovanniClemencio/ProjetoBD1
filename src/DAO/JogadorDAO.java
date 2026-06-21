/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Jogador;
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
public class JogadorDAO {

    // Inserir jogador
    public void inserir(Jogador jogador) throws SQLException {
        String sql = "INSERT INTO jogador (id_jogador, nome) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getIdJogador());
            stmt.setString(2, jogador.getNome());

            stmt.executeUpdate();
        }
    }

    // Excluir jogador
    public void excluir(String idJogador) throws SQLException {
        String sql = "DELETE FROM jogador WHERE id_jogador = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idJogador);

            stmt.executeUpdate();
        }
    }

    // Atualizar jogador
    public void atualizar(Jogador jogador) throws SQLException {
        String sql = "UPDATE jogador SET nome = ? WHERE id_jogador = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getIdJogador()); // Filtro do WHERE

            stmt.executeUpdate();
        }
    }

    // Buscar jogador por ID
    public Jogador buscarPorId(String id) throws SQLException {
        String sql = "SELECT * FROM jogador WHERE id_jogador = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Jogador j = new Jogador("nome");
                    j.setIdJogador(rs.getString("id_jogador"));
                    return j;
                }
            }
        }
        return null;
    }

    // Listar todos os jogadores
    public ArrayList<Jogador> listarTodos() throws SQLException {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM jogador ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Jogador j = new Jogador("nome");
                j.setIdJogador(rs.getString("id_jogador"));
                
                jogadores.add(j);
            }
        }
        return jogadores;
    }
}
