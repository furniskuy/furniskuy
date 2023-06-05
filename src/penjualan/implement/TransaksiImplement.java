/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import penjualan.entity.BarangEntity;
import penjualan.entity.TransaksiEntity;
import penjualan.interfc.TableDataInterface;
import penjualan.koneksi.KoneksiSql;

/**
 *
 * @author fadil
 */
public class TransaksiImplement {
    public ArrayList<String> viewKdBarang() throws SQLException {
        ArrayList<String> viewNamaBarang = new ArrayList();
        
        try {
            Statement st = KoneksiSql.getKoneksi().createStatement();
            ResultSet rs = st.executeQuery("select kode_barang, nama_barang from barang;");
            
            while(rs.next()) {
                viewNamaBarang.add(rs.getString("kode_barang") + "-" + rs.getString("nama_barang"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewNamaBarang;
    }
    
    public ArrayList<String> viewIdPelanggan() throws SQLException {
        ArrayList<String> viewIdPelanggan = new ArrayList();
        
        try {
            Statement st = KoneksiSql.getKoneksi().createStatement();
            ResultSet rs = st.executeQuery("select id_pelanggan, nama from pelanggan;");
            
            while(rs.next()) {
                viewIdPelanggan.add(rs.getString("id_pelanggan") + "-" + rs.getString("nama"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewIdPelanggan;
    }
    
   public ArrayList<TableDataInterface> getAll() throws SQLException {
        Statement st = KoneksiSql.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from transaksi join barang on barang.kode_barang = transaksi.id_barang join pelanggan on pelanggan.id_pelanggan =transaksi.id_pelanggan ;");
        ArrayList<TableDataInterface> listTransaksi = new ArrayList<>();
        
        while(rs.next()) {
            TransaksiEntity transaksi = new TransaksiEntity();
            
            transaksi.setKodeBarang(rs.getString("kode_barang"));
            transaksi.setNamaBarang(rs.getString("nama_barang"));
            transaksi.setJumlah(Integer.parseInt(rs.getString("jumlah")));
            transaksi.setHargaBarang(Double.parseDouble(rs.getString("harga")));
            transaksi.setSubTotal(Long.parseLong(rs.getString("sub_total")));
            transaksi.setNamaPelanggan(rs.getString("nama"));
            transaksi.setAlamat(rs.getString("alamat"));
            transaksi.setNoTelp(rs.getString("notlp"));
            
            listTransaksi.add(transaksi);
        }
        return listTransaksi;
    }
   
    public int insert(List<TransaksiEntity> listTransaksi) throws SQLException {
        KoneksiSql.getKoneksi().setAutoCommit(false);
        int inserted = 0;
        
        for (TransaksiEntity transaksi : listTransaksi) {
            PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                    "insert into transaksi(id_barang, id_pelanggan, jumlah, sub_total) values(?, ?, ?, ?);"
            );
            st.setString(1, transaksi.getIdBarang());
            st.setInt(2, transaksi.getIdPelanggan());
            st.setInt(3, transaksi.getJumlah());
            st.setLong(4, transaksi.getSubTotal());
            System.out.println(st.toString());
            int rowAffected = st.executeUpdate();
            
            if (rowAffected == 1) {
                inserted++;
            }
        }
        
        if (inserted == listTransaksi.size()) {
            KoneksiSql.getKoneksi().commit();
        } else {
            KoneksiSql.getKoneksi().rollback();
        }
        
        return inserted;
    }
    
     public void delete(String id) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "delete from transaksi where id = ?;"
        );
        st.setString(1, id);
        st.executeUpdate();
    }
}
