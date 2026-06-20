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
public class Missao {
    private String idMissao;
    private String nome;
    private String descricao;
    private Jogador mestre;
    private ArrayList<Personagem> personagens;
    private ArrayList<Monstro> inimigos;
    private ArrayList<Item> recompensas;
    private int xpBonus;

    public Missao(String idMissao, String nome, String descricao, Jogador mestre, ArrayList<Personagem> personagens, ArrayList<Monstro> inimigos, ArrayList<Item> recompensas, int xpBonus) {
        this.idMissao = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.mestre = mestre;
        this.personagens = new ArrayList<>();
        this.inimigos = new ArrayList<>();
        this.recompensas = new ArrayList<>();
        this.xpBonus = xpBonus;
    }

    public String getIdMissao() {
        return idMissao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Jogador getMestre() {
        return mestre;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public ArrayList<Monstro> getInimigos() {
        return inimigos;
    }

    public ArrayList<Item> getRecompensas() {
        return recompensas;
    }

    public int getXpBonus() {
        return xpBonus;
    }

    public void setIdMissao(String idMissao) {
        this.idMissao = idMissao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMestre(Jogador mestre) {
        this.mestre = mestre;
    }

    public void setPersonagens(ArrayList<Personagem> personagens) {
        this.personagens = personagens;
    }

    public void setInimigos(ArrayList<Monstro> inimigos) {
        this.inimigos = inimigos;
    }

    public void setRecompensas(ArrayList<Item> recompensas) {
        this.recompensas = recompensas;
    }

    public void setXpBonus(int xpBonus) {
        this.xpBonus = xpBonus;
    }
    
    
}
