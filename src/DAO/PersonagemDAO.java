/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Campanha;
import Classes.ItemDTO;
import Classes.Personagem;
import Classes.PersonagemClasseDTO;
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

    // Listar todos os personagens cadastrados
    public ArrayList<Personagem> listarTodos() throws SQLException {
        ArrayList<Personagem> personagens = new ArrayList<>();
        String sql = "SELECT * FROM personagem ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Personagem p = new Personagem(rs.getString("nome"), rs.getDouble("carga_maxima"), rs.getDouble("xp"),
                        rs.getInt("vida"), rs.getInt("forca"), rs.getInt("destreza"), rs.getInt("constituicao"), rs.getInt("inteligencia"),
                        rs.getInt("sabedoria"), rs.getInt("carisma"), rs.getString("id_jogador"));

                p.setIdPersonagem(rs.getString("id_personagem"));

                personagens.add(p);
            }
        }
        return personagens;
    }

    // Listar todos os itens que estão no inventário de um personagem específico
    public ArrayList<ItemDTO> listarItensDoInventario(String idPersonagem) throws SQLException {
        ArrayList<ItemDTO> inventario = new ArrayList<>();

        // Pega o item e a quantidade
        String sql = "SELECT i.id_item, i.nome, i.peso, pi.quantidade FROM item i "
                + "JOIN personagem_item pi ON i.id_item = pi.id_item "
                + "WHERE pi.id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemDTO itemFormatado = new ItemDTO(rs.getString("id_item"), rs.getInt("quantidade"));
                    inventario.add(itemFormatado);
                }
            }
        }
        return inventario;
    }

    // Listar todas as classes que um personagem tem
    public ArrayList<PersonagemClasseDTO> listarClassesDoPersonagem(String idPersonagem) throws SQLException {
        ArrayList<PersonagemClasseDTO> classesDoPersonagem = new ArrayList<>();

        String sql = "SELECT c.id_classe, c.nome, pc.nivel FROM classe c "
                + "JOIN classe_personagem pc ON c.id_classe = pc.id_classe "
                + "WHERE pc.id_personagem = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PersonagemClasseDTO classeFormatada = new PersonagemClasseDTO();
                    classeFormatada.setIdClasse(rs.getString("id_classe"));
                    classeFormatada.setNome(rs.getString("nome"));
                    classeFormatada.setNivel(rs.getInt("nivel"));

                    classesDoPersonagem.add(classeFormatada);
                }
            }
        }
        return classesDoPersonagem;
    }

    // Buscar o nível de uma classe especifica de um personagem
    public int buscarNivelClasse(String idPersonagem, String idClasse) throws SQLException {
        String sql = "SELECT nivel_classe FROM classe_personagem WHERE id_personagem = ? AND id_classe = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.setString(2, idClasse);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("nivel_classe");
                }
            }
        }
        return 0;
    }

    // Vincular classe a um personagem
    public void adicionarClasse(String idPersonagem, String idClasse, int nivelInicial) throws SQLException {
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

    // Reduzir o nível de uma classe
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

    // Remover a classe do personagem
    public void removerVinculoClasse(String idPersonagem, String idClasse) throws SQLException {
        String sql = "DELETE FROM personagem_classe WHERE id_personagem = ? AND id_classe = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPersonagem);
            stmt.setString(2, idClasse);

            stmt.executeUpdate();
        }
    }
}
