/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Luan
 */
public class PersonagemClasseDTO {
    private String idClasse;
    private String nome;
    private int nivel;

    public PersonagemClasseDTO() {
    }

    public PersonagemClasseDTO(String idClasse, String nome, int nivel) {
        this.idClasse = idClasse;
        this.nome = nome;
        this.nivel = nivel;
    }

    public String getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(String idClasse) {
        this.idClasse = idClasse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
