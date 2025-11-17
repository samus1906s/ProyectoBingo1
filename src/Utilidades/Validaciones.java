/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

/**
 *
 * @author samue
 */
public class Validaciones {
    private Validaciones() {}

    public static boolean numeroColumna(int numero, int columna) {
        return switch (columna) {
            case 0 -> numero >= 1 && numero <= 15;
            case 1 -> numero >= 16 && numero <= 30;
            case 2 -> numero >= 31 && numero <= 45;
            case 3 -> numero >= 46 && numero <= 60;
            case 4 -> numero >= 61 && numero <= 75;
            default -> false;
        };
    }
}
