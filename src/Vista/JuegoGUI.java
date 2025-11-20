
package Vista;

import Controlador.JuegoControlador;
import javax.swing.JOptionPane;
import Modelo.Carton;
import Modelo.ModoJuego;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Timer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JuegoGUI extends javax.swing.JFrame {
   private boolean llenadoAutomatico;
     private JuegoControlador controlador;

    private PanelCartonesGUI panelCartones;
    private TombolaGUI panelTombola;
    private PanelEstadoGanadores panelEstadoGanadores;
    private Timer timerParpadeo;
    private boolean estadoParpadeo = false;

    public JuegoGUI(JuegoControlador controller, boolean modoAutomatico, ModoJuego modoJuego) {
        initComponents();
        this.controlador = controller;
        this.llenadoAutomatico = modoAutomatico;
        this.llenadoAutomatico = modoAutomatico;
        setLocationRelativeTo(null);


        PanelFondo fondo = new PanelFondo("src\\imagenes\\mesa_negra.jpg");

        panelCartones = new PanelCartonesGUI(controlador);
        panelCartones.setOpaque(false);
        fondo.add(panelCartones);
        scrollCartones.setViewportView(fondo);
        panelCartones.refrescarCartones();


        panelEstadoGanadores = new PanelEstadoGanadores();
        panelEstadoContainer.add(panelEstadoGanadores);
        panelEstadoGanadores.setEstadoGanador(false);


        panelTombola = new TombolaGUI();
        panelTombolaContainer.add(panelTombola);


        DocumentFilter onlyNumbers = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string != null && string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null || text.isEmpty() || text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        ((AbstractDocument) txtIdCarton.getDocument()).setDocumentFilter(onlyNumbers);
        ((AbstractDocument) txtEliminarId.getDocument()).setDocumentFilter(onlyNumbers);
        ((AbstractDocument) labelNumeroManual.getDocument()).setDocumentFilter(onlyNumbers);


        String textoModo = switch (modoJuego) {
            case CUATROESQUINAS -> "Modo de juego: CUATRO ESQUINAS";
            case CARTONLLENO -> "Modo de juego: CARTÓN LLENO";
            default -> "Modo de juego: NORMAL";
        };
        lblModoJuego.setText(textoModo);
        lblCreado.setVisible(false);
        lblUltimoNumero.setText("Último número: -");


        panelCampoManual.setVisible(!llenadoAutomatico);
        btnMarcar.setVisible(!llenadoAutomatico);

        if (llenadoAutomatico) {
            panelCampoManual.setPreferredSize(new Dimension(0, 0));
        } else {
            panelCampoManual.setPreferredSize(null);
        }

        panelMenu.revalidate();
        panelMenu.repaint();
    }

    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        panelCampoManual = new javax.swing.JPanel();
        labelNumeroManual = new javax.swing.JTextField();
        btnMarcar = new javax.swing.JButton();
        btnExtraerNumero = new javax.swing.JButton();
        panelSlot = new javax.swing.JPanel();
        lblUltimoNumero = new javax.swing.JLabel();
        btnVerificar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        panelEstadoContainer = new javax.swing.JPanel();
        btnVolverMenu = new javax.swing.JButton();
        lblModoJuego = new javax.swing.JLabel();
        panelMenuArriba = new javax.swing.JPanel();
        lblIdCarton = new javax.swing.JLabel();
        txtIdCarton = new javax.swing.JTextField();
        btnCrearCarton = new javax.swing.JButton();
        btnLimpiarCartones = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtEliminarId = new javax.swing.JTextField();
        btnEliminarUno = new javax.swing.JButton();
        btnEliminarTodos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblCreado = new javax.swing.JLabel();
        lblEliminado = new javax.swing.JLabel();
        panelTombolaContainer = new javax.swing.JPanel();
        scrollCartones = new javax.swing.JScrollPane();
        panelCartonesContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelMenu.setBackground(new java.awt.Color(51, 51, 51));
        panelMenu.setForeground(new java.awt.Color(204, 204, 204));
        panelMenu.setName("panelMenu"); // NOI18N
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 400));
        panelMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        panelCampoManual.setPreferredSize(new java.awt.Dimension(245, 90));

        labelNumeroManual.setBackground(new java.awt.Color(51, 51, 51));
        labelNumeroManual.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        labelNumeroManual.setForeground(new java.awt.Color(255, 255, 255));
        labelNumeroManual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelNumeroManual.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        labelNumeroManual.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout panelCampoManualLayout = new javax.swing.GroupLayout(panelCampoManual);
        panelCampoManual.setLayout(panelCampoManualLayout);
        panelCampoManualLayout.setHorizontalGroup(
            panelCampoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelNumeroManual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );
        panelCampoManualLayout.setVerticalGroup(
            panelCampoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelNumeroManual, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        panelMenu.add(panelCampoManual);

        btnMarcar.setBackground(new java.awt.Color(51, 51, 51));
        btnMarcar.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnMarcar.setForeground(new java.awt.Color(255, 255, 255));
        btnMarcar.setText("MARCAR");
        btnMarcar.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnMarcar.setPreferredSize(new java.awt.Dimension(245, 50));
        btnMarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarActionPerformed(evt);
            }
        });
        panelMenu.add(btnMarcar);

        btnExtraerNumero.setBackground(new java.awt.Color(51, 51, 51));
        btnExtraerNumero.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnExtraerNumero.setForeground(new java.awt.Color(255, 255, 255));
        btnExtraerNumero.setText("Extraer Numero");
        btnExtraerNumero.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnExtraerNumero.setMaximumSize(new java.awt.Dimension(100, 30));
        btnExtraerNumero.setMinimumSize(new java.awt.Dimension(200, 50));
        btnExtraerNumero.setOpaque(true);
        btnExtraerNumero.setPreferredSize(new java.awt.Dimension(245, 60));
        btnExtraerNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtraerNumeroActionPerformed(evt);
            }
        });
        panelMenu.add(btnExtraerNumero);

        panelSlot.setBackground(new java.awt.Color(51, 51, 51));
        panelSlot.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        panelSlot.setPreferredSize(new java.awt.Dimension(245, 70));

        lblUltimoNumero.setBackground(new java.awt.Color(255, 255, 255));
        lblUltimoNumero.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelSlotLayout = new javax.swing.GroupLayout(panelSlot);
        panelSlot.setLayout(panelSlotLayout);
        panelSlotLayout.setHorizontalGroup(
            panelSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUltimoNumero)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        panelSlotLayout.setVerticalGroup(
            panelSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSlotLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblUltimoNumero)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        panelMenu.add(panelSlot);

        btnVerificar.setBackground(new java.awt.Color(51, 51, 51));
        btnVerificar.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnVerificar.setForeground(new java.awt.Color(255, 255, 255));
        btnVerificar.setText("Verificar Ganadores");
        btnVerificar.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnVerificar.setMaximumSize(new java.awt.Dimension(150, 40));
        btnVerificar.setMinimumSize(new java.awt.Dimension(1, 33));
        btnVerificar.setPreferredSize(new java.awt.Dimension(245, 60));
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        panelMenu.add(btnVerificar);

        btnReiniciar.setBackground(new java.awt.Color(51, 51, 51));
        btnReiniciar.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnReiniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnReiniciar.setText("Reiniciar Juego");
        btnReiniciar.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnReiniciar.setMaximumSize(new java.awt.Dimension(100, 30));
        btnReiniciar.setMinimumSize(new java.awt.Dimension(200, 50));
        btnReiniciar.setPreferredSize(new java.awt.Dimension(245, 60));
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });
        panelMenu.add(btnReiniciar);

        panelEstadoContainer.setMaximumSize(new java.awt.Dimension(200, 80));
        panelEstadoContainer.setMinimumSize(new java.awt.Dimension(200, 80));
        panelEstadoContainer.setOpaque(false);
        panelEstadoContainer.setPreferredSize(new java.awt.Dimension(245, 210));
        panelEstadoContainer.setLayout(new javax.swing.BoxLayout(panelEstadoContainer, javax.swing.BoxLayout.LINE_AXIS));
        panelMenu.add(panelEstadoContainer);

        btnVolverMenu.setBackground(new java.awt.Color(51, 51, 51));
        btnVolverMenu.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnVolverMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverMenu.setText("Volver al Menu");
        btnVolverMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnVolverMenu.setPreferredSize(new java.awt.Dimension(245, 50));
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });
        panelMenu.add(btnVolverMenu);

        lblModoJuego.setBackground(new java.awt.Color(51, 51, 51));
        lblModoJuego.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        lblModoJuego.setForeground(new java.awt.Color(255, 255, 255));
        lblModoJuego.setText("Modalidad: -");
        lblModoJuego.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        lblModoJuego.setOpaque(true);
        lblModoJuego.setPreferredSize(new java.awt.Dimension(245, 60));
        panelMenu.add(lblModoJuego);

        getContentPane().add(panelMenu, java.awt.BorderLayout.WEST);

        panelMenuArriba.setBackground(new java.awt.Color(51, 51, 51));
        panelMenuArriba.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelMenuArriba.setForeground(new java.awt.Color(204, 204, 204));
        panelMenuArriba.setPreferredSize(new java.awt.Dimension(50, 65));

        lblIdCarton.setBackground(new java.awt.Color(51, 51, 51));
        lblIdCarton.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblIdCarton.setForeground(new java.awt.Color(255, 255, 255));
        lblIdCarton.setText("ID Cartón:");
        lblIdCarton.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));

        btnCrearCarton.setBackground(new java.awt.Color(51, 51, 51));
        btnCrearCarton.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnCrearCarton.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCarton.setText("Crear Cartón");
        btnCrearCarton.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnCrearCarton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCartonActionPerformed(evt);
            }
        });

        btnLimpiarCartones.setBackground(new java.awt.Color(51, 51, 51));
        btnLimpiarCartones.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnLimpiarCartones.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarCartones.setText("Limpiar Cartones");
        btnLimpiarCartones.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnLimpiarCartones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCartonesActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Eliminar ID:");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));

        btnEliminarUno.setBackground(new java.awt.Color(51, 51, 51));
        btnEliminarUno.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnEliminarUno.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarUno.setText("Eliminar Uno");
        btnEliminarUno.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnEliminarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUnoActionPerformed(evt);
            }
        });

        btnEliminarTodos.setBackground(new java.awt.Color(51, 51, 51));
        btnEliminarTodos.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnEliminarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarTodos.setText("Eliminar Todos");
        btnEliminarTodos.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 0, 153)));
        btnEliminarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTodosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout panelMenuArribaLayout = new javax.swing.GroupLayout(panelMenuArriba);
        panelMenuArriba.setLayout(panelMenuArribaLayout);
        panelMenuArribaLayout.setHorizontalGroup(
            panelMenuArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuArribaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIdCarton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdCarton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearCarton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCreado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(btnLimpiarCartones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEliminarId, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarUno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarTodos)
                .addGap(181, 181, 181)
                .addComponent(lblEliminado)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        panelMenuArribaLayout.setVerticalGroup(
            panelMenuArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuArribaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMenuArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdCarton)
                    .addComponent(txtIdCarton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearCarton)
                    .addComponent(btnLimpiarCartones)
                    .addComponent(jLabel1)
                    .addComponent(txtEliminarId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarUno)
                    .addComponent(btnEliminarTodos)
                    .addComponent(jLabel2)
                    .addComponent(lblCreado)
                    .addComponent(lblEliminado))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(panelMenuArriba, java.awt.BorderLayout.NORTH);

        panelTombolaContainer.setBackground(new java.awt.Color(51, 51, 51));
        panelTombolaContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelTombolaContainer.setForeground(new java.awt.Color(51, 51, 51));
        panelTombolaContainer.setName("panelTombolaContainer"); // NOI18N
        panelTombolaContainer.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelTombolaContainer, java.awt.BorderLayout.SOUTH);

        scrollCartones.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCartones.setViewportView(panelCartonesContainer);

        panelCartonesContainer.setBackground(new java.awt.Color(51, 51, 51));
        panelCartonesContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5));
        panelCartonesContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout panelCartonesContainerLayout = new javax.swing.GroupLayout(panelCartonesContainer);
        panelCartonesContainer.setLayout(panelCartonesContainerLayout);
        panelCartonesContainerLayout.setHorizontalGroup(
            panelCartonesContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 935, Short.MAX_VALUE)
        );
        panelCartonesContainerLayout.setVerticalGroup(
            panelCartonesContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
        );

        scrollCartones.setViewportView(panelCartonesContainer);

        getContentPane().add(scrollCartones, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        controlador.reiniciarJuego();
        lblUltimoNumero.setText("Último número: -");
        panelEstadoGanadores.setEstadoGanador(false);
        btnExtraerNumero.setEnabled(true);
        panelTombola.reiniciar();
        panelCartones.refrescarCartones();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        detenerParpadeoVerificar();
        var ganadores = controlador.obtenerGanadores();
        if (ganadores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ganadores todavía.");
            return;
        }

        StringBuilder sb = new StringBuilder("Lista de ganadores:\n\n");
        for (var c : ganadores) {
            sb.append("- Cartón ID: ").append(c.getId()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());

        for (var c : ganadores) {
            new WinnerPanel(this, c, controlador).setVisible(true);
        }
    }//GEN-LAST:event_btnVerificarActionPerformed
    
    private void refrescarCartones() {
    panelCartones.refrescarCartones();
}
    
    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        this.dispose();
        Inicio sw = new Inicio();
        sw.setVisible(true);
    }//GEN-LAST:event_btnVolverMenuActionPerformed

    private void btnExtraerNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtraerNumeroActionPerformed

    if (controlador.getCartones().isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "Debe crear al menos un cartón antes de extraer números."
        );
        return;
    }


    int numero = controlador.extraerSiguienteNumero(llenadoAutomatico);
    if (numero == -1) {
        JOptionPane.showMessageDialog(this, "No hay más números disponibles.");
        return;
    }


    lblUltimoNumero.setText("Último número: " + numero);


    panelTombola.agregarNumero(numero);


    if (llenadoAutomatico) {
        controlador.marcarNumeroEnCartones(numero); 
    }
 


    panelCartones.refrescarCartones();


    var ganadores = controlador.obtenerGanadores();

    if (ganadores.isEmpty()) {
        panelEstadoGanadores.setEstadoGanador(false);
        btnExtraerNumero.setEnabled(true);
    } else {
        panelEstadoGanadores.setEstadoGanador(true);
        btnExtraerNumero.setEnabled(false);
        iniciarParpadeoVerificar();
    }
    }//GEN-LAST:event_btnExtraerNumeroActionPerformed

    private void btnEliminarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodosActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Seguro que desea eliminar TODOS los cartones?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarTodosCartones();
            refrescarCartones();
        }
    }//GEN-LAST:event_btnEliminarTodosActionPerformed

    private void btnEliminarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUnoActionPerformed
         String id = txtEliminarId.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un ID válido.");
            return;
        }

        Carton encontrado = controlador.buscarCartonPorId(id);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(this,
                    "No existe un cartón con ese ID.");
            return;
        }

        controlador.eliminarCartonPorId(id);
        panelCartones.refrescarCartones();

        lblEliminado.setText("ELIMINADO");
        lblEliminado.setForeground(new java.awt.Color(255, 0, 0)); 
        lblEliminado.setVisible(true);
        new javax.swing.Timer(1500, e -> lblEliminado.setVisible(false)).start();
    }//GEN-LAST:event_btnEliminarUnoActionPerformed

    private void btnLimpiarCartonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCartonesActionPerformed
        if (JOptionPane.showConfirmDialog(this,
            "¿Seguro que desea limpiar todos los cartones?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            controlador.limpiarCartones();
            panelCartones.refrescarCartones();
        }
    }//GEN-LAST:event_btnLimpiarCartonesActionPerformed

    private void btnCrearCartonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCartonActionPerformed
       
    String id = txtIdCarton.getText().trim();

    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese un ID válido.");
        return;
    }

    boolean creado = false;

    if (llenadoAutomatico) {

        Carton c = controlador.crearCartonAutomatico(id);

        if (c == null) {
            JOptionPane.showMessageDialog(this,
                "Ya existe un cartón con ese ID.");
            return;
        }

        creado = true;

    } else {

        LlenadoManualDialog dlg = new LlenadoManualDialog(this, controlador, id);
        dlg.setVisible(true);

        if (!dlg.fueCreado()) return; 
        creado = true;
    }

    if (creado) {
        lblCreado.setText("✓");
        lblCreado.setForeground(new java.awt.Color(0, 255, 0));
        lblCreado.setVisible(true);

        new javax.swing.Timer(1500, e -> lblCreado.setVisible(false)).start();
    }

    panelCartones.refrescarCartones();
        
    }//GEN-LAST:event_btnCrearCartonActionPerformed

    private void btnMarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarActionPerformed
         String txt = labelNumeroManual.getText().trim();
    if (txt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese un número para marcar.");
        return;
    }

    int numero;

    try {
        numero = Integer.parseInt(txt);
    } catch (NumberFormatException e){
        JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
        return;
    }


    if (!controlador.numeroHaSalido(numero)) {
        JOptionPane.showMessageDialog(this, 
           "Ese número NO ha salido en la tómbola. No se puede marcar.");
        return;
    }


    controlador.marcarNumeroEnCartones(numero);

    panelCartones.refrescarCartones();
    labelNumeroManual.setText("");

    var ganadores = controlador.obtenerGanadores();

    if (ganadores.isEmpty()) {
        panelEstadoGanadores.setEstadoGanador(false);
        btnExtraerNumero.setEnabled(true);
    } else {
        panelEstadoGanadores.setEstadoGanador(true);
        btnExtraerNumero.setEnabled(false);
        iniciarParpadeoVerificar();
    }
    }//GEN-LAST:event_btnMarcarActionPerformed

   private void iniciarParpadeoVerificar() {
    if (timerParpadeo != null && timerParpadeo.isRunning()) return;

    timerParpadeo = new Timer(250, e -> {
        estadoParpadeo = !estadoParpadeo;

        if (estadoParpadeo) {
            btnVerificar.setBackground(new Color(0, 255, 120));     
            btnVerificar.setForeground(Color.WHITE);
        } else {
            btnVerificar.setBackground(new Color(0, 120, 60));     
            btnVerificar.setForeground(Color.WHITE);
        }
    });

    timerParpadeo.start();
}

   private void detenerParpadeoVerificar() {
    if (timerParpadeo != null) {
        timerParpadeo.stop();
        timerParpadeo = null;
    }


    btnVerificar.setBackground(new Color(51, 51, 51));
    btnVerificar.setForeground(Color.WHITE);
}

    
    public void actualizarEstadoGanadores() {
        var ganadores = controlador.obtenerGanadores();

        if (ganadores.isEmpty()) {
            panelEstadoGanadores.setEstadoGanador(false);
            btnExtraerNumero.setEnabled(true);
        } else {
            panelEstadoGanadores.setEstadoGanador(true);
            btnExtraerNumero.setEnabled(false);
        }
    }
    
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearCarton;
    private javax.swing.JButton btnEliminarTodos;
    private javax.swing.JButton btnEliminarUno;
    private javax.swing.JButton btnExtraerNumero;
    private javax.swing.JButton btnLimpiarCartones;
    private javax.swing.JButton btnMarcar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField labelNumeroManual;
    private javax.swing.JLabel lblCreado;
    private javax.swing.JLabel lblEliminado;
    private javax.swing.JLabel lblIdCarton;
    private javax.swing.JLabel lblModoJuego;
    private javax.swing.JLabel lblUltimoNumero;
    private javax.swing.JPanel panelCampoManual;
    private javax.swing.JPanel panelCartonesContainer;
    private javax.swing.JPanel panelEstadoContainer;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMenuArriba;
    private javax.swing.JPanel panelSlot;
    private javax.swing.JPanel panelTombolaContainer;
    private javax.swing.JScrollPane scrollCartones;
    private javax.swing.JTextField txtEliminarId;
    private javax.swing.JTextField txtIdCarton;
    // End of variables declaration//GEN-END:variables
}