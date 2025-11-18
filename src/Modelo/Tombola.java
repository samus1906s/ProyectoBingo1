/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Modelo.GenerarNums;
/**
 *
 * @author je110
 */
public class Tombola {
    
    private final GenerarNums generador;
    private final List<Integer> historial;

    public Tombola(GenerarNums generador) {
        this.generador = generador;
        this.historial = new ArrayList<>();
    }

    public int extraerNumero() {
        int numero = generador.generar();
        if (numero != -1) {
            historial.add(numero);
        }
        return numero;
    }

    public List<Integer> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public void reiniciar() {
        historial.clear();
        generador.reinicio();
    }
}
