/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author samue
 */
public class ReglaCuatroEsquinas implements ReglaVictoria{

    @Override
    public boolean esGanador(Carton carton) {
         Casillas[][] c = carton.getEspacios();
        return c[0][0].isMarcados()
            && c[0][4].isMarcados()
            && c[4][0].isMarcados()
            && c[4][4].isMarcados();
    }
    
    }
    
    

