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

  /*  @Override
    public boolean esGanador(Cartones carton) {
         Casillas[][] c = carton.getCasillas();

    //  Juego Horizontal 
    for (int fila = 0; fila < 5; fila++) {
        boolean completa = true;
        for (int col = 0; col < 5; col++) {
            if (!c[fila][col].isMarcado()) {
                completa = false;
                break;
            }
        }
        if (completa) 
            
            return true;
    }

    //  Juego Vertical 
    for (int col = 0; col < 5; col++) {
        boolean completa = true;
        for (int fila = 0; fila < 5; fila++) {
            if (!c[fila][col].isMarcado()) {
                completa = false;
                break;
            }
        }
        if (completa) 
            
            return true;
    }

    //  Juego Diagonal Primero   
    boolean diag1 = true;
    for (int i = 0; i < 5; i++) {
        if (!c[i][i].isMarcado()) {
            diag1 = false;
            break;
        }
    }
    if (diag1) 
        
        return true;

    //   Juego Diagonal Segundo 
    boolean diag2 = true;
    for (int i = 0; i < 5; i++) {
        if (!c[i][4 - i].isMarcado()) {
            diag2 = false;
            break;
        }
    }
    if (diag2) 
        
        return true;

    return false;
    }
    
   }
    
    
    

