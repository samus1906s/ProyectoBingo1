/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Constantes;
import Utilidades.Validaciones;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Valdelomaar
 */
public class Juego {
    
    private final List<Carton> cartones;
    private final Tablero tablero;
    private final Tombola tombola;
    private final GeneradorNumeroAutomatico generador;
    private ModoJuego modoJuego;
    private ReglaVictoria reglaVictoria;
    private int ultimoNumero;
    private boolean marcadoAutomatico = true;

    public Juego() {
        this.cartones = new ArrayList<>();
        this.tablero = new Tablero();
        this.generador = new GeneradorNumeroAutomatico();
        this.tombola = new Tombola(generador);
        setModoJuego(ModoJuego.NORMAL);
    }

    public void setMarcadoAutomatico(boolean automatico) {
        this.marcadoAutomatico = automatico;
    }

    public boolean isMarcadoAutomatico() {
        return marcadoAutomatico;
    }

    public void setModoJuego(ModoJuego modo) {
        this.modoJuego = modo;
        switch (modo) {
            case NORMAL:
                this.reglaVictoria = new ReglaNormal();
                break;
            case CUATROESQUINAS:
                this.reglaVictoria = new ReglaCuatroEsquinas();
                break;
            case CARTONLLENO:
                this.reglaVictoria = new ReglaCartonLleno();
                break;
        }
    }

    public Carton crearCartonAutomatico(String id) {
    if (buscarCartonPorId(id) != null) {
        return null;
    }

    Casillas[][] casillas = new Casillas[5][5];
    Random rnd = new Random();

    for (int col = 0; col < 5; col++) {
        Set<Integer> usados = new HashSet<>();

        for (int fila = 0; fila < 5; fila++) {
            boolean libre = (fila == 2 && col == 2);

            if (libre) {
                casillas[fila][col] = new Casillas(0, true);
                continue;
            }

            int min, max;
            switch (col) {
                case 0 -> { min = 1;  max = 15; }
                case 1 -> { min = 16; max = 30; }
                case 2 -> { min = 31; max = 45; }
                case 3 -> { min = 46; max = 60; }
                default -> { min = 61; max = 75; }
            }

            int numero;
            do {
                numero = rnd.nextInt(max - min + 1) + min;
            } while (!Validaciones.numeroColumna(numero, col) ||
                     usados.contains(numero));

            usados.add(numero);
            casillas[fila][col] = new Casillas(numero, false);
        }
    }

    Carton carton = new Carton(id, casillas);
    cartones.add(carton);
    return carton;
}

    public void desmarcarNumeroEnCartones(int numero) {
    for (Carton c : cartones) {
        c.desmarcarNumero(numero);
    }
}
    
    public Carton crearCartonManual(String id, int[][] numeros) {
        if (buscarCartonPorId(id) != null) {
            return null;
        }
        Casillas[][] casillas = new Casillas[Constantes.filasCarton][Constantes.columnasCarton];
        for (int fila = 0; fila < Constantes.filasCarton; fila++) {
            for (int col = 0; col < Constantes.columnasCarton; col++) {
                boolean libre = (fila == 2 && col == 2);
                int valor = libre ? 0 : numeros[fila][col];
                casillas[fila][col] = new Casillas(valor, libre);
            }
        }
        Carton carton = new Carton(id, casillas);
        cartones.add(carton);
        return carton;
    }

    public List<Carton> getCartones() {
        return Collections.unmodifiableList(cartones);
    }

    public Carton buscarCartonPorId(String id) {
        for (Carton c : cartones) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void eliminarCartonPorId(String id) {
        cartones.removeIf(c -> c.getId().equals(id));
    }

    public void eliminarTodosCartones() {
        cartones.clear();
    }

    public void limpiarCartones() {
        for (Carton c : cartones) {
            c.limpiarMarcados();
        }
    }

    public void marcarNumeroEnCartones(int numero) {
        for (Carton carton : cartones) {
            carton.marcarNumero(numero);
        }
    }

    public void marcarNumeroEnCarton(String idCarton, int numero) {
        for (Carton c : cartones) {
            if (c.getId().equals(idCarton)) {
                c.marcarNumero(numero);
                break;
            }
        }
    }

    public int procesarSiguienteNumero() {
        int numero = tombola.extraerNumero();
        ultimoNumero = numero;
        tablero.marcar(numero);
        if (marcadoAutomatico) {
            marcarNumeroEnCartones(numero);
        }
        return numero;
    }

    public int getUltimoNumero() {
        return ultimoNumero;
    }

    public List<Integer> getHistorialTombola() {
        return tombola.getHistorial();
    }

    public void reiniciarJuego() {
        tombola.reiniciar();
        tablero.reiniciar();
        limpiarCartones();
        ultimoNumero = 0;
    }

    public List<Carton> obtenerGanadores() {
        List<Carton> ganadores = new ArrayList<>();
        if (reglaVictoria == null) {
            return ganadores;
        }
        for (Carton c : cartones) {
            if (reglaVictoria.esGanador(c)) {
                ganadores.add(c);
            }
        }
        return ganadores;
    }

    public List<Point> obtenerLineaGanadora(Carton carton) {
        List<Point> pts = new ArrayList<>();
        Casillas[][] c = carton.getEspacios();

        if (modoJuego == ModoJuego.CUATROESQUINAS) {
            boolean esquinas =
                    c[0][0].isMarcados() &&
                    c[0][4].isMarcados() &&
                    c[4][0].isMarcados() &&
                    c[4][4].isMarcados();
            if (esquinas) {
                pts.add(new Point(0, 0));
                pts.add(new Point(0, 4));
                pts.add(new Point(4, 0));
                pts.add(new Point(4, 4));
            }
            return pts;
        }

        if (modoJuego == ModoJuego.CARTONLLENO) {
            boolean lleno = true;
            for (int fila = 0; fila < 5; fila++) {
                for (int col = 0; col < 5; col++) {
                    if (!c[fila][col].isMarcados()) {
                        lleno = false;
                        break;
                    }
                }
                if (!lleno) {
                    break;
                }
            }
            if (lleno) {
                for (int fila = 0; fila < 5; fila++) {
                    for (int col = 0; col < 5; col++) {
                        pts.add(new Point(fila, col));
                    }
                }
            }
            return pts;
        }

        for (int fila = 0; fila < 5; fila++) {
            boolean ok = true;
            for (int col = 0; col < 5; col++) {
                if (!c[fila][col].isMarcados()) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                for (int col = 0; col < 5; col++) {
                    pts.add(new Point(fila, col));
                }
                return pts;
            }
        }

        for (int col = 0; col < 5; col++) {
            boolean ok = true;
            for (int fila = 0; fila < 5; fila++) {
                if (!c[fila][col].isMarcados()) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                for (int fila = 0; fila < 5; fila++) {
                    pts.add(new Point(fila, col));
                }
                return pts;
            }
        }

        boolean okDiag1 = true;
        for (int i = 0; i < 5; i++) {
            if (!c[i][i].isMarcados()) {
                okDiag1 = false;
                break;
            }
        }
        if (okDiag1) {
            for (int i = 0; i < 5; i++) {
                pts.add(new Point(i, i));
            }
            return pts;
        }

        boolean okDiag2 = true;
        for (int i = 0; i < 5; i++) {
            if (!c[i][4 - i].isMarcados()) {
                okDiag2 = false;
                break;
            }
        }
        if (okDiag2) {
            for (int i = 0; i < 5; i++) {
                pts.add(new Point(i, 4 - i));
            }
            return pts;
        }

        return pts;
    }
    
}
