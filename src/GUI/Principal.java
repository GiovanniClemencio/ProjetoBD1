/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import Controller.ControladorCampanha;
import Controller.ControladorClasse;
import Controller.ControladorItem;
import Controller.ControladorJogador;
import Controller.ControladorMissao;
import Controller.ControladorMonstro;
import Controller.ControladorPersonagem;
import DAO.CampanhaDAO;
import DAO.ClasseDAO;
import DAO.ItemDAO;
import DAO.JogadorDAO;
import DAO.MissaoDAO;
import DAO.MonstroDAO;
import DAO.PersonagemDAO;

/**
 *
 * @author Portu
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GerenciadorControladores gerenciador = new GerenciadorControladores();
    }
    
}
