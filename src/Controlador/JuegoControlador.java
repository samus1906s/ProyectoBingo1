/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Carton;
import java.awt.Point;
import java.util.List;

import Modelo.Juego;
import Modelo.Carton;
import Modelo.ModoJuego;

/**
 *
 * @author Valdelomaar
 */
public class JuegoControlador {
    
     private final Juego juego;

    public JuegoControlador(Juego juego) {
        this.juego = juego;
    }

 
    public void setModoJuego(ModoJuego modo) {
        juego.setModoJuego(modo);
    }


    public Carton crearCartonAutomatico(String id) {
        return juego.crearCartonAutomatico(id);
    }

    public Carton crearCartonManual(String id, int[][] numeros) {
        return juego.crearCartonManual(id, numeros);
    }

    public List<Carton> getCartones() {
        return juego.getCartones();
    }

    public Carton buscarCartonPorId(String id) {
        return juego.buscarCartonPorId(id);
    }

    public void eliminarCartonPorId(String id) {
        juego.eliminarCartonPorId(id);
    }

    public void eliminarTodosCartones() {
        juego.eliminarTodosCartones();
    }

    public void limpiarCartones() {
        juego.limpiarCartones();
    }




    public int extraerSiguienteNumero() {
        return juego.procesarSiguienteNumero();   
    }

    /** EXTRACCIÓN MANUAL (textfield) */
    public int extraerNumeroManual(int numero) {
        return juego.agregarNumeroManual(numero); 
    }

    public int getUltimoNumero() {
        return juego.getUltimoNumero();
    }

    public List<Integer> getHistorialTombola() {
        return juego.getHistorialTombola();
    }



    public boolean marcarNumeroEnCartones(int numero) {
    return juego.marcarCartones(numero); 
}
    
    public boolean numeroHaSalido(int numero) {
    return juego.getHistorialTombola().contains(numero);
}
    

    public List<Carton> obtenerGanadores() {
        return juego.obtenerGanadores();
    }

    public List<Point> obtenerLineaGanadora(Carton carton) {
        return juego.obtenerLineaGanadora(carton);
    }


    public void reiniciarJuego() {
        juego.reiniciarJuego();
    }
}
