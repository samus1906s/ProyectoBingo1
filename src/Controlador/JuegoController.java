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
public class JuegoController {
    
    private final Juego juego;

    public JuegoController(Juego juego) {
        this.juego = juego;
    }

   
    public void setModoJuego(ModoJuego modo) {
        juego.setModoJuego(modo);
    }

    public void setMarcadoAutomatico(boolean automatico) {
        juego.setMarcadoAutomatico(automatico);
    }

    public boolean isMarcadoAutomatico() {
        return juego.isMarcadoAutomatico();
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

    public List<Point> obtenerLineaGanadora(Carton carton) {
    return juego.obtenerLineaGanadora(carton);
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

    public int getUltimoNumero() {
        return juego.getUltimoNumero();
    }

    public List<Integer> getHistorialTombola() {
        return juego.getHistorialTombola();
    }

    public void reiniciarJuego() {
        juego.reiniciarJuego();
    }

   
    public void marcarNumeroEnCarton(String idCarton, int numero) {
        juego.marcarNumeroEnCarton(idCarton, numero);
    }

   
    public List<Carton> obtenerGanadores() {
        return juego.obtenerGanadores();
    }
    
}
