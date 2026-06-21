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
public class Monstro {
    private String idMonstro;
    private String nome;
    private String descricao;
    private String tipo;
    private int vida;
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;
    private int cr;
    // private ArrayList<Item> drops;

    public Monstro(String nome, String descricao, String tipo, int vida, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int cr) {
        this.idMonstro = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.vida = vida;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
        this.cr = cr;
    }

    public String getIdMonstro() {
        return idMonstro;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getForca() {
        return forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public int getCarisma() {
        return carisma;
    }

    public int getCr() {
        return cr;
    }

    public void setIdMonstro(String idMonstro) {
        this.idMonstro = idMonstro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    @Override
    public String toString() {
        return "Monstro{" + "nome=" + nome + ", tipo=" + tipo + '}';
    }
    
    public String toStringResumo() {
        return "Monstro{" + "idMonstro=" + idMonstro + 
                "\n nome=" + nome + 
                "\n descricao=" + descricao + 
                "\n tipo=" + tipo + 
                "\n vida=" + vida + 
                "\n forca=" + forca + 
                "\n destreza=" + destreza + 
                "\n constituicao=" + constituicao + 
                "\n inteligencia=" + inteligencia + 
                "\n sabedoria=" + sabedoria + 
                "\n carisma=" + carisma + 
                "\n cr=" + cr + '}' + "\n\n";
    }
}
