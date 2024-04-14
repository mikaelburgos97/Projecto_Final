/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.ReservaDAO;
import models.ReservaDTO;
import models.utils.DateHandler;

/**
 *
 * @author mikae
 */
public class ReservaCRUD extends javax.swing.JFrame {
    
    ReservaDAO reservaDAO = new ReservaDAO();
    DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Creates new form ReservaCRUD
     */
    public ReservaCRUD() {
        initComponents();
        agregarListenersCampos();
        
        jButton3.setEnabled(false);
        // Agregar event listeners a los botones
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            agregar();
        }
    });

    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            actualizar();
        }
    });

    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            eliminar();
        }
    });
    
        jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelar();
        }
    });

    
    jTable1.getSelectionModel().addListSelectionListener(event -> {
    if (!event.getValueIsAdjusting()) {
        boolean isRowSelected = jTable1.getSelectedRow() != -1;
        jButton2.setEnabled(isRowSelected);
        jButton3.setEnabled(isRowSelected);
        jButton4.setEnabled(isRowSelected);
        
        if (isRowSelected) {
            int selectedRow = jTable1.getSelectedRow();
            jTextField2.setText(jTable1.getValueAt(selectedRow, 1).toString());
            jTextField3.setText(jTable1.getValueAt(selectedRow, 2).toString());
            jTextField4.setText(jTable1.getValueAt(selectedRow, 3).toString());
            jTextField5.setText(jTable1.getValueAt(selectedRow, 4).toString());
            jTextField7.setText(jTable1.getValueAt(selectedRow, 5).toString());
            
            verificarCampos(); // Llamada al método verificarCampos() después de llenar los campos
        } else {
            limpiarCamps();
            jButton1.setEnabled(true);
        }
    }
});
    
    

    

        
        
        listarReservas();
    }
    
    
    
        private void listarReservas() {
        List<ReservaDTO> reservas = reservaDAO.listar();
        modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de llenarla
        Object[] objeto = new Object[6];
        for (int i = 0; i < reservas.size(); i++) {
            objeto[0] = reservas.get(i).getId();
            objeto[1] = reservas.get(i).getFecha_peticion();
            objeto[2] = reservas.get(i).getFecha_entrega();
            objeto[3] = reservas.get(i).getLibro_id();
            objeto[4] = reservas.get(i).getCliente_id();
            objeto[5] = reservas.get(i).getEstado();
            modelo.addRow(objeto);
        }
        jTable1.setModel(modelo);
    }
        
        private void agregar() {
            ReservaDTO reserva = new ReservaDTO();

            // Obtener los valores de los campos de texto
            String fechaPeticion = jTextField2.getText();
            String fechaEntrega = jTextField3.getText();
            int libro = Integer.parseInt(jTextField4.getText());
            int cliente = Integer.parseInt(jTextField5.getText());
            String estado = jTextField7.getText();

            // Asignar los valores a la instancia de ReservaDTO
            reserva.setFecha_peticion(fechaPeticion);
            reserva.setFecha_entrega(fechaEntrega);
            reserva.setLibro_id(libro);
            reserva.setCliente_id(cliente);
            reserva.setEstado(estado);

            int resultado = reservaDAO.agregar(reserva);
            if (resultado == 1) {
                listarReservas();
                
            }
            limpiarCamps();
        }
           
            private void actualizar() {
                int fila = jTable1.getSelectedRow();
                if (fila != -1) {
                    ReservaDTO reserva = new ReservaDTO();

                    // Obtener los valores de los campos de texto
                    reserva.setFecha_peticion(jTextField2.getText());
                    reserva.setFecha_entrega(jTextField3.getText());
                    reserva.setLibro_id(Integer.parseInt(jTextField4.getText()));
                    reserva.setCliente_id(Integer.parseInt(jTextField5.getText()));
                    reserva.setEstado(jTextField7.getText());
                    
                    System.out.println(jTextField7.getText());

                    // Obtiene el ID desde la tabla (asumiendo que el ID está en la columna 0)
                    reserva.setId((int) jTable1.getValueAt(fila, 0));

                    int resultado = reservaDAO.actualizar(reserva);
                    if (resultado == 1) {
                        listarReservas(); // Refresca la lista en la tabla
                        JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar la reserva.");
                    }
                    deshabilitarBotones();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para actualizar.");
                }
                limpiarCamps();
                jButton1.setEnabled(false);
            }


        
        
                private void eliminar() {
                    int fila = jTable1.getSelectedRow();
                    if (fila >= 0) {
                        int id = (int) modelo.getValueAt(fila, 0);
                        int resultado = reservaDAO.eliminar(id);
                        if (resultado == 1) {
                            listarReservas();
                        }
                        deshabilitarBotones();
                    }
                }
                
                private void limpiarCamps(){
                    // Limpiar los campos de texto después de agregar una reserva
                    jTextField2.setText("");
                    jTextField3.setText("");
                    jTextField4.setText("");
                    jTextField5.setText("");
                    jTextField7.setText("");
                }

                
        private void cancelar(){
           limpiarCamps();
           jTable1.getSelectionModel().clearSelection();
           deshabilitarBotones();
       }
        
        private void verificarCampos() {
    boolean todosCamposLlenos = !jTextField2.getText().isEmpty() &&
                                 !jTextField3.getText().isEmpty() &&
                                 !jTextField4.getText().isEmpty() &&
                                 !jTextField5.getText().isEmpty() &&
                                 !jTextField7.getText().isEmpty();
    
    boolean algunCampoConTexto = !jTextField2.getText().isEmpty() ||
                                  !jTextField3.getText().isEmpty() ||
                                  !jTextField4.getText().isEmpty() ||
                                  !jTextField5.getText().isEmpty() ||
                                  !jTextField7.getText().isEmpty();
    
    int selectedRow = jTable1.getSelectedRow();
    boolean esFilaSeleccionada = selectedRow != -1;
    boolean esFilaDiferente = esFilaSeleccionada &&
                              (!jTextField2.getText().equals(jTable1.getValueAt(selectedRow, 1).toString()) ||
                               !jTextField3.getText().equals(jTable1.getValueAt(selectedRow, 2).toString()) ||
                               !jTextField4.getText().equals(jTable1.getValueAt(selectedRow, 3).toString()) ||
                               !jTextField5.getText().equals(jTable1.getValueAt(selectedRow, 4).toString()) ||
                               !jTextField7.getText().equals(jTable1.getValueAt(selectedRow, 5).toString()));
    
    jButton1.setEnabled(todosCamposLlenos && (!esFilaSeleccionada || esFilaDiferente));
    jButton4.setEnabled(algunCampoConTexto);
}
        
        private void agregarListenersCampos() {
        DocumentListener listener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            verificarCampos();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            verificarCampos();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            verificarCampos();
        }
    };
    
    jTextField2.getDocument().addDocumentListener(listener);
    jTextField3.getDocument().addDocumentListener(listener);
    jTextField4.getDocument().addDocumentListener(listener);
    jTextField5.getDocument().addDocumentListener(listener);
    jTextField7.getDocument().addDocumentListener(listener);
}
        
        
     private void deshabilitarBotones() {
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
    }       
             
             
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Fecha de Peticion");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha de Entrega");

        jLabel4.setText("ID del Libro");

        jLabel5.setText("ID del Cliente");

        jLabel7.setText("Estado de Entrega");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha de Peticion", "Fecha de Entrega", "ID/Nombre Libro", "ID/Nombre Cliente", "Estado de Entrega"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Agregar");
        jButton1.setEnabled(false);

        jButton2.setText("Actualizar");
        jButton2.setEnabled(false);

        jButton3.setText("Borrar");
        jButton3.setEnabled(false);

        jButton4.setText("Cancelar");
        jButton4.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(112, 112, 112)
                        .addComponent(jButton2)
                        .addGap(114, 114, 114)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(66, 66, 66))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReservaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservaCRUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables


}
