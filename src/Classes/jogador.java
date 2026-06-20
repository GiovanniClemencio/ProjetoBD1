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
public class jogador {
    private String idJogador;
    private String nome;
    private ArrayList<personagem> personagens;

    public jogador(String nome) {
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

    public ArrayList<personagem> getPersonagens() {
        return personagens;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPersonagens(ArrayList<personagem> personagens) {
        this.personagens = personagens;
    }
    
    
}
