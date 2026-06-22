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
    // private String idMestre; Redundante não? Quem está mestrando a campanha é quem vai mestrar a missão

    private int xpBonus;

    public Missao(String nome, String descricao, int xpBonus) {
        this.idMissao = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
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

    public void setXpBonus(int xpBonus) {
        this.xpBonus = xpBonus;
    }

    @Override
    public String toString() {
        return "Missao{" + "nome=" + nome + '}';
    }
    
    public String toStringResumo() {
        return "Missao{" + "idMissao=" + idMissao + 
                "\n nome=" + nome + 
                "\n descricao=" + descricao +
                "\n xpBonus=" + xpBonus + '}' + "\n\n";
    }
}
