/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Reynold
 */
public class Cartones {
    private final String id;
    private final Casillas[][] casillas;

    public Cartones(String id, Casillas[][] espacios) {
        this.id = id;
        if (espacios.length != 5 || espacios[0].length != 5) {
            throw new IllegalArgumentException("El tamaño del cartón debe ser 5x5.");
        }
        this.casillas = espacios;
    }

    public String getId() {
        return id;
    }

    public Casillas[][] getEspacios() {
        return casillas;
    }
    
    public void marcarNumero(int numero) {
        for (int i = 0; i < 5; i++) {
            for (int a = 0; a < 5; a++) {
                Casillas e = casillas[i][a];
                if (!e.isDisponible()&& e.getValores()== numero) {
                    e.marcar();
                }
            }
        }
    }

    public void limpiarMarcados() {
        for (int i=0; i <5; i++) {
            for (int a=0; a<5; a++) {
                casillas[i][a].desmarcar();
            }
        }
    }
}
