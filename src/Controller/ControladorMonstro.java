/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Monstro;
import DAO.MonstroDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorMonstro implements Controlador {

    private final MonstroDAO monstroDAO;

    public ControladorMonstro() {
        this.monstroDAO = new MonstroDAO();
    }

    // Cadastrar monstro
    public void cadastrarMonstro(String nome, String descricao, String tipo, int vida, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int cr) throws SQLException, IllegalArgumentException {

        // Validações
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do monstro não pode estar vazio!");
        }
        if (vida <= 0) {
            throw new IllegalArgumentException("A vida do monstro deve ser maior que zero!");
        }
        if (forca < 0 || destreza < 0 || constituicao < 0 || inteligencia < 0 || sabedoria < 0 || carisma < 0) {
            throw new IllegalArgumentException("Os atributos do monstro não podem ser negativos!");
        }
        if (cr < 0) {
            throw new IllegalArgumentException("O Nível de Desafio (CR) não pode ser negativo!");
        }

        Monstro novoMonstro = new Monstro(nome, descricao, tipo, vida, forca, destreza, constituicao, inteligencia, sabedoria, carisma, cr);

        monstroDAO.inserir(novoMonstro);
    }

    // Excluir monstro
    public void excluirMonstro(String idMonstro) throws SQLException, IllegalArgumentException {
        if (idMonstro == null || idMonstro.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão de monstro.");
        }

        monstroDAO.excluir(idMonstro);
    }

    // Atualizar monstro
    public void atualizarMonstro(String idMonstro, String nome, String descricao, String tipo, int vida, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int cr) throws SQLException, IllegalArgumentException {
        
        // Validação
        if (idMonstro == null || idMonstro.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização do monstro.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do monstro não pode estar vazio!");
        }
        if (vida <= 0) {
            throw new IllegalArgumentException("A vida do monstro deve ser maior que zero!");
        }
        if (forca < 0 || destreza < 0 || constituicao < 0 || inteligencia < 0 || sabedoria < 0 || carisma < 0) {
            throw new IllegalArgumentException("Os atributos do monstro não podem ser negativos!");
        }
        if (cr < 0) {
            throw new IllegalArgumentException("O Nível de Desafio (CR) não pode ser negativo!");
        }

        Monstro monstroAtualizado = new Monstro(nome, descricao, tipo, vida, forca, destreza, constituicao, inteligencia, sabedoria, carisma, cr);
        monstroAtualizado.setIdMonstro(idMonstro);

        monstroDAO.atualizar(monstroAtualizado);
    }

    // Busca por ID
    public Monstro buscarMonstro(String id) throws SQLException, IllegalArgumentException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido para busca de monstro.");
        }
        return monstroDAO.buscarPorId(id);
    }

    // Listar monstros
    public ArrayList<Monstro> listarTodosOsMonstros() throws SQLException {
        return monstroDAO.listarTodos();
    }

    // Adicionar Drop ao monstro
    public void adicionarDropAoMonstro(String idMonstro, String idItem, int quantidade)
            throws SQLException, IllegalArgumentException {

        if (idMonstro == null || idMonstro.isBlank()) {
            throw new IllegalArgumentException("ID do monstro inválido.");
        }
        if (idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("ID do item inválido.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do drop deve ser maior que zero!");
        }

        monstroDAO.adicionarDrop(idMonstro, idItem, quantidade);
    }

    // Remover Drop do monstro
    public void removerDropDoMonstro(String idMonstro, String idItem)
            throws SQLException, IllegalArgumentException {

        if (idMonstro == null || idMonstro.isBlank()) {
            throw new IllegalArgumentException("ID do monstro inválido.");
        }
        if (idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("ID do item inválido.");
        }

        monstroDAO.removerDrop(idMonstro, idItem);
    }
}
