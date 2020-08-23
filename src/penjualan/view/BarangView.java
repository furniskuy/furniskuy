/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import penjualan.entity.BarangModel;
import penjualan.interfc.EntityInterfc;
import penjualan.koneksi.KoneksiSql;

/**
 *
 * @author fadil
 */
public class BarangView extends javax.swing.JFrame {

    KoneksiSql koneksiSql = new KoneksiSql();
    ArrayList<EntityInterfc> listBarang = new ArrayList();
    
    BarangModel emptyBarangModel = new BarangModel();
    BarangModel selectedBarangModel = null;

    /**
     * Creates new form BarangView
     */
    public BarangView() {
        initComponents();
        getListBarang();

        tableBarang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tableBarang.getSelectedRow() != -1) {
                    if (tableBarang.getValueAt(tableBarang.getSelectedRow(), 0) != null) {
                        int indexSelected = Integer.parseInt(tableBarang.getValueAt(tableBarang.getSelectedRow(), 0).toString()
                        );
                    setFieldValueAsSelectedRow(indexSelected - 1);

                    }
                }
            }
        });
    }

    void getListBarang() {
        DefaultTableModel dtm = new DefaultTableModel(null, emptyBarangModel.getHeader());
        try {
            listBarang = koneksiSql.select("SELECT * FROM barang", emptyBarangModel);
            for (int i = 0; i < listBarang.size(); i++) {
                dtm.addRow(listBarang.get(i).toTableRowWithIndex(i));
            }
            tableBarang.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, "Gagal mendapat data dari database \n" + e.getMessage());
        }
    }

    void setFieldValueAsSelectedRow(int index) {
        selectedBarangModel = ((BarangModel) listBarang.get(index));

        kodeBarangField.setText(selectedBarangModel.getKodeBarang());
        kodeBarangField.setEnabled(false);

        namaBarangField.setText(selectedBarangModel.getNamaBarang());
        jumlahField.setText("" + selectedBarangModel.getJumlah() + "");
        hargaField.setText("" + selectedBarangModel.getHarga() + "");
    }

    void clearField() {
        selectedBarangModel = null;
        kodeBarangField.setText("");

        namaBarangField.setText("");
        jumlahField.setText("");
        hargaField.setText("");
    }

    boolean fieldIsEmpty() {
        return namaBarangField.getText().isEmpty() || jumlahField.getText().isEmpty() || hargaField.getText().isEmpty();
    }
    
    String addSingleQuote(String str) {
        return "'" + str + "'";
    }

    void simpanData() {
        if (fieldIsEmpty()) {
            String join = String.join(", ", emptyBarangModel.getNamaKolom());
            String query = "INSERT INTO barang(" + join + ") VALUES(" + 
                   addSingleQuote(namaBarangField.getText()) + ", " +
                   addSingleQuote(kodeBarangField.getText()) + ", " +
                   jumlahField.getText() + ", " +
                   hargaField.getText() +
                    ");";

            try {
                koneksiSql.update(query);
                JOptionPane.showConfirmDialog(this, "Berhasil menyimpan data dari database");
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(this, "Gagal menyimpan data dari database \n" + e.getMessage());
            }

        }
    }
    
    void updateData() {
        if (fieldIsEmpty()) {
            String query = "UPDATE barang SET kode_barang = " + 
                   addSingleQuote(namaBarangField.getText()) + ", nama_barang = " +
                   addSingleQuote(kodeBarangField.getText()) + ", jumlah = " +
                   jumlahField.getText() + ", harga = " +
                   hargaField.getText() + ";" ;

            try {
                koneksiSql.update(query);
                JOptionPane.showConfirmDialog(this, "Berhasil menyimpan data dari database");
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(this, "Gagal menyimpan data dari database \n" + e.getMessage());
            }

        }
    }

    void hapusData() {
        if (selectedBarangModel != null) {

            String query = "DELETE FROM barang WHERE kode_barang = '" + selectedBarangModel.getKodeBarang() + "';";
            try {
                koneksiSql.update(query);
                JOptionPane.showConfirmDialog(this, "Berhasil menghapus data dari database");
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(this, "Gagal menghapus data dari database \n" + e.getMessage());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        simpanButton = new javax.swing.JButton();
        ubahButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        kodeBarangField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        namaBarangField = new javax.swing.JTextField();
        jumlahField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        hargaField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        clearFieldButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 139));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 100, 0};
        jPanel2.setLayout(jPanel2Layout);

        simpanButton.setText("Simpan");
        simpanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpanButtonMouseClicked(evt);
            }
        });
        jPanel2.add(simpanButton, new java.awt.GridBagConstraints());

        ubahButton.setText("Ubah");
        ubahButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ubahButtonMouseClicked(evt);
            }
        });
        jPanel2.add(ubahButton, new java.awt.GridBagConstraints());

        hapusButton.setText("Hapus");
        hapusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusButtonMouseClicked(evt);
            }
        });
        jPanel2.add(hapusButton, new java.awt.GridBagConstraints());

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Jumlah", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableBarang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
        );

        jLabel13.setText("Kode Barang");

        kodeBarangField.setEnabled(false);

        jLabel14.setText("Nama Barang");

        jLabel15.setText("Jumlah");

        jLabel16.setText("Harga");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16))
                    .addGap(56, 56, 56)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(kodeBarangField)
                        .addComponent(namaBarangField)
                        .addComponent(jumlahField)
                        .addComponent(hargaField, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(kodeBarangField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(namaBarangField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jumlahField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(hargaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(24, Short.MAX_VALUE)))
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Barang");

        clearFieldButton.setText("Clear Field");
        clearFieldButton.setToolTipText("");
        clearFieldButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearFieldButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(clearFieldButton)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(300, 300, 300))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(clearFieldButton)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanButtonMouseClicked
        simpanData();
    }//GEN-LAST:event_simpanButtonMouseClicked

    private void ubahButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahButtonMouseClicked
        updateData();
    }//GEN-LAST:event_ubahButtonMouseClicked

    private void hapusButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusButtonMouseClicked
       hapusData();
    }//GEN-LAST:event_hapusButtonMouseClicked

    private void clearFieldButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearFieldButtonMouseClicked
        clearField();        // TODO add your handling code here:
    }//GEN-LAST:event_clearFieldButtonMouseClicked

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
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearFieldButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JTextField hargaField;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlahField;
    private javax.swing.JTextField kodeBarangField;
    private javax.swing.JTextField namaBarangField;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTable tableBarang;
    private javax.swing.JButton ubahButton;
    // End of variables declaration//GEN-END:variables
}
