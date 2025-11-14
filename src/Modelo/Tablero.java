/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Reynold
 */
public class Tablero {
    private final boolean[] marcados;

    public Tablero() {
        marcados = new boolean[76];
    }

    public void marcar(int numero) {
        if (numero>=1 && numero<=75) {
            marcados[numero] = true;
        }
    }

    public boolean estaMarcado(int numero) {
        if (numero<1 || numero>75) return false;
        return marcados[numero];
    }

    public void reiniciar() {
        for (int i = 1; i <=75; i++) {
            marcados[i] = false;
        }
    }
}
