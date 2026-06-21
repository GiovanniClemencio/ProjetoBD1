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
    private String idMestre;

    public Campanha(String nome, String idMestre) {
        this.idCampanha = UUID.randomUUID().toString();
        this.nome = nome;
        this.idMestre = idMestre;
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

    public String getIdMestre() {
        return idMestre;
    }

    public void setIdMestre(String idMestre) {
        this.idMestre = idMestre;
    }

    @Override
    public String toString() {
        return "Campanha{" + "nome=" + nome + '}';
    }

    public String toStringResumo() {
        return "Campanha{" + "idCampanha=" + idCampanha + ", nome=" + nome + ", idMestre=" + idMestre + '}';
    }
}
