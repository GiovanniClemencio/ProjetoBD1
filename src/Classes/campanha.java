/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Portu
 */
public class Campanha {
    private String idCampanha;
    private String nome;
    private ArrayList<Missao> missoes;
    private ArrayList<Jogador> mestres;
    private ArrayList<Personagem> personagens;

    public Campanha(String nome) {
        this.idCampanha = UUID.randomUUID().toString();
        this.nome = nome;
        this.missoes = new ArrayList<>();
        this.mestres = new ArrayList<>();
        this.personagens = new ArrayList<>();
    }

    public String getIdCampanha() {
        return idCampanha;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Missao> getMissoes() {
        return missoes;
    }

    public ArrayList<Jogador> getMestres() {
        return mestres;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public void setIdCampanha(String idCampanha) {
        this.idCampanha = idCampanha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMissoes(ArrayList<Missao> missoes) {
        this.missoes = missoes;
    }

    public void setMestres(ArrayList<Jogador> participantes) {
        this.mestres = participantes;
    }

    public void setPersonagens(ArrayList<Personagem> personagens) {
        this.personagens = personagens;
    }
    
    
}
