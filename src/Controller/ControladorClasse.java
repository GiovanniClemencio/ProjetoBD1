/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Classe;
import DAO.ClasseDAO;
import java.sql.SQLException;

/**
 *
 * @author Luan
 */
public class ControladorClasse implements Controlador  {

    private final ClasseDAO classeDAO;

    public ControladorClasse() {
        this.classeDAO = new ClasseDAO();
    }

    // Cadastrar classe
    public void cadastrarClasse(String nome, String descricao) throws SQLException, IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da classe não pode estar vazio!");
        }

        Classe novaClasse = new Classe(nome, descricao);

        classeDAO.inserir(novaClasse);
    }

    // Vincular classe a um personagem
    public void adicionarClasseAoPersonagem(String idPersonagem, String idClasse, int nivelInicial) throws SQLException, IllegalArgumentException {
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido.");
        }
        if (idClasse == null || idClasse.isBlank()) {
            throw new IllegalArgumentException("ID da classe inválido.");
        }
        if (nivelInicial <= 0) {
            throw new IllegalArgumentException("O nível inicial da classe deve ser pelo menos 1!");
        }

        classeDAO.adicionarClasseAoPersonagem(idPersonagem, idClasse, nivelInicial);
    }

    // Personagem upa o nível
    public void uparClassePersonagem(String idPersonagem, String idClasse) throws SQLException, IllegalArgumentException {

        // Validação IDs
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido para subir de nível.");
        }
        if (idClasse == null || idClasse.isBlank()) {
            throw new IllegalArgumentException("ID da classe inválido para subir de nível.");
        }

        classeDAO.uparNivelClasse(idPersonagem, idClasse);
    }

    // Reduzir o nível de uma classe (Caso o usuário de um clique acidental de aumento de nivel)
    public void diminuirNivelClasse(String idPersonagem, String idClasse) throws SQLException, IllegalArgumentException {
        if (idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("ID do personagem inválido.");
        }
        if (idClasse == null || idClasse.isBlank()) {
            throw new IllegalArgumentException("ID da classe inválido.");
        }

        // Você pode usar um método de busca (tipo o que fizemos para o inventário) 
        // para checar o nível atual se quiser validar antes, ou deixar o WHERE do banco barrar.
        classeDAO.reduzirNivelClasse(idPersonagem, idClasse);
    }
}
