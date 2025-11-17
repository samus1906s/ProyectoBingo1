/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Reynold
 */
public class Carton {
    private final String id;
    private final Casillas[][] casillas;

    public Carton(String id, Casillas[][] espacios) {
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
    
    public void marcarNumero(int numero){
        for (int i=0; i<5; i++) {
            for (int a=0; a<5; a++) {
                Casillas c = casillas[i][a];
                if (!c.isDisponible()&& c.getValores()== numero) {
                    c.marcar();
                }
            }
        }
    }

    public void limpiarMarcados(){
        for (int i=0; i <5; i++) {
            for (int a=0; a<5; a++) {
                casillas[i][a].desmarcar();
            }
        }
    }
    
    public void desmarcarNumero(int numero){
    for (int i=0; i<5; i++) {
        for (int a=0; a<5; a++) {
            Casillas c = casillas[i][a];
            if (!c.isDisponible() && c.getValores() == numero) {
                c.desmarcar();
            }
        }
    }
}
}
