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
public class missao {
    private String idMissao;
    private String nome;
    private String descricao;
    private jogador mestre;
    private ArrayList<personagem> personagens;
    private ArrayList<monstro> inimigos;
    private ArrayList<item> recompensas;
    private int xpBonus;

    public missao(String idMissao, String nome, String descricao, jogador mestre, ArrayList<personagem> personagens, ArrayList<monstro> inimigos, ArrayList<item> recompensas, int xpBonus) {
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

    public jogador getMestre() {
        return mestre;
    }

    public ArrayList<personagem> getPersonagens() {
        return personagens;
    }

    public ArrayList<monstro> getInimigos() {
        return inimigos;
    }

    public ArrayList<item> getRecompensas() {
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

    public void setMestre(jogador mestre) {
        this.mestre = mestre;
    }

    public void setPersonagens(ArrayList<personagem> personagens) {
        this.personagens = personagens;
    }

    public void setInimigos(ArrayList<monstro> inimigos) {
        this.inimigos = inimigos;
    }

    public void setRecompensas(ArrayList<item> recompensas) {
        this.recompensas = recompensas;
    }

    public void setXpBonus(int xpBonus) {
        this.xpBonus = xpBonus;
    }
    
    
}
