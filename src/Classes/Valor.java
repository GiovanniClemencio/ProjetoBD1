/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Portu
 */
public class Valor {
    private int platinum;
    private int gold;
    private int electrum;
    private int silver;
    private int copper;

    public Valor(int platinum, int gold, int electrum, int silver, int copper) {
        this.platinum = platinum;
        this.gold = gold;
        this.electrum = electrum;
        this.silver = silver;
        this.copper = copper;
    }

    public int getPlatinum() {
        return platinum;
    }

    public int getGold() {
        return gold;
    }

    public int getElectrum() {
        return electrum;
    }

    public int getSilver() {
        return silver;
    }

    public int getCopper() {
        return copper;
    }

    public void setPlatinum(int platinum) {
        this.platinum = platinum;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setElectrum(int electrum) {
        this.electrum = electrum;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public void setCopper(int copper) {
        this.copper = copper;
    }

    @Override
    public String toString() {
        return "valor{" + "platinum=" + platinum + 
                ", gold=" + gold + 
                ", electrum=" + electrum + 
                ", silver=" + silver + 
                ", copper=" + copper + '}';
    }   
}
