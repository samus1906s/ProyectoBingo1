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
    private final Espacios[][] espacios;

    public Cartones(String id, Espacios[][] espacios) {
        this.id = id;
        if (espacios.length != 5 || espacios[0].length != 5) {
            throw new IllegalArgumentException("El tamaño del cartón debe ser 5x5.");
        }
        this.espacios = espacios;
    }

    public String getId() {
        return id;
    }

    public Espacios[][] getEspacios() {
        return espacios;
    }
    
    public void marcarNumero(int numero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Espacios e = espacios[i][j];
                if (!e.isLibre() && e.getValor() == numero) {
                    e.marcar();
                }
            }
        }
    }

    public void limpiarMarcados() {
        for (int i=0; i <5; i++) {
            for (int a=0; a<5; a++) {
                espacios[i][a].desmarcar();
            }
        }
    }
}
