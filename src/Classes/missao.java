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
    private String idMestre;
    // private ArrayList<Personagem> personagens;
    // private ArrayList<Monstro> inimigos;
    // private ArrayList<Item> recompensas;
    private int xpBonus;

    public Missao(String nome, String descricao, String idMestre, int xpBonus) {
        this.idMissao = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.idMestre = idMestre;
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

    public String getMestre() {
        return idMestre;
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

    public void setMestre(String idMestre) {
        this.idMestre = idMestre;
    }

    public void setXpBonus(int xpBonus) {
        this.xpBonus = xpBonus;
    }
    
    
}
