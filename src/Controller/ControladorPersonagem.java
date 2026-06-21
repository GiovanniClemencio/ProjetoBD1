/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Campanha;
import Classes.Personagem;
import DAO.PersonagemDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorPersonagem {

    private PersonagemDAO personagemDAO;

    public ControladorPersonagem() {
        this.personagemDAO = new PersonagemDAO();
    }

    public void cadastrarPersonagem(String nome, double cargaMaxima, double xp, int vida, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, String idJogador) throws SQLException, IllegalArgumentException {

        // VALIDAÇÕES
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do personagem não pode estar vazio!");
        }
        if (cargaMaxima <= 0) {
            throw new IllegalArgumentException("A carga máxima do personagem deve ser maior que 0!");
        }
        if (vida <= 0) {
            throw new IllegalArgumentException("A vida inicial do personagem deve ser maior que 0!");
        }
        if (forca < 0 || destreza < 0 || constituicao < 0 || inteligencia < 0 || sabedoria < 0 || carisma < 0) {
            throw new IllegalArgumentException("Os atributos do personagem não podem ser negativos!");
        }
        if (idJogador == null || idJogador.isBlank()) {
            throw new IllegalArgumentException("O personagem precisa estar vinculado a um Jogador!");
        }

        Personagem novoPersonagem = new Personagem(nome, cargaMaxima, xp, vida, forca, destreza, constituicao, inteligencia, sabedoria, carisma, idJogador);

        personagemDAO.salvar(novoPersonagem);
    }

    public Personagem obterPersonagemPorId(String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID inválido para busca.");
        }
        return personagemDAO.buscarPorId(idPersonagem);
    }

    public void atualizarPersonagem(String idPersonagem, String nome, double cargaMaxima, double xp, int vida, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, String idJogador) throws SQLException, IllegalArgumentException {

        // Validações
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização do personagem.");
        }
        if (idJogador == null || idJogador.isBlank()) {
            throw new IllegalArgumentException("O personagem deve estar vinculado a um jogador válido.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do personagem não pode estar vazio!");
        }
        if (vida <= 0) {
            throw new IllegalArgumentException("A vida do personagem deve ser maior que zero!");
        }
        if (cargaMaxima < 0 || xp < 0) {
            throw new IllegalArgumentException("A carga máxima e o XP não podem ser valores negativos!");
        }
        if (forca < 0 || destreza < 0 || constituicao < 0 || inteligencia < 0 || sabedoria < 0 || carisma < 0) {
            throw new IllegalArgumentException("Os atributos do personagem não podem ser negativos!");
        }

        Personagem personagemAtualizado = new Personagem(nome, cargaMaxima, xp, vida, forca, destreza, constituicao, inteligencia, sabedoria, carisma, idJogador);

        personagemAtualizado.setIdPersonagem(idPersonagem);
        personagemDAO.atualizar(personagemAtualizado);
    }

    public void excluirPersonagem(String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        personagemDAO.deletar(idPersonagem);
    }

    public void personagemGanhaXP(String idPersonagem, double xp) throws SQLException, IllegalArgumentException {
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido.");
        }
        if (xp <= 0) {
            throw new IllegalArgumentException("A quantidade de XP deve ser maior que zero.");
        }

        personagemDAO.adicionarXP(idPersonagem, xp);
    }

    // Personagem ganha um item
    public void personagemGanhaItem(String idPersonagem, String idItem, int qtd) throws SQLException, IllegalArgumentException {

        // Validação IDs
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido.");
        }
        if (idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("ID do item inválido.");
        }

        // Validação quantidade
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade de itens adquiridos deve ser maior que zero!");
        }

        personagemDAO.adquirirItem(idPersonagem, idItem, qtd);
    }

    // Personagem consome/perde um item
    public void personagemPerdeItem(String idPersonagem, String idItem, int qtd) throws SQLException, IllegalArgumentException {

        // Validação IDs e quantidade válida
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido.");
        }
        if (idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("ID do item inválido.");
        }
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade a ser removida deve ser maior que zero!");
        }

        // Validação se o personagem em questão tem o item e a quantidade para usar/perder
        int qtdAtual = personagemDAO.buscarQuantidadeItemInventario(idPersonagem, idItem);

        if (qtdAtual == 0) {
            throw new IllegalArgumentException("O personagem não possui este item no inventário!");
        }
        if (qtd > qtdAtual) {
            throw new IllegalArgumentException("Quantidade insuficiente! O personagem possui apenas " + qtdAtual + " unidade(s) deste item.");
        }

        personagemDAO.removerItem(idPersonagem, idItem, qtd);
    }

    // Listar campanhas que o personagem participa
    public ArrayList<Campanha> obterCampanhasParticipando(String idPersonagem) throws SQLException, IllegalArgumentException {
        // Validação
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido para listar campanhas.");
        }

        return personagemDAO.listarCampanhasParticipando(idPersonagem);
    }
}
