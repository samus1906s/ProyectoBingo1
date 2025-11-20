/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author samue
 */
public class ReglaNormal implements ReglaVictoria{

    @Override
    public boolean esGanador(Carton carton) {
         Casillas[][] c = carton.getEspacios();


    for (int fila = 0; fila < 5; fila++) {
        boolean completa = true;
        for (int col = 0; col < 5; col++) {
            if (!c[fila][col].isMarcados()) {
                completa = false;
                break;
            }
        }
        if (completa) 
            
            return true;
    }


    for (int col = 0; col < 5; col++) {
        boolean completa = true;
        for (int fila = 0; fila < 5; fila++) {
            if (!c[fila][col].isMarcados()) {
                completa = false;
                break;
            }
        }
        if (completa) 
            
            return true;
    }

 
    boolean diag1 = true;
    for (int i = 0; i < 5; i++) {
        if (!c[i][i].isMarcados()) {
            diag1 = false;
            break;
        }
    }
    if (diag1) 
        
        return true;


    boolean diag2 = true;
    for (int i = 0; i < 5; i++) {
        if (!c[i][4 - i].isMarcados()) {
            diag2 = false;
            break;
        }
    }
    if (diag2) 
        
        return true;

    return false;
    }
    
   }
    
    
    

