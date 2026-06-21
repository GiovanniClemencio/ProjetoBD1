/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Item;
import DAO.ItemDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorItem implements Controlador  {

    private ItemDAO itemDAO;

    public ControladorItem(ItemDAO itemDAO) {
        this.itemDAO = new ItemDAO();
    }

    public void cadastrarItem(String nome, String raridade, double custo, double peso, String descricao) throws SQLException, IllegalArgumentException {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do item não pode estar vazio!");
        }
        if (custo < 0) {
            throw new IllegalArgumentException("O custo do item não pode ser negativo!");
        }
        if (peso < 0) {
            throw new IllegalArgumentException("O peso do item não pode ser negativo!");
        }

        Item novoItem = new Item(nome, raridade, custo, peso, descricao);

        itemDAO.inserir(novoItem);
    }

    // Excluir item
    public void excluirItem(String idItem) throws SQLException, IllegalArgumentException {
        if (idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão de item.");
        }

        itemDAO.excluir(idItem);
    }

    // Atualizar item
    public void atualizarItem(String idItem, String nome, String raridade, double custo, double peso, String descricao) throws SQLException, IllegalArgumentException {
        
        // Validar
        if (idItem == null || idItem.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização do item.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do item não pode estar vazio!");
        }
        if (custo < 0) {
            throw new IllegalArgumentException("O custo do item não pode ser negativo!");
        }
        if (peso < 0) {
            throw new IllegalArgumentException("O peso do item não pode ser negativo!");
        }

        Item itemAtualizado = new Item(nome, raridade, custo, peso, descricao);
        itemAtualizado.setIdItem(idItem);

        itemDAO.atualizar(itemAtualizado);
    }

    // Buscar ID
    public Item buscarItem(String id) throws SQLException, IllegalArgumentException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido para busca de item.");
        }
        return itemDAO.buscarPorId(id);
    }

    // Listar todos os itens
    public ArrayList<Item> listarTodosOsItens() throws SQLException {
        return itemDAO.listarTodos();
    }
}
