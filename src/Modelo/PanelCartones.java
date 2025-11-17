/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.GridLayout;
import javax.swing.JPanel;
//import src.controller.JuegoController;
//import model.entities.Carton;
//import src.view.PanelCartonUI;
/**
 *
 * @author je110
 */
public class PanelCartones extends JPanel {
    
    //private JuegoController controller;
    private boolean modoManual = false;

    //public PanelCartones(JuegoController controller) {
        //this.controller = controller;
        //setLayout(new GridLayout(0, 2, 10, 10));
    //}

    public void setModoMarcadoManual(boolean manual) {
        this.modoManual = manual;
    }

    public void refrescarCartones() {
        removeAll();

        //for (Carton c : controller.getCartones()) {
           // PanelCartonUI cv = new PanelCartonUI();
            //cv.setModoManual(modoManual);
            //cv.mostrarCarton(c);
            //add(cv);
    }

       // revalidate();
       // repaint();
}
