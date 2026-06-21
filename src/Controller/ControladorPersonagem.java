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
        if (forca < 1) {
            throw new IllegalArgumentException("A força do personagem deve ser maior ou igual a 1!");
        }
        if (destreza < 1) {
            throw new IllegalArgumentException("A destreza do personagem deve ser maior ou igual a 1!");
        }
        if (constituicao < 1) {
            throw new IllegalArgumentException("A constituição do personagem deve ser maior ou igual a 1!");
        }
        if (inteligencia < 1) {
            throw new IllegalArgumentException("A inteligência do personagem deve ser maior ou igual a 1!");
        }
        if (sabedoria < 1) {
            throw new IllegalArgumentException("A sabedoria do personagem deve ser maior ou igual a 1!");
        }
        if (carisma < 1) {
            throw new IllegalArgumentException("O carisma do personagem deve ser maior ou igual a 1!");
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

    public void atualizarPersonagem(Personagem personagem) throws SQLException, IllegalArgumentException {
        if (personagem == null || personagem.getIdPersonagem() == null) {
            throw new IllegalArgumentException("Personagem inválido para atualização.");
        }
        personagemDAO.atualizar(personagem);
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

    // Listar campanhas que o personagem participa
    public ArrayList<Campanha> obterCampanhasParticipando(String idPersonagem) throws SQLException, IllegalArgumentException {
        // Validação
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido para listar campanhas.");
        }

        return personagemDAO.listarCampanhasParticipando(idPersonagem);
    }
}
