/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Jogador;
import Classes.Missao;
import DAO.MissaoDAO;
import java.sql.SQLException;

/**
 *
 * @author Luan
 */
public class ControladorMissao implements Controlador  {

    private MissaoDAO missaoDAO;

    public ControladorMissao(MissaoDAO missaoDAO) {
        this.missaoDAO = new MissaoDAO();
    }

    // Criar Missão
    public void cadastrarMissao(String nome, String descricao, String idMestre, int xpBonus) 
            throws SQLException, IllegalArgumentException {
        
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da missão não pode estar vazio!");
        }
        if (xpBonus < 0) {
            throw new IllegalArgumentException("O XP bônus não pode ser negativo!");
        }
        if (idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("A missão precisa de um mestre válido!");
        }

        Missao novaMissao = new Missao(nome, descricao, idMestre, xpBonus);

        missaoDAO.inserir(novaMissao);
    }

    // Excluir Missão
    public void excluirMissao(String idMissao) throws SQLException, IllegalArgumentException {
        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão da missão.");
        }
        
        missaoDAO.excluir(idMissao);
    }

    // Atualizar Missão
    public void atualizarMissao(String idMissao, String novoNome, String novaDescricao, String idMestre, int novoXpBonus) throws SQLException, IllegalArgumentException {
        
        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização da missão.");
        }
        if (novoNome == null || novoNome.isBlank()) {
            throw new IllegalArgumentException("O nome da missão não pode estar vazio!");
        }
        if (novoXpBonus < 0) {
            throw new IllegalArgumentException("O XP bônus não pode ser negativo!");
        }
        if (idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("A missão precisa de um mestre válido!");
        }

        Missao missaoAtualizada = new Missao(novoNome, novaDescricao, idMestre, novoXpBonus);
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

    // Vincular participante
    public void adicionarParticipante(String idMissao, String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idMissao == null || idMissao.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para adicionar participante.");
        }
        missaoDAO.adicionarPersonagem(idMissao, idPersonagem);
    }
}
