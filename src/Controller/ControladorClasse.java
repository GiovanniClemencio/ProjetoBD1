/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Classe;
import DAO.ClasseDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorClasse implements Controlador  {

    private final ClasseDAO classeDAO;

    public ControladorClasse() {
        this.classeDAO = new ClasseDAO();
    }

    // Cadastrar classe no catálogo
    public void cadastrarClasse(String nome, String descricao) throws SQLException, IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da classe não pode estar vazio!");
        }

        Classe novaClasse = new Classe(nome, descricao);
        classeDAO.inserir(novaClasse);
    }

    // Excluir classe do catálogo
    public void excluirClasse(String idClasse) throws SQLException, IllegalArgumentException {
        if (idClasse == null || idClasse.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        classeDAO.excluir(idClasse);
    }

    // Atualizar dados da classe no catálogo
    public void atualizarClasse(String idClasse, String novoNome, String novaDescricao) throws SQLException, IllegalArgumentException {
        if (idClasse == null || idClasse.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        if (novoNome == null || novoNome.isBlank()) {
            throw new IllegalArgumentException("O novo nome da classe não pode estar vazio!");
        }

        Classe classeAtualizada = new Classe(novoNome, novaDescricao);
        classeAtualizada.setIdClasse(idClasse);

        classeDAO.atualizar(classeAtualizada);
    }

    // Listar todas as classes cadastradas no sistema
    public ArrayList<Classe> listarTodasAsClasses() throws SQLException {
        return classeDAO.listarTodos();
    }
}
