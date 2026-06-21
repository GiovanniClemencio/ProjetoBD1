/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Portu
 */
public class GerenciadorControladores {
    private final Map<Class<? extends Controlador>, Controlador> controladores = new HashMap<>();

    public <T extends Controlador> void registrar(T controlador) {
        controladores.put(controlador.getClass(), controlador);
    }

    public <T extends Controlador> T obter(Class<T> tipo) {
        Controlador controlador = controladores.get(tipo);
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador não encontrado: " + tipo.getSimpleName());
        }
        return tipo.cast(controlador);
    }
}
