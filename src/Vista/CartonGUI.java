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
 * @author je110
 */
public class CartonGUI extends javax.swing.JPanel {
    private JLabel[][] labels;
    private Carton cartonActual;
    private List<Point> casillasResaltadas = new ArrayList<>();
    private boolean blinkState = true;
    private boolean modoManual = false;
    private float fadeAlpha = 0f;
    private Timer fadeTimer;

    private float glowLevel = 0f;
    private boolean glowUp = true;
    private Timer glowTimer;

    private float clickHighlightAlpha = 0f;
    private Timer clickTimer;
    /**
     * Creates new form Cartón
     */
    public CartonGUI() {
        initComponents();
        labels = new JLabel[][]{ {lbl1, lbl2, lbl3, lbl4, lbl5}, {lbl6, lbl7, lbl8, lbl9, lbl10}, {lbl11, lbl12, lblFree, lbl14, lbl15}, {lbl16, lbl17, lbl18, lbl19, lbl20}, {lbl21, lbl22, lbl23, lbl24, lbl25} };

        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 5; c++) {
                final int ff = f;
                final int cc = c;

                labels[f][c].setOpaque(true);
                labels[f][c].addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (!modoManual) return;
        if (cartonActual == null) return;

        Casillas cs = cartonActual.getEspacios()[ff][cc];
        if (cs.isDisponible()) return;

        if (cs.isMarcados()) cs.desmarcar();
        else cs.marcar();

        mostrarCarton(cartonActual);
        animacionClickSuave();

        java.awt.Container parent = CartonGUI.this.getParent();
        while (parent != null && !(parent instanceof JuegoGUI)) {
            parent = parent.getParent();
        }

        if (parent instanceof JuegoGUI gw) {
            gw.actualizarEstadoGanadores();
        }
    }
});
                
            }
        }
    }
 
    public Carton getCartonActual() {
        return cartonActual;
    }
    
    public void setModoManual(boolean modo) {
        this.modoManual = modo;
    }

     @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();

        if (fadeAlpha > 0f) {
            g2.setComposite(java.awt.AlphaComposite.getInstance(
                    java.awt.AlphaComposite.SRC_OVER, fadeAlpha));
            g2.setColor(new java.awt.Color(0, 255, 0, 60));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        }

        if (glowLevel > 0f) {
            int alphaGlow = (int) (40 + glowLevel * 80);
            if (alphaGlow > 255) alphaGlow = 255;

            g2.setColor(new java.awt.Color(255, 215, 0, alphaGlow));
            int grosor = 6;
            for (int i = 0; i < 3; i++) {
                g2.drawRoundRect(grosor / 2 + i, grosor / 2 + i, getWidth() - grosor - 2 * i, getHeight() - grosor - 2 * i, 30, 30);
            }
        }

        g2.dispose();
    }
    
    private void animacionClickSuave() {
        if (clickTimer != null && clickTimer.isRunning()) {
            clickTimer.stop();
        }

        clickHighlightAlpha = 0.7f; 
        clickTimer = new Timer(30, e -> {
            clickHighlightAlpha -= 0.08f;
            if (clickHighlightAlpha <= 0f) {
                clickHighlightAlpha = 0f;
                ((Timer) e.getSource()).stop();
            }
            repaint();
        });
        clickTimer.start();
    }

    public void iniciarFadeGanador() {
        if (fadeTimer != null && fadeTimer.isRunning()) {
            fadeTimer.stop();
        }

        fadeAlpha = 0f;
        fadeTimer = new javax.swing.Timer(30, e -> {
            fadeAlpha += 0.05f;
            if (fadeAlpha >= 1f) {
                fadeAlpha = 1f;
                ((javax.swing.Timer) e.getSource()).stop();
            }
            repaint();
        });
        fadeTimer.start();
    }

    public void iniciarGlowGanador() {
        if (glowTimer != null && glowTimer.isRunning()) {
            glowTimer.stop();
        }

        glowLevel = 0f;
        glowUp = true;

        glowTimer = new javax.swing.Timer(40, e -> {
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
        if (glowTimer != null && glowTimer.isRunning()) {
            glowTimer.stop();
        }
        glowLevel = 0f;
        repaint();
    }
    
    public void mostrarCarton(Carton carton) {
        this.cartonActual = carton;

        lblID.setText("ID: " + carton.getId());
        Casillas[][] cs = carton.getEspacios();

        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 5; c++) {
                JLabel lbl = labels[f][c];
                Casillas cas = cs[f][c];

                if (cas.isDisponible()) {
                    lbl.setText("FREE");
                    lbl.setBackground(new Color(0, 120, 255));
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
            }
        }
        revalidate();
        repaint();
    }

    public void setCasillasResaltadas(List<Point> pts) {
        this.casillasResaltadas = (pts != null) ? pts : new ArrayList<>();
        iniciarAnimacion();
    }

    private void iniciarAnimacion() {
        Timer t = new Timer(350, e -> {
            blinkState = !blinkState;
            if (cartonActual != null) {
                mostrarCarton(cartonActual);
            }
        });
       t.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        lbl10 = new javax.swing.JLabel();
        lbl11 = new javax.swing.JLabel();
        lbl12 = new javax.swing.JLabel();
        lblFree = new javax.swing.JLabel();
        lbl14 = new javax.swing.JLabel();
        lbl15 = new javax.swing.JLabel();
        lbl16 = new javax.swing.JLabel();
        lbl17 = new javax.swing.JLabel();
        lbl18 = new javax.swing.JLabel();
        lbl19 = new javax.swing.JLabel();
        lbl20 = new javax.swing.JLabel();
        lbl21 = new javax.swing.JLabel();
        lbl22 = new javax.swing.JLabel();
        lbl23 = new javax.swing.JLabel();
        lbl24 = new javax.swing.JLabel();
        lbl25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        lblI = new javax.swing.JLabel();
        lblN = new javax.swing.JLabel();
        lblG = new javax.swing.JLabel();
        lblO = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(10, 14, 39));

        jPanel1.setBackground(new java.awt.Color(10, 14, 39));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.setLayout(new java.awt.GridLayout(5, 5));

        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl1);

        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl2);

        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl3);

        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl4);

        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl5);

        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl6);

        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl7);

        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl8);

        lbl9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl9);

        lbl10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl10);

        lbl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl11);

        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl12);

        lblFree.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblFree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Free.png"))); // NOI18N
        lblFree.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        lblFree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblFree);

        lbl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl14);

        lbl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl15);

        lbl16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl16);

        lbl17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl17);

        lbl18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl18);

        lbl19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl19);

        lbl20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl20);

        lbl21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl21);

        lbl22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl22);

        lbl23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl23);

        lbl24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl24);

        lbl25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cuadrito.png"))); // NOI18N
        lbl25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 63, 81)));
        jPanel1.add(lbl25);

        jPanel3.setBackground(new java.awt.Color(10, 14, 39));

        lblID.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblID.setText("ID:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(lblID)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblID)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        lblB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/B.png"))); // NOI18N

        lblI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/I.png"))); // NOI18N

        lblN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/N.png"))); // NOI18N

        lblG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/G.png"))); // NOI18N

        lblO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/O.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblI, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(lblN, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(lblG, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(lblO, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblO, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblN, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblG, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblI, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl16;
    private javax.swing.JLabel lbl17;
    private javax.swing.JLabel lbl18;
    private javax.swing.JLabel lbl19;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl20;
    private javax.swing.JLabel lbl21;
    private javax.swing.JLabel lbl22;
    private javax.swing.JLabel lbl23;
    private javax.swing.JLabel lbl24;
    private javax.swing.JLabel lbl25;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblFree;
    private javax.swing.JLabel lblG;
    private javax.swing.JLabel lblI;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblN;
    private javax.swing.JLabel lblO;
    // End of variables declaration//GEN-END:variables
}
