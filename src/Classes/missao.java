/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

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
    // private ArrayList<Personagem> personagens;
    // private ArrayList<Monstro> inimigos;
    // private ArrayList<Item> recompensas;
    private int xpBonus;

    public Missao(String nome, String descricao, Jogador mestre, int xpBonus) {
        this.idMissao = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.mestre = mestre;
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

    public void setXpBonus(int xpBonus) {
        this.xpBonus = xpBonus;
    }
    
    
}
