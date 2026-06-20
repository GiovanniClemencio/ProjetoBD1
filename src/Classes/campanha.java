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
public class campanha {
    private String idCampanha;
    private String nome;
    private ArrayList<missao> missoes;
    private ArrayList<jogador> mestres;
    private ArrayList<personagem> personagens;

    public campanha(String idCampanha, String nome) {
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

    public ArrayList<missao> getMissoes() {
        return missoes;
    }

    public ArrayList<jogador> getMestres() {
        return mestres;
    }

    public ArrayList<personagem> getPersonagens() {
        return personagens;
    }

    public void setIdCampanha(String idCampanha) {
        this.idCampanha = idCampanha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMissoes(ArrayList<missao> missoes) {
        this.missoes = missoes;
    }

    public void setMestres(ArrayList<jogador> participantes) {
        this.mestres = participantes;
    }

    public void setPersonagens(ArrayList<personagem> personagens) {
        this.personagens = personagens;
    }
    
    
}
