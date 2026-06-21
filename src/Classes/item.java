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
public class Item {
    private String idItem;
    private String nome;
    private String raridade;
    private double custo;
    private double peso;

    public Item(String nome, String raridade, double custo, double peso) {
        this.idItem = UUID.randomUUID().toString();
        this.nome = nome;
        this.raridade = raridade;
        this.custo = custo;
        this.peso = peso;
    }

    public String getIdItem() {
        return idItem;
    }

    public String getNome() {
        return nome;
    }

    public String getRaridade() {
        return raridade;
    }

    public double getCusto() {
        return custo;
    }

    public double getPeso() {
        return peso;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    
}
