/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Reynold
 */
public class Casillas {
    private int valores;
    private boolean marcados;
    private boolean disponible;

    public Casillas(int valores, boolean disponible) {
        this.valores = valores;
        this.marcados = disponible;
        this.disponible = disponible;
    }

    public int getValores() {
        return valores;
    }

    public boolean isMarcados() {
        return marcados;
    }

    public boolean isDisponible() {
        return disponible;
    }
    
    public void marcar() {
        this.marcados = true;
    }

    public void desmarcar() {
       if (disponible) {
            this.marcados = true;
        } else {
            this.marcados = false;
        }
    }
}
