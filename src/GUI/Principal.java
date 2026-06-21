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
import Controller.GerenciadorControladores;
import DAO.CampanhaDAO;
import DAO.ClasseDAO;
import DAO.ItemDAO;
import DAO.JogadorDAO;
import DAO.MissaoDAO;
import DAO.MonstroDAO;
import DAO.PersonagemDAO;
import GUI.telas.TelaInicial;

/**
 *
 * @author Portu
 */
public class Principal {

    
    public static void main(String[] args) {
        GerenciadorControladores gerenciador = new GerenciadorControladores();
        
        ControladorCampanha ctrlCampanha = new ControladorCampanha();
        ControladorClasse ctrlClasse = new ControladorClasse();
        ControladorItem ctrlItem = new ControladorItem();
        ControladorJogador ctrlJogador = new ControladorJogador();
        ControladorMissao ctrlMissao = new ControladorMissao();
        ControladorMonstro ctrlMonstro = new ControladorMonstro();
        ControladorPersonagem ctrlPersonagem = new ControladorPersonagem();
        
        gerenciador.registrar(ctrlCampanha);
        gerenciador.registrar(ctrlClasse);
        gerenciador.registrar(ctrlItem);
        gerenciador.registrar(ctrlJogador);
        gerenciador.registrar(ctrlMissao);
        gerenciador.registrar(ctrlMonstro);
        gerenciador.registrar(ctrlPersonagem);
        
        TelaInicial inicio = new TelaInicial(gerenciador);
        inicio.setVisible(true);
    }
    
}
