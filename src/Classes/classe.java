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
public class Classe {
    private String idClasse;
    private String nome;
    private String descricao;

    public Classe(String nome, String descricao) {
        this.idClasse = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getIdClasse() {
        return idClasse;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setIdClasse(String idClasse) {
        this.idClasse = idClasse;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
