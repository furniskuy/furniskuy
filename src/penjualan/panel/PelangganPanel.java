/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package penjualan.panel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import penjualan.entity.BarangEntity;
import penjualan.entity.PelangganEntity;
import penjualan.implement.BarangImplement;
import penjualan.implement.PelangganImplement;
import penjualan.interfc.PelangganInterface;
import penjualan.interfc.TableDataInterface;

/**
 *
 * @author fadildesk
 */
public class PelangganPanel extends javax.swing.JPanel {
    List<TableDataInterface> listPelanggan = new ArrayList();

    PelangganEntity emptyPelanggan = new PelangganEntity();
    PelangganEntity selectedPelanggan = null;
    PelangganImplement pelangganImplement = new PelangganImplement();
    
    String jenisKelamin = "";

    /**
     * Creates new form PegawaiView
     */
    public PelangganPanel() {
        initComponents();
        getListPelanggan();
        
        buttonGroup1.add(lkRadio);
        buttonGroup1.add(prRadio);

        tablePelanggan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tablePelanggan.getSelectedRow() != -1) {
                    if (tablePelanggan.getValueAt(tablePelanggan.getSelectedRow(), 0) != null) {
                        int indexSelected = Integer.parseInt(tablePelanggan.getValueAt(tablePelanggan.getSelectedRow(), 0).toString()
                        );
                        setFieldValueAsSelectedRow(indexSelected - 1);
                        simpanButton.setEnabled(false);

                    }
                }
            }
        });
    }
    
    void getListPelanggan() {
        DefaultTableModel dtm = new DefaultTableModel(null, emptyPelanggan.getHeader());
        try {
            listPelanggan = pelangganImplement.getAll();
            for (int i = 0; i < listPelanggan.size(); i++) {
                dtm.addRow(listPelanggan.get(i).toTableRowWithIndex(i));
            }
            tablePelanggan.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mendapat data dari database \n" + e.getMessage());
        }
    }

    void clearField() {
        this.idPegawaiField.setText("");
        this.namaPegawaiField.setText("");
        this.alamatField.setText("");
        this.noTeleponField.setText("");
        jenisKelamin = "";
        lkRadio.setSelected(false);
        prRadio.setSelected(false);
    }
   
    boolean fieldIsEmpty() {
        return idPegawaiField.getText().isEmpty() || namaPegawaiField.getText().isEmpty() || jenisKelamin.isEmpty() || alamatField.getText().isEmpty() || noTeleponField.getText().isEmpty();
    }
    
    void setFieldValueAsSelectedRow(int row) {
        selectedPelanggan =((PelangganEntity) listPelanggan.get(row));
        this.idPegawaiField.setText(selectedPelanggan.getIdPelanggan());
        this.namaPegawaiField.setText(selectedPelanggan.getNama());
        this.alamatField.setText(selectedPelanggan.getAlamat());
        this.noTeleponField.setText(selectedPelanggan.getNoTelp());
        jenisKelamin = selectedPelanggan.getJenisKelamin();
        if (selectedPelanggan.getJenisKelamin().equals("L")) {
            lkRadio.setSelected(true);
        }
        if (selectedPelanggan.getJenisKelamin().equals("P")) {
            prRadio.setSelected(true);
        }
    }
    
    void simpanData() {
        if (!fieldIsEmpty()) {
            PelangganEntity pelanggan = new PelangganEntity();
            pelanggan.setNama(namaPegawaiField.getText());
            pelanggan.setJenisKelamin(jenisKelamin);
            pelanggan.setAlamat(alamatField.getText());
            pelanggan.setNoTelp(noTeleponField.getText());

            try {
                pelangganImplement.insert(pelanggan);
                JOptionPane.showMessageDialog(this, "Berhasil menyimpan data ke database");
                getListPelanggan();
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data ke database \n" + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong");
        }
    }
    void updateData() {
        if (selectedPelanggan != null) {
            if (!fieldIsEmpty()) {
                selectedPelanggan.setNama(namaPegawaiField.getText());
                selectedPelanggan.setJenisKelamin(jenisKelamin);
                selectedPelanggan.setAlamat(alamatField.getText());
                selectedPelanggan.setNoTelp(noTeleponField.getText());
                selectedPelanggan.setJenisKelamin(jenisKelamin);

                try {
                    pelangganImplement.update(selectedPelanggan);
                    JOptionPane.showMessageDialog(this, "Berhasil menngubah data dari database");
                    getListPelanggan();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Gagal mengubah data dari database \n" + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak boleh kosong");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mohon pilih barang dalam tabel untuk melakukan penghapusan");
        }
    }

    void hapusData() {
        if (selectedPelanggan != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus barang  " + selectedPelanggan.getIdPelanggan(), "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                try {
                    pelangganImplement.delete(selectedPelanggan.getIdPelanggan());
                    JOptionPane.showMessageDialog(this, "Berhasil menghapus data dari database");
                    getListPelanggan();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data dari database \n" + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mohon pilih barang dalam tabel untuk melakukan penghapusan");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePelanggan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        simpanButton = new javax.swing.JButton();
        ubahButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        noTeleponField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        alamatField = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        lkRadio = new javax.swing.JRadioButton();
        prRadio = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        namaPegawaiField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        idPegawaiField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        clearFieldButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        tablePelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablePelanggan);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(200, 139));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        simpanButton.setText("Simpan");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });
        jPanel5.add(simpanButton, new java.awt.GridBagConstraints());

        ubahButton.setText("Ubah");
        ubahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahButtonActionPerformed(evt);
            }
        });
        jPanel5.add(ubahButton, new java.awt.GridBagConstraints());

        hapusButton.setText("Hapus");
        hapusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusButtonMouseClicked(evt);
            }
        });
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });
        jPanel5.add(hapusButton, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel5, new java.awt.GridBagConstraints());

        jLabel6.setText("No Tlp");

        alamatField.setColumns(20);
        alamatField.setRows(5);
        jScrollPane3.setViewportView(alamatField);

        jLabel7.setText("Alamat");

        lkRadio.setText("Laki - Laki");
        lkRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lkRadioActionPerformed(evt);
            }
        });

        prRadio.setText("Perempuan");
        prRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prRadioActionPerformed(evt);
            }
        });

        jLabel8.setText("Jenis Kelamin");

        jLabel9.setText("Nama");

        idPegawaiField.setEditable(false);

        jLabel10.setText("ID Pelanggan");

        clearFieldButton.setText("Clear Field");
        clearFieldButton.setToolTipText("");
        clearFieldButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearFieldButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(noTeleponField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 223, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lkRadio)
                                .addGap(37, 37, 37)
                                .addComponent(prRadio))
                            .addComponent(jScrollPane3)
                            .addComponent(namaPegawaiField)
                            .addComponent(idPegawaiField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearFieldButton)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(idPegawaiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearFieldButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(namaPegawaiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lkRadio)
                    .addComponent(prRadio))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noTeleponField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Pelanggan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(24, 24, 24)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        simpanData();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void ubahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahButtonActionPerformed
       updateData();
    }//GEN-LAST:event_ubahButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        hapusData();
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void lkRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lkRadioActionPerformed
        jenisKelamin = "L";
    }//GEN-LAST:event_lkRadioActionPerformed

    private void hapusButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusButtonMouseClicked
         clearField();
    }//GEN-LAST:event_hapusButtonMouseClicked

    private void prRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prRadioActionPerformed
        jenisKelamin = "P";
    }//GEN-LAST:event_prRadioActionPerformed

    private void clearFieldButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearFieldButtonMouseClicked
        clearField();        // TODO add your handling code here:
    }//GEN-LAST:event_clearFieldButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamatField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearFieldButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JTextField idPegawaiField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton lkRadio;
    private javax.swing.JTextField namaPegawaiField;
    private javax.swing.JTextField noTeleponField;
    private javax.swing.JRadioButton prRadio;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTable tablePelanggan;
    private javax.swing.JButton ubahButton;
    // End of variables declaration//GEN-END:variables
}
