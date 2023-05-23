package penjualan.panel;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import penjualan.entity.BarangEntity;
import penjualan.implement.BarangImplement;
import penjualan.koneksi.KoneksiSql;
import penjualan.interfc.TableDataInterface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author fadildesk
 */
public class BarangPanel extends javax.swing.JPanel {

    KoneksiSql koneksiSql = new KoneksiSql();
    List<TableDataInterface> listBarang = new ArrayList();

    BarangEntity emptyBarangEntity = new BarangEntity();
    BarangEntity selectedBarangEntity = null;
    BarangImplement barangImplement = new BarangImplement();

    /**
     * Creates new form BarangView
     */
    public BarangPanel() {
        initComponents();
        getListBarang();

        tableBarang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tableBarang.getSelectedRow() != -1) {
                    if (tableBarang.getValueAt(tableBarang.getSelectedRow(), 0) != null) {
                        int indexSelected = Integer.parseInt(tableBarang.getValueAt(tableBarang.getSelectedRow(), 0).toString()
                        );
                        setFieldValueAsSelectedRow(indexSelected - 1);
                        simpanButton.setEnabled(false);

                    }
                }
            }
        });
    }

    void getListBarang() {
        DefaultTableModel dtm = new DefaultTableModel(null, emptyBarangEntity.getHeader());
        try {
            listBarang = barangImplement.getAll();
            for (int i = 0; i < listBarang.size(); i++) {
                dtm.addRow(listBarang.get(i).toTableRowWithIndex(i));
            }
            tableBarang.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mendapat data dari database \n" + e.getMessage());
        }
    }

    void setFieldValueAsSelectedRow(int index) {
        selectedBarangEntity = ((BarangEntity) listBarang.get(index));

        kodeBarangField.setText(selectedBarangEntity.getKodeBarang());
        kodeBarangField.setEnabled(false);

        namaBarangField.setText(selectedBarangEntity.getNamaBarang());
        jumlahField.setText("" + selectedBarangEntity.getJumlah() + "");
        hargaField.setText("" + selectedBarangEntity.getHarga() + "");
    }

    void clearField() {
        selectedBarangEntity = null;
        kodeBarangField.setText("");
        kodeBarangField.setEnabled(true);

        namaBarangField.setText("");
        jumlahField.setText("");
        hargaField.setText("");

        simpanButton.setEnabled(true);
    }

    boolean fieldIsEmpty() {
        return namaBarangField.getText().isEmpty() || jumlahField.getText().isEmpty() || hargaField.getText().isEmpty();
    }

    String addSingleQuote(String str) {
        return "'" + str + "'";
    }

    void simpanData() {
        if (!fieldIsEmpty() && !(kodeBarangField.getText().isEmpty())) {
            String join = String.join(", ", emptyBarangEntity.getNamaKolom());
            BarangEntity barang = new BarangEntity();
            barang.setKodeBarang(kodeBarangField.getText());
            barang.setNamaBarang(namaBarangField.getText());
            barang.setJumlah(jumlahField.getText());
            barang.setHarga(hargaField.getText());

            try {
                barangImplement.insert(barang);
                JOptionPane.showMessageDialog(this, "Berhasil menyimpan data ke database");
                getListBarang();
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data ke database \n" + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong");
        }
    }
    void updateData() {
        if (selectedBarangEntity != null) {
            if (!fieldIsEmpty() && !(kodeBarangField.getText().isEmpty())) {
                selectedBarangEntity.setNamaBarang(namaBarangField.getText());
                selectedBarangEntity.setJumlah(jumlahField.getText());
                selectedBarangEntity.setHarga(hargaField.getText());
                selectedBarangEntity.setKodeBarang(kodeBarangField.getText());

                try {
                    barangImplement.update(selectedBarangEntity);
                    JOptionPane.showMessageDialog(this, "Berhasil menngubah data dari database");
                    kodeBarangField.setEnabled(true);
                    getListBarang();
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
        if (selectedBarangEntity != null) {

            int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus barang  " + selectedBarangEntity.getKodeBarang(), "Konfirmasi", JOptionPane.YES_NO_OPTION);

            if (confirm == 0) {
                try {
                    barangImplement.delete(selectedBarangEntity.getKodeBarang());
                    JOptionPane.showMessageDialog(this, "Berhasil menghapus data dari database");
                    kodeBarangField.setEnabled(true);
                    getListBarang();
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

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 139));
        jPanel2.setLayout(new java.awt.GridBagLayout());

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        );

        jLabel13.setText("Kode Barang");

        jLabel14.setText("Nama Barang");

        jLabel15.setText("Jumlah");

        jLabel16.setText("Harga");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
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
            .addGap(0, 180, Short.MAX_VALUE)
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
                .addGap(0, 120, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );
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
