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
public class Campanha {
    private String idCampanha;
    private String nome;

    public Campanha(String nome) {
        this.idCampanha = UUID.randomUUID().toString();
        this.nome = nome;
    }

    public String getIdCampanha() {
        return idCampanha;
    }

    public String getNome() {
        return nome;
    }

    public void setIdCampanha(String idCampanha) {
        this.idCampanha = idCampanha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
