
package Vista;

import Controlador.JuegoController;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Modelo.Carton;
import Modelo.ModoJuego;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import Vista.LlenadoManualDialog;
import Vista.Inicio;
import Vista.WinnerPanel;
import Vista.PanelFondo;


import Modelo.PanelCartones;

import Vista.TombolaGUI;
import Vista.PanelGanadorGUI;

public class JuegoGUI extends javax.swing.JFrame {
   
     private JuegoController controller;
    private boolean llenadoAutomatico;
    private PanelCartones panelCartones;
    private TombolaGUI panelTombola;
    

    private PanelEstadoGanadores panelEstadoGanadores;
    
    public JuegoGUI(JuegoController controller,
                      boolean marcadoManual,
                      boolean llenadoAutomatico,
                      ModoJuego modoJuego) {

    initComponents();
    this.controller = controller;
    this.llenadoAutomatico = llenadoAutomatico;
    setLocationRelativeTo(null);
    
    panelMenu.setLayout(new BorderLayout());
    PanelFondo fondoMenu = new PanelFondo("C:\\Users\\Valdelomaar\\Documents\\ProyectoFinalBoss_Limpio\\ProyectoFinalBoss\\src\\images\\mesa_morada.png");
     fondoMenu.setLayout(new BorderLayout());  
    panelMenu.add(fondoMenu, BorderLayout.CENTER);

   

    PanelFondo fondo = new PanelFondo("C:\\Users\\Valdelomaar\\Documents\\ProyectoFinalBoss_Limpio\\ProyectoFinalBoss\\src\\images\\mesa_morada.png");
    fondo.setLayout(new BorderLayout());


    panelCartones = new PanelCartones(controller);
    panelCartones.setOpaque(false);
    panelCartones.setModoMarcadoManual(marcadoManual);

  
    fondo.add(panelCartones, BorderLayout.CENTER);


    scrollCartones.setViewportView(fondo);


    panelCartones.refrescarCartones();

    panelEstadoGanadores = new PanelEstadoGanadores();
    panelEstadoContainer.setLayout(new BorderLayout());
    panelEstadoContainer.add(panelEstadoGanadores, BorderLayout.CENTER);
    panelEstadoContainer.revalidate();
    panelEstadoContainer.repaint();
    panelEstadoGanadores.setEstadoGanador(false);


    panelTombola = new PanelTombolaUI();
    panelTombolaContainer.setLayout(new BorderLayout());
    panelTombolaContainer.add(panelTombola, BorderLayout.CENTER);


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


    String textoModo = switch (modoJuego) {
        case CUATROESQUINAS -> "Modo de juego: CUATRO ESQUINAS";
        case CARTONLLENO    -> "Modo de juego: CARTÓN LLENO";
        default              -> "Modo de juego: NORMAL";
    };
    lblModoJuego.setText(textoModo);

    lblCreado.setVisible(false);
    lblUltimoNumero.setText("Último número: -");

    controller.setMarcadoAutomatico(!marcadoManual);
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        btnExtraerNumero = new javax.swing.JButton();
        lblModoJuego = new javax.swing.JLabel();
        btnReiniciar = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        btnVolverMenu = new javax.swing.JButton();
        panelSlot = new javax.swing.JPanel();
        lblUltimoNumero = new javax.swing.JLabel();
        panelEstadoContainer = new javax.swing.JPanel();
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

        panelMenu.setBackground(new java.awt.Color(102, 102, 102));
        panelMenu.setForeground(new java.awt.Color(204, 204, 204));
        panelMenu.setName("panelMenu"); // NOI18N
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 400));

        btnExtraerNumero.setBackground(new java.awt.Color(51, 51, 51));
        btnExtraerNumero.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnExtraerNumero.setForeground(new java.awt.Color(255, 255, 255));
        btnExtraerNumero.setText("Extraer Numero");
        btnExtraerNumero.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 204, 0)));
        btnExtraerNumero.setOpaque(true);
        btnExtraerNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtraerNumeroActionPerformed(evt);
            }
        });

        lblModoJuego.setBackground(new java.awt.Color(51, 51, 51));
        lblModoJuego.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblModoJuego.setForeground(new java.awt.Color(255, 255, 255));
        lblModoJuego.setText("Modo de juego: -");
        lblModoJuego.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 3));
        lblModoJuego.setOpaque(true);

        btnReiniciar.setBackground(new java.awt.Color(51, 51, 51));
        btnReiniciar.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnReiniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnReiniciar.setText("Reiniciar Juego");
        btnReiniciar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 3));
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnVerificar.setBackground(new java.awt.Color(51, 51, 51));
        btnVerificar.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnVerificar.setForeground(new java.awt.Color(255, 255, 255));
        btnVerificar.setText("Verificar Ganadores");
        btnVerificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 3));
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        btnVolverMenu.setBackground(new java.awt.Color(51, 51, 51));
        btnVolverMenu.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnVolverMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverMenu.setText("Volver al Menu");
        btnVolverMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 3));
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });

        panelSlot.setBackground(new java.awt.Color(51, 51, 51));
        panelSlot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 197, 66), 4));

        lblUltimoNumero.setBackground(new java.awt.Color(255, 255, 255));
        lblUltimoNumero.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelSlotLayout = new javax.swing.GroupLayout(panelSlot);
        panelSlot.setLayout(panelSlotLayout);
        panelSlotLayout.setHorizontalGroup(
            panelSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUltimoNumero)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSlotLayout.setVerticalGroup(
            panelSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSlotLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblUltimoNumero)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panelEstadoContainer.setOpaque(false);

        javax.swing.GroupLayout panelEstadoContainerLayout = new javax.swing.GroupLayout(panelEstadoContainer);
        panelEstadoContainer.setLayout(panelEstadoContainerLayout);
        panelEstadoContainerLayout.setHorizontalGroup(
            panelEstadoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelEstadoContainerLayout.setVerticalGroup(
            panelEstadoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addComponent(panelEstadoContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnVolverMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(btnReiniciar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExtraerNumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblModoJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(btnExtraerNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModoJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelEstadoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.WEST);

        panelMenuArriba.setBackground(new java.awt.Color(51, 51, 51));
        panelMenuArriba.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelMenuArriba.setForeground(new java.awt.Color(204, 204, 204));
        panelMenuArriba.setPreferredSize(new java.awt.Dimension(50, 65));

        lblIdCarton.setForeground(new java.awt.Color(255, 255, 255));
        lblIdCarton.setText("ID Cartón:");

        btnCrearCarton.setText("Crear Cartón");
        btnCrearCarton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCartonActionPerformed(evt);
            }
        });

        btnLimpiarCartones.setText("Limpiar Cartones");
        btnLimpiarCartones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCartonesActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Eliminar ID");

        btnEliminarUno.setText("Eliminar Uno");
        btnEliminarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUnoActionPerformed(evt);
            }
        });

        btnEliminarTodos.setText("Eliminar Todos");
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
                .addContainerGap(179, Short.MAX_VALUE))
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
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(panelMenuArriba, java.awt.BorderLayout.NORTH);

        panelTombolaContainer.setBackground(new java.awt.Color(51, 51, 51));
        panelTombolaContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelTombolaContainer.setForeground(new java.awt.Color(51, 51, 51));
        panelTombolaContainer.setName("panelTombolaContainer"); // NOI18N

        javax.swing.GroupLayout panelTombolaContainerLayout = new javax.swing.GroupLayout(panelTombolaContainer);
        panelTombolaContainer.setLayout(panelTombolaContainerLayout);
        panelTombolaContainerLayout.setHorizontalGroup(
            panelTombolaContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        panelTombolaContainerLayout.setVerticalGroup(
            panelTombolaContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

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
            .addGap(0, 880, Short.MAX_VALUE)
        );
        panelCartonesContainerLayout.setVerticalGroup(
            panelCartonesContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
        );

        scrollCartones.setViewportView(panelCartonesContainer);

        getContentPane().add(scrollCartones, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        controller.reiniciarJuego();
        lblUltimoNumero.setText("Último número: -");

        panelEstadoGanadores.setEstadoGanador(false);
        btnExtraerNumero.setEnabled(true);

        panelTombola.reiniciar();
        panelCartones.refrescarCartones();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
      var ganadores = controller.obtenerGanadores();
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
        new WinnerPanel(this, c, controller).setVisible(true);
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
          if (controller.getCartones().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe crear al menos un cartón antes de extraer números.");
            return;
        }

        int numero = controller.extraerSiguienteNumero();
        if (numero == -1) {
            JOptionPane.showMessageDialog(this, "No hay más números disponibles.");
            return;
        }

        lblUltimoNumero.setText("Último número: " + numero);

        panelTombola.agregarNumero(numero);
        panelCartones.refrescarCartones();

        var ganadores = controller.obtenerGanadores();

        if (ganadores.isEmpty()) {
            panelEstadoGanadores.setEstadoGanador(false);
            btnExtraerNumero.setEnabled(true);
        } else {
            panelEstadoGanadores.setEstadoGanador(true);
            btnExtraerNumero.setEnabled(false);
        }
    }//GEN-LAST:event_btnExtraerNumeroActionPerformed

    private void btnEliminarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodosActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Seguro que desea eliminar TODOS los cartones?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.eliminarTodosCartones();
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

    Carton encontrado = controller.buscarCartonPorId(id);
    if (encontrado == null) {
        JOptionPane.showMessageDialog(this,
                "No existe un cartón con ese ID.");
        return;
    }

    controller.eliminarCartonPorId(id);
    panelCartones.refrescarCartones();

    lblEliminado.setText("ELIMINADO");
    lblEliminado.setForeground(new java.awt.Color(255, 0, 0)); // Rojo
    lblEliminado.setVisible(true);

    new javax.swing.Timer(1500, e -> lblEliminado.setVisible(false)).start();
    }//GEN-LAST:event_btnEliminarUnoActionPerformed

    private void btnLimpiarCartonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCartonesActionPerformed
        if (JOptionPane.showConfirmDialog(this,
            "¿Seguro que desea limpiar todos los cartones?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        controller.limpiarCartones();
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
            Carton c = controller.crearCartonAutomatico(id);
            if (c == null) {
                JOptionPane.showMessageDialog(this,
                    "Ya existe un cartón con ese ID.");
                return;
            }
            creado = true;
        } else {
            LlenadoManualDialog dlg = new LlenadoManualDialog(this, controller, id);
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

    public void actualizarEstadoGanadores() {
        var ganadores = controller.obtenerGanadores();

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
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCreado;
    private javax.swing.JLabel lblEliminado;
    private javax.swing.JLabel lblIdCarton;
    private javax.swing.JLabel lblModoJuego;
    private javax.swing.JLabel lblUltimoNumero;
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