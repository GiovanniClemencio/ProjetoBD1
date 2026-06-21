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
public class ControladorCampanha {

    private CampanhaDAO dao;

    public ControladorCampanha(CampanhaDAO dao) {
        this.dao = new CampanhaDAO();
    }

    // Cadastrar
    public void cadastrarCampanha(String nome) throws SQLException, IllegalArgumentException {

        // Validar nome
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da campanha não pode estar vazio!");
        }

        Campanha campanha = new Campanha(nome);

        dao.inserir(campanha);
    }

    // Buscar por ID
    public Campanha buscarCampanha(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID inválido para busca de campanha.");
        }

        return dao.buscarPorId(idCampanha);
    }

    // Buscar personagens que participam da campanha
    public ArrayList<Personagem> obterPersonagensParticipantes(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID da campanha inválido para listagem de participantes.");
        }

        return dao.listarPersonagensParticipantes(idCampanha);
    }

    // Vinculando um personagem a uma campanha
    public void vincularPersonagem(String idCampanha, String idPersonagem) throws SQLException, IllegalArgumentException {
        // Validação
        if (idCampanha == null || idCampanha.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para realizar o vínculo.");
        }

        dao.adicionarPersonagem(idCampanha, idPersonagem);
    }

    // Desvinculando um personagem a uma campanha
    public void desvincularPersonagem(String idCampanha, String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover o personagem da campanha.");
        }

        dao.removerPersonagem(idCampanha, idPersonagem);
    }

    // Vinculando um mestre a uma campanha
    public void vincularMestre(String idCampanha, String idMestre) throws SQLException, IllegalArgumentException {
        // Validação
        if (idCampanha == null || idCampanha.isBlank() || idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para realizar o vínculo.");
        }

        dao.adicionarPersonagem(idCampanha, idMestre);
    }

    // Desvinculando um mestre a uma campanha
    public void desvincularMestre(String idCampanha, String idMestre) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover o personagem da campanha.");
        }

        dao.removerPersonagem(idCampanha, idMestre);
    }

}
