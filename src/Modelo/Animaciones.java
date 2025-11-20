/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
/**
 *
 * @author Valdelomaar
 */
public class Animaciones {
    
    private final JLabel label;
    private Timer timer;
    private ImageIcon[] frames;
    private int currentFrame = 0;

    public Animaciones(JLabel label, int delayMs) {
        this.label = label;
        this.frames = loadFrames();


        int delay = (delayMs > 0) ? delayMs : calcularDelayAutomatico();

        this.timer = new Timer(delay, e -> updateFrame());
    }

    public Animaciones(JLabel labelFelicidades, int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    private ImageIcon[] loadFrames() {

        ArrayList<ImageIcon> lista = new ArrayList<>();
        ClassLoader cl = getClass().getClassLoader();


        for (int i = 1; i <= 200; i++) {

            String resourceName = "imagenes/frame_" + i + ".png";
            URL url = cl.getResource(resourceName);

            if (url == null) break; 

            lista.add(new ImageIcon(url));
        }

        if (lista.isEmpty()) {
            System.err.println("⚠ No se cargó ningún frame. Revisa la carpeta /images/");
            return new ImageIcon[0];
        }

        System.out.println("Frames cargados: " + lista.size());
        return lista.toArray(new ImageIcon[0]);
    }


    private int calcularDelayAutomatico() {
        int n = frames.length;

        if (n <= 0) return 100;

 
        int delay = Math.max(45, 1600 / n);

        System.out.println("⏱ Delay automático = " + delay + " ms (frames=" + n + ")");
        return delay;
    }


    private void updateFrame() {

        if (frames.length == 0) return;

        ImageIcon base = frames[currentFrame];

        int labelW = label.getWidth();
        int labelH = label.getHeight();


        int maxW = 220;
        int maxH = 220;

        int finalW = Math.min(labelW, maxW);
        int finalH = Math.min(labelH, maxH);

        if (finalW <= 0 || finalH <= 0) {
            label.setIcon(base);
            return;
        }

        Image scaled = base.getImage().getScaledInstance(finalW, finalH, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));

        currentFrame = (currentFrame + 1) % frames.length;
    }


    public void start() {
        if (!timer.isRunning()) timer.start();
    }

    public void stop() {
        if (timer.isRunning()) timer.stop();
    }
}
