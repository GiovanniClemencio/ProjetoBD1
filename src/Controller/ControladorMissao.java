/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.ItemDropDTO;
import Classes.Missao;
import Classes.Personagem;
import DAO.MissaoDAO;
import DAO.PersonagemDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorMissao implements Controlador {

    private final MissaoDAO missaoDAO;
    private final PersonagemDAO personagemDAO;

    public ControladorMissao() {
        this.missaoDAO = new MissaoDAO();
        this.personagemDAO = new PersonagemDAO();
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
    public void adicionarParticipante(String idCampanha, String idMissao, String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || idMissao == null || idMissao.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para adicionar participante.");
        }
        missaoDAO.adicionarPersonagem(idCampanha, idMissao, idPersonagem);
    }

    // Retornar XP da missão
    public int buscarXpBonusDaMissao(String idMissao) throws SQLException, IllegalArgumentException {

        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID da missão inválido para busca de XP.");
        }

        return missaoDAO.buscarXpBonusDaMissao(idMissao);
    }

    // Listar personagens participantes da missão em uma campanha
    public ArrayList<Personagem> listarParticipantesDaMissao(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {

        if (idCampanha == null || idCampanha.isBlank() || idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para listagem");
        }

        return missaoDAO.listarParticipantesDaMissao(idCampanha, idMissao);
    }

    // Finalizar uma missão, distribuindo o XP e os itens para todos os personagens participantes
    public void concluirMissao(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {
        
        if (idCampanha == null || idCampanha.isBlank() ||idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID inválido para conclusão.");
        }

        int xpGanho = missaoDAO.buscarXpBonusDaMissao(idMissao);

        ArrayList<Personagem> participantes = missaoDAO.listarParticipantesDaMissao(idCampanha, idMissao);

        if (participantes.isEmpty()) {
            throw new IllegalArgumentException("Não é possível concluir uma missão sem personagens participantes!");
        }

        ArrayList<ItemDropDTO> recompensas = missaoDAO.listarRecompensasDaMissao(idMissao);


        for (Personagem personagem : participantes) {
            String idPersonagem = personagem.getIdPersonagem();
            
            personagemDAO.adicionarXP(idPersonagem, xpGanho);
            
            for (ItemDropDTO item : recompensas) {
                personagemDAO.adquirirItem(idPersonagem, item.getIdItem(), item.getQuantidade());
            }
        }

        missaoDAO.atualizarStatusMissao(idCampanha, idMissao, true);
    }
}
