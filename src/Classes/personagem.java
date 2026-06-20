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
public class personagem {
    private String idPersonagem;
    private String nome;
    private double cargaMaxima;
    private double xp;
    private int vida;
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;
    private String idJogador;
    private ArrayList<classe> classes;
    private ArrayList<item> inventario;

    public personagem(String nome, double cargaMaxima, double xp, int vida, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, String idJogador, ArrayList<classe> classes, ArrayList<item> inventario){
        this.idPersonagem = UUID.randomUUID().toString();
        this.nome = nome;
        this.cargaMaxima = cargaMaxima;
        this.xp = xp;
        this.vida = vida;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
        this.idJogador = idJogador;
        this.classes = new ArrayList<>();
        this.inventario = new ArrayList<>();
    }

    public String getIdPersonagem() {
        return idPersonagem;
    }

    public String getNome() {
        return nome;
    }

    public double getCargaMaxima() {
        return cargaMaxima;
    }

    public double getXp() {
        return xp;
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

    public String getIdJogador() {
        return idJogador;
    }

    public ArrayList<classe> getClasses() {
        return classes;
    }

    public ArrayList<item> getInventario() {
        return inventario;
    }

    public void setIdPersonagem(String idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargaMaxima(double cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public void setXp(double xp) {
        this.xp = xp;
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

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public void setClasses(ArrayList<classe> classes) {
        this.classes = classes;
    }

    public void setInventario(ArrayList<item> inventario) {
        this.inventario = inventario;
    }
    
    
}
