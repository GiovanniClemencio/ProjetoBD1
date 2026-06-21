/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Jogador;
import DAO.JogadorDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorJogador implements Controlador  {
    
    private final JogadorDAO jogadorDAO;

    public ControladorJogador() {
        this.jogadorDAO = new JogadorDAO();
    }
    
    // Cadastrar jogador
    public void cadastrarJogador(String nome) throws SQLException, IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do jogador não pode estar vazio!");
        }

        // Instancia o objeto (o construtor da sua classe deve gerar o UUID para o idJogador)
        Jogador novoJogador = new Jogador(nome);

        // Envia para o DAO salvar no MySQL
        jogadorDAO.inserir(novoJogador);
    }

    // Excluir jogador
    public void excluirJogador(String idJogador) throws SQLException, IllegalArgumentException {
        if (idJogador == null || idJogador.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }

        // Executa a remoção no banco
        jogadorDAO.excluir(idJogador);
    }

    // Atualizar jogador
    public void atualizarJogador(String idJogador, String novoNome) throws SQLException, IllegalArgumentException {
        if (idJogador == null || idJogador.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        if (novoNome == null || novoNome.isBlank()) {
            throw new IllegalArgumentException("O novo nome do jogador não pode estar vazio!");
        }

        Jogador jogadorAtualizado = new Jogador(novoNome);
        jogadorAtualizado.setIdJogador(idJogador);

        jogadorDAO.atualizar(jogadorAtualizado);
    }

    // Buscar jogador por ID
    public Jogador buscarJogador(String id) throws SQLException, IllegalArgumentException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido para busca.");
        }
        return jogadorDAO.buscarPorId(id);
    }

    // Listar todos os jogadores
    public ArrayList<Jogador> listarTodosOsJogadores() throws SQLException {
        return jogadorDAO.listarTodos();
    }
    
}
