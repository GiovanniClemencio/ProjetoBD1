/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Luan
 */
public class ItemDropDTO {
    private String idItem;
    private int quantidade;

    public ItemDropDTO(String idItem, int quantidade) {
        this.idItem = idItem;
        this.quantidade = quantidade;
    }

    public String getIdItem() {
        return idItem;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
