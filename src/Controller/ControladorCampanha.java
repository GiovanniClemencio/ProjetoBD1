/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Campanha;
import Classes.Personagem;
import DAO.CampanhaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorCampanha implements Controlador {

    private final CampanhaDAO campanhaDAO;

    public ControladorCampanha() {
        this.campanhaDAO = new CampanhaDAO();
    }

    // Cadastrar
    public void cadastrarCampanha(String nome, String idMestre) throws SQLException, IllegalArgumentException {

        // Validar nome
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da campanha não pode estar vazio!");
        }
        // Validar mestre
        if (idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("Campanha precisa de um mestre cadastrado!");
        }

        Campanha campanha = new Campanha(nome, idMestre);

        campanhaDAO.inserir(campanha);
    }

    // Excluir campanha
    public void excluirCampanha(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão da campanha.");
        }

        campanhaDAO.excluir(idCampanha);
    }

    // Atualizar campanha
    public void atualizarCampanha(String idCampanha, String nome, String idMestre) throws SQLException, IllegalArgumentException {

        // Validar
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização da campanha.");
        }
        if(nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da campanha não pode estar vazio!");
        }
        if (idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("A campanha deve ter um mestre!");
        }

        Campanha campanhaAtualizada = new Campanha(nome, idMestre);
        campanhaAtualizada.setIdCampanha(idCampanha);

        campanhaDAO.atualizar(campanhaAtualizada);
    }

    // Buscar por ID
    public Campanha buscarCampanha(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID inválido para busca de campanha.");
        }

        return campanhaDAO.buscarPorId(idCampanha);
    }

    // Buscar personagens que participam da campanha
    public ArrayList<Personagem> obterPersonagensParticipantes(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID da campanha inválido para listagem de participantes.");
        }

        return campanhaDAO.listarPersonagensParticipantes(idCampanha);
    }

    // Vinculando um personagem a uma campanha
    public void vincularPersonagem(String idCampanha, String idPersonagem) throws SQLException, IllegalArgumentException {
        // Validação
        if (idCampanha == null || idCampanha.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para realizar o vínculo.");
        }

        campanhaDAO.adicionarPersonagem(idCampanha, idPersonagem);
    }

    // Desvinculando um personagem a uma campanha
    public void desvincularPersonagem(String idCampanha, String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover o personagem da campanha.");
        }

        campanhaDAO.removerPersonagem(idCampanha, idPersonagem);
    }

    // Vinculando um mestre a uma campanha
    public void vincularMestre(String idCampanha, String idMestre) throws SQLException, IllegalArgumentException {
        // Validação
        if (idCampanha == null || idCampanha.isBlank() || idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para realizar o vínculo.");
        }

        campanhaDAO.adicionarPersonagem(idCampanha, idMestre);
    }

    // Desvinculando um mestre a uma campanha
    public void desvincularMestre(String idCampanha, String idMestre) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover o personagem da campanha.");
        }

        campanhaDAO.removerPersonagem(idCampanha, idMestre);
    }

    // Vincular missão a campanha
    public void adicionarMissaoAoCronograma(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {
        
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID da campanha inválido.");
        }
        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID da missão inválido.");
        }

        campanhaDAO.adicionarMissaoAcampanha(idCampanha, idMissao);
    }

    // Remover missão da campanha
    public void removerMissaoDoCronograma(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {
        
        if (idCampanha == null || idCampanha.isBlank() || idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para desvincular a missão.");
        }

        campanhaDAO.removerMissaoDaCampanha(idCampanha, idMissao);
    }
}
