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

    public Juego() {
        this.cartones = new ArrayList<>();
        this.tablero = new Tablero();
        this.generador = new GeneradorNumeroAutomatico();
        this.tombola = new Tombola(generador);
        setModoJuego(ModoJuego.NORMAL);
    }


    public void setModoJuego(ModoJuego modo) {
        this.modoJuego = modo;

        switch (modo) {
            case NORMAL -> this.reglaVictoria = new ReglaNormal();
            case CUATROESQUINAS -> this.reglaVictoria = new ReglaCuatroEsquinas();
            case CARTONLLENO -> this.reglaVictoria = new ReglaCartonLleno();
        }
    }

   
    public Carton crearCartonAutomatico(String id) {
        if (buscarCartonPorId(id) != null) return null;

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
                    case 0 -> { min = 1; max = 15; }
                    case 1 -> { min = 16; max = 30; }
                    case 2 -> { min = 31; max = 45; }
                    case 3 -> { min = 46; max = 60; }
                    default -> { min = 61; max = 75; }
                }

                int numero;
                do {
                    numero = rnd.nextInt(max - min + 1) + min;
                } while (!Validaciones.numeroColumna(numero, col) || usados.contains(numero));

                usados.add(numero);
                casillas[fila][col] = new Casillas(numero, false);
            }
        }

        Carton c = new Carton(id, casillas);
        cartones.add(c);
        return c;
    }


    public Carton crearCartonManual(String id, int[][] numeros) {
        if (buscarCartonPorId(id) != null) return null;

        Casillas[][] casillas = new Casillas[Constantes.filasCarton][Constantes.columnasCarton];

        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {

                boolean libre = (fila == 2 && col == 2);
                int valor = libre ? 0 : numeros[fila][col];

                casillas[fila][col] = new Casillas(valor, libre);
            }
        }

        Carton c = new Carton(id, casillas);
        cartones.add(c);
        return c;
    }


    public List<Carton> getCartones() {
        return Collections.unmodifiableList(cartones);
    }

    public Carton buscarCartonPorId(String id) {
        return cartones.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void eliminarCartonPorId(String id) {
        cartones.removeIf(c -> c.getId().equals(id));
    }

    public void eliminarTodosCartones() {
        cartones.clear();
    }

    public void limpiarCartones() {
        for (Carton c : cartones) c.limpiarMarcados();
    }


       public boolean marcarCartones(int numero) {
    boolean marcado = false;

    for (Carton c : cartones) {
        if (c.marcarNumero(numero)) {
            marcado = true;
        }
    }
    return marcado;
}
    

    public int procesarSiguienteNumero() {

    int numero = tombola.extraerNumero();
    if (numero <= 0) return -1;

    ultimoNumero = numero;


    tablero.marcar(numero);


    return numero;
}

    
    public int agregarNumeroManual(int numero) {

    if (numero < 1 || numero > 75) return -1;
    if (tablero.estaMarcado(numero)) return -2;

    tablero.marcar(numero);
    tombola.agregarNumeroManual(numero); 
    ultimoNumero = numero;

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

        if (reglaVictoria == null) return ganadores;

        for (Carton c : cartones) {
            if (reglaVictoria.esGanador(c)) ganadores.add(c);
        }

        return ganadores;
    }


    public List<Point> obtenerLineaGanadora(Carton carton) {

        List<Point> pts = new ArrayList<>();
        Casillas[][] c = carton.getEspacios();


        if (modoJuego == ModoJuego.CUATROESQUINAS) {
            if (c[0][0].isMarcados() &&
                c[0][4].isMarcados() &&
                c[4][0].isMarcados() &&
                c[4][4].isMarcados()) {

                pts.add(new Point(0, 0));
                pts.add(new Point(0, 4));
                pts.add(new Point(4, 0));
                pts.add(new Point(4, 4));
            }
            return pts;
        }

        if (modoJuego == ModoJuego.CARTONLLENO) {

            boolean lleno = true;
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    if (!c[i][j].isMarcados())
                        lleno = false;

            if (lleno) {
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 5; j++)
                        pts.add(new Point(i, j));
            }
            return pts;
        }



        for (int fila = 0; fila < 5; fila++) {
            boolean ok = true;

            for (int col = 0; col < 5; col++) {
                if (!c[fila][col].isMarcados()) ok = false;
            }

            if (ok) {
                for (int col = 0; col < 5; col++)
                    pts.add(new Point(fila, col));
                return pts;
            }
        }


        for (int col = 0; col < 5; col++) {
            boolean ok = true;

            for (int fila = 0; fila < 5; fila++) {
                if (!c[fila][col].isMarcados()) ok = false;
            }

            if (ok) {
                for (int fila = 0; fila < 5; fila++)
                    pts.add(new Point(fila, col));
                return pts;
            }
        }


        boolean diag1 = true;
        for (int i = 0; i < 5; i++)
            if (!c[i][i].isMarcados()) diag1 = false;
        if (diag1) {
            for (int i = 0; i < 5; i++)
                pts.add(new Point(i, i));
            return pts;
        }


        boolean diag2 = true;
        for (int i = 0; i < 5; i++)
            if (!c[i][4 - i].isMarcados()) diag2 = false;
        if (diag2) {
            for (int i = 0; i < 5; i++)
                pts.add(new Point(i, 4 - i));
            return pts;
        }

        return pts;
    }
}
