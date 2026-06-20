/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Campanha;
import DAO.CampanhaDAO;

/**
 *
 * @author Luan
 */
public class ControladorCampanha {

    private CampanhaDAO dao = new CampanhaDAO();

    public void cadastrarCampanha(String nome) {
        
        Campanha campanha = new Campanha(nome);

        dao.inserir(campanha);
    }
}
