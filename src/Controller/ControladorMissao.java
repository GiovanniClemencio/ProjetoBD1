/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Missao;
import DAO.MissaoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorMissao implements Controlador {

    private final MissaoDAO missaoDAO;

    public ControladorMissao() {
        this.missaoDAO = new MissaoDAO();
    }

    // Criar Missão
    public Missao cadastrarMissao(String nome, String descricao, int xpBonus)
            throws SQLException, IllegalArgumentException {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da missão não pode estar vazio!");
        }
        if (xpBonus < 0) {
            throw new IllegalArgumentException("O XP bônus não pode ser negativo!");
        }

        Missao novaMissao = new Missao(nome, descricao, xpBonus);

        missaoDAO.inserir(novaMissao);
        
        return novaMissao;
    }

    // Excluir Missão
    public void excluirMissao(String idMissao) throws SQLException, IllegalArgumentException {
        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão da missão.");
        }

        missaoDAO.excluir(idMissao);
    }

    // Atualizar Missão
    public void atualizarMissao(String idMissao, String novoNome, String novaDescricao, int novoXpBonus) throws SQLException, IllegalArgumentException {

        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização da missão.");
        }
        if (novoNome == null || novoNome.isBlank()) {
            throw new IllegalArgumentException("O nome da missão não pode estar vazio!");
        }
        if (novoXpBonus < 0) {
            throw new IllegalArgumentException("O XP bônus não pode ser negativo!");
        }

        Missao missaoAtualizada = new Missao(novoNome, novaDescricao, novoXpBonus);
        missaoAtualizada.setIdMissao(idMissao);

        missaoDAO.atualizar(missaoAtualizada);
    }

    // Adicionar inimigo a missão
    public void adicionarInimigo(String idMissao, String idMonstro, int qtd) throws SQLException, IllegalArgumentException {
        if (idMissao == null || idMissao.isBlank() || idMonstro == null || idMonstro.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para adicionar inimigo.");
        }
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade de monstros deve ser maior que zero!");
        }
        missaoDAO.adicionarMonstro(idMissao, idMonstro, qtd);
    }

    // Remover inimigo da missão
    public void removerInimigo(String idMissao, String idMonstro) throws SQLException, IllegalArgumentException {
        if (idMissao == null || idMissao.isBlank() || idMonstro == null || idMonstro.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover inimigo.");
        }

        missaoDAO.removerMonstro(idMissao, idMonstro);
    }


    // Adicionar recompensa a missão
    public void adicionarRecompensa(String idMissao, String idItem, int qtd) throws SQLException, IllegalArgumentException {
        if (idMissao == null || idMissao.isBlank() || idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para adicionar recompensa.");
        }
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade de itens deve ser maior que zero!");
        }
        missaoDAO.adicionarRecompensa(idMissao, idItem, qtd);
    }

    // Remover item da missão
    public void removerRecompensa(String idMissao, String idItem) throws SQLException, IllegalArgumentException {
        if (idMissao == null || idMissao.isBlank() || idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover recompensa.");
        }

        missaoDAO.removerItemDaMissao(idMissao, idItem);
    }

    // Retornar XP da missão
    public int buscarXpBonusDaMissao(String idMissao) throws SQLException, IllegalArgumentException {

        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID da missão inválido para busca de XP.");
        }

        return missaoDAO.buscarXpBonusDaMissao(idMissao);
    }

    // Listar todas as missões cadastradas
    public ArrayList<Missao> listarTodasAsMissoes() throws SQLException {
        return missaoDAO.listarTodos();
    }
}
