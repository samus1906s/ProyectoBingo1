/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author samue
 */
public class ReglaCartonLleno implements ReglaVictoria{

    @Override
    public boolean esGanador(Carton carton) {
        Casillas[][] c = carton.getEspacios();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if(!c[i][j].isMarcados()){
                    return false;
                }
            }
        }
      return true;  
    }   
}
