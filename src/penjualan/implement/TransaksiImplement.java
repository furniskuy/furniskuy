/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import penjualan.koneksi.KoneksiSql;

/**
 *
 * @author fadil
 */
public class TransaksiImplement {
    public int urutanDb() {
        Connection con = (Connection) KoneksiSql.getKoneksi();
        int jumlah = 0;
        
        try {
            String sql = "select count(*) as urutan from penjualan;";
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                jumlah = rs.getInt("urutan");
            }
            
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ++jumlah;
    }
    
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
}
