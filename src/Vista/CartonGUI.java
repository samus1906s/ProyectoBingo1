/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package Vista;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.Timer;
import Modelo.Carton;
import Modelo.Casillas;
/**
 *
 * @author Valdelomaar
 */
public class CartonGUI extends javax.swing.JPanel {

    private final JLabel[][] labels;
    private Carton cartonActual;
    private List<Point> casillasResaltadas = new ArrayList<>();
    private boolean blinkState = true;

    private float fadeAlpha = 0f;
    private Timer fadeTimer;

    private float glowLevel = 0f;
    private boolean glowUp = true;
    private Timer glowTimer;

    public CartonGUI() {
        initComponents();

        labels = new JLabel[][]{
            {Label1, Label2, Label3, Label4, Label5},
            {Label6, Label7, Label8, Label9, Label10},
            {Label11, Label12, Label13, Label14, Label15},
            {Label16, Label17, Label18, Label19, Label20},
            {Label21, Label22, Label23, Label24, Label25}
        };


        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 5; c++) {
                labels[f][c].setOpaque(true);
            }
        }
    }

    public Carton getCartonActual() {
        return cartonActual;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();


        if (fadeAlpha > 0f) {
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, fadeAlpha));
            g2.setColor(new java.awt.Color(0, 255, 0, 60));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        }


        if (glowLevel > 0f) {
            int alphaGlow = (int) (40 + glowLevel * 80);
            if (alphaGlow > 255) alphaGlow = 255;

            g2.setColor(new java.awt.Color(255, 215, 0, alphaGlow));
            int grosor = 6;

            for (int i = 0; i < 3; i++) {
                g2.drawRoundRect(
                    grosor / 2 + i,
                    grosor / 2 + i,
                    getWidth() - grosor - 2 * i,
                    getHeight() - grosor - 2 * i,
                    30,
                    30
                );
            }
        }

        g2.dispose();
    }

    public void iniciarFadeGanador() {
        if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();

        fadeAlpha = 0f;
        fadeTimer = new Timer(30, e -> {
            fadeAlpha += 0.05f;
            if (fadeAlpha >= 1f) {
                fadeAlpha = 1f;
                ((Timer) e.getSource()).stop();
            }
            repaint();
        });
        fadeTimer.start();
    }

    public void iniciarGlowGanador() {
        if (glowTimer != null && glowTimer.isRunning()) glowTimer.stop();

        glowLevel = 0f;
        glowUp = true;

        glowTimer = new Timer(40, e -> {
            if (glowUp) {
                glowLevel += 0.05f;
                if (glowLevel >= 1f) {
                    glowLevel = 1f;
                    glowUp = false;
                }
            } else {
                glowLevel -= 0.05f;
                if (glowLevel <= 0f) {
                    glowLevel = 0f;
                    glowUp = true;
                }
            }
            repaint();
        });

        glowTimer.start();
    }

    public void detenerGlowGanador() {
        if (glowTimer != null && glowTimer.isRunning()) glowTimer.stop();
        glowLevel = 0f;
        repaint();
    }

    

    public void setCasillasResaltadas(List<Point> pts) {
        casillasResaltadas = (pts != null) ? pts : new ArrayList<>();
        iniciarAnimacion();
    }

    private void iniciarAnimacion() {
        Timer t = new Timer(350, e -> {
            blinkState = !blinkState;
            if (cartonActual != null) mostrarCarton(cartonActual);
        });
        t.start();
    }
    
    
    public void mostrarCarton(Carton carton) {
    this.cartonActual = carton;
    LabelID.setText("ID: " + carton.getId());

    Casillas[][] cs = carton.getEspacios();

    for (int f = 0; f < 5; f++) {
        for (int c = 0; c < 5; c++) {

            JLabel lbl = labels[f][c];
            Casillas cas = cs[f][c];

            lbl.setOpaque(true);     
            lbl.setText("");        

            if (cas.isDisponible()) {
                
                lbl.setBackground(new Color(0, 120, 255));
                lbl.setText("FREE");

            } else {
                lbl.setText(String.valueOf(cas.getValores()));

                if (cas.isMarcados()) {

                    lbl.setBackground(new Color(0, 170, 0)); 

                } else {

                    lbl.setBackground(new Color(45, 45, 45));
                }
            }


            if (casillasResaltadas.contains(new Point(f, c)) && blinkState) {
                lbl.setBackground(Color.YELLOW);
            }

            lbl.repaint();  
        }
    }

    revalidate();
    repaint();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        LabelB = new javax.swing.JLabel();
        LabelI = new javax.swing.JLabel();
        LabelN = new javax.swing.JLabel();
        LabelG = new javax.swing.JLabel();
        LabelO = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        Label3 = new javax.swing.JLabel();
        Label4 = new javax.swing.JLabel();
        Label5 = new javax.swing.JLabel();
        Label6 = new javax.swing.JLabel();
        Label7 = new javax.swing.JLabel();
        Label8 = new javax.swing.JLabel();
        Label9 = new javax.swing.JLabel();
        Label10 = new javax.swing.JLabel();
        Label11 = new javax.swing.JLabel();
        Label12 = new javax.swing.JLabel();
        Label13 = new javax.swing.JLabel();
        Label14 = new javax.swing.JLabel();
        Label15 = new javax.swing.JLabel();
        Label16 = new javax.swing.JLabel();
        Label17 = new javax.swing.JLabel();
        Label18 = new javax.swing.JLabel();
        Label19 = new javax.swing.JLabel();
        Label20 = new javax.swing.JLabel();
        Label21 = new javax.swing.JLabel();
        Label22 = new javax.swing.JLabel();
        Label23 = new javax.swing.JLabel();
        Label24 = new javax.swing.JLabel();
        Label25 = new javax.swing.JLabel();
        LabelID = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel3.setLayout(new java.awt.GridLayout(1, 5));

        LabelB.setBackground(new java.awt.Color(51, 51, 51));
        LabelB.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        LabelB.setForeground(new java.awt.Color(255, 0, 0));
        LabelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelB.setText("B");
        LabelB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        LabelB.setOpaque(true);
        jPanel3.add(LabelB);

        LabelI.setBackground(new java.awt.Color(51, 51, 51));
        LabelI.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        LabelI.setForeground(new java.awt.Color(255, 255, 51));
        LabelI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelI.setText("I");
        LabelI.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        LabelI.setOpaque(true);
        jPanel3.add(LabelI);

        LabelN.setBackground(new java.awt.Color(51, 51, 51));
        LabelN.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        LabelN.setForeground(new java.awt.Color(0, 204, 153));
        LabelN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelN.setText("N");
        LabelN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        LabelN.setOpaque(true);
        jPanel3.add(LabelN);

        LabelG.setBackground(new java.awt.Color(51, 51, 51));
        LabelG.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        LabelG.setForeground(new java.awt.Color(51, 153, 255));
        LabelG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelG.setText("G");
        LabelG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        LabelG.setOpaque(true);
        jPanel3.add(LabelG);

        LabelO.setBackground(new java.awt.Color(51, 51, 51));
        LabelO.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        LabelO.setForeground(new java.awt.Color(153, 51, 255));
        LabelO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelO.setText("O");
        LabelO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        LabelO.setOpaque(true);
        jPanel3.add(LabelO);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.setLayout(new java.awt.GridLayout(5, 5));

        Label1.setBackground(java.awt.Color.black);
        Label1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label1);

        Label2.setBackground(java.awt.Color.black);
        Label2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label2);

        Label3.setBackground(java.awt.Color.black);
        Label3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label3);

        Label4.setBackground(java.awt.Color.black);
        Label4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label4);

        Label5.setBackground(java.awt.Color.black);
        Label5.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label5);

        Label6.setBackground(java.awt.Color.black);
        Label6.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label6);

        Label7.setBackground(java.awt.Color.black);
        Label7.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label7);

        Label8.setBackground(java.awt.Color.black);
        Label8.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label8);

        Label9.setBackground(java.awt.Color.black);
        Label9.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label9);

        Label10.setBackground(java.awt.Color.black);
        Label10.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label10);

        Label11.setBackground(java.awt.Color.black);
        Label11.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label11);

        Label12.setBackground(java.awt.Color.black);
        Label12.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label12);

        Label13.setBackground(java.awt.Color.black);
        Label13.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label13.setText("FREE");
        Label13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label13);

        Label14.setBackground(java.awt.Color.black);
        Label14.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label14);

        Label15.setBackground(java.awt.Color.black);
        Label15.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label15);

        Label16.setBackground(java.awt.Color.black);
        Label16.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label16);

        Label17.setBackground(java.awt.Color.black);
        Label17.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label17);

        Label18.setBackground(java.awt.Color.black);
        Label18.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label18);

        Label19.setBackground(java.awt.Color.black);
        Label19.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label19);

        Label20.setBackground(java.awt.Color.black);
        Label20.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label20);

        Label21.setBackground(java.awt.Color.black);
        Label21.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label21);

        Label22.setBackground(java.awt.Color.black);
        Label22.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label22);

        Label23.setBackground(java.awt.Color.black);
        Label23.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label23);

        Label24.setBackground(java.awt.Color.black);
        Label24.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label24);

        Label25.setBackground(java.awt.Color.black);
        Label25.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        Label25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Label25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Label25);

        LabelID.setBackground(new java.awt.Color(102, 102, 102));
        LabelID.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        LabelID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelID.setText("ID:");
        LabelID.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label10;
    private javax.swing.JLabel Label11;
    private javax.swing.JLabel Label12;
    private javax.swing.JLabel Label13;
    private javax.swing.JLabel Label14;
    private javax.swing.JLabel Label15;
    private javax.swing.JLabel Label16;
    private javax.swing.JLabel Label17;
    private javax.swing.JLabel Label18;
    private javax.swing.JLabel Label19;
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Label20;
    private javax.swing.JLabel Label21;
    private javax.swing.JLabel Label22;
    private javax.swing.JLabel Label23;
    private javax.swing.JLabel Label24;
    private javax.swing.JLabel Label25;
    private javax.swing.JLabel Label3;
    private javax.swing.JLabel Label4;
    private javax.swing.JLabel Label5;
    private javax.swing.JLabel Label6;
    private javax.swing.JLabel Label7;
    private javax.swing.JLabel Label8;
    private javax.swing.JLabel Label9;
    private javax.swing.JLabel LabelB;
    private javax.swing.JLabel LabelG;
    private javax.swing.JLabel LabelI;
    private javax.swing.JLabel LabelID;
    private javax.swing.JLabel LabelN;
    private javax.swing.JLabel LabelO;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
