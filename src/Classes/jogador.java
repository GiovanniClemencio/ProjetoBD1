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
public class Jogador {
    private String idJogador;
    private String nome;
    private ArrayList<Personagem> personagens;

    public Jogador(String nome) {
        this.idJogador = UUID.randomUUID().toString();
        this.nome = nome;
        this.personagens = new ArrayList<>();
    }

    public String getIdJogador() {
        return idJogador;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPersonagens(ArrayList<Personagem> personagens) {
        this.personagens = personagens;
    }
    
    
}
