/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.implement;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import penjualan.entity.BarangEntity;
import penjualan.interfc.BarangInterface;
import penjualan.koneksi.KoneksiSql;
import penjualan.interfc.TableDataInterface;

public class BarangImplement implements BarangInterface {
    public BarangEntity insert(BarangEntity barang) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "insert into barang values(?, ?, ?, ?);"
        );
        st.setString(1, barang.getKodeBarang());
        st.setString(2, barang.getNamaBarang());
        st.setString(3, barang.getJumlah());
        st.setString(4, barang.getHarga());
        st.executeUpdate();
        
        return barang;
    }
    
    public void update(BarangEntity barang) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "update barang set nama_barang = ?, jumlah = ?, " +
                "harga = ? where kode_barang = ?;"
        );
        st.setString(1, barang.getNamaBarang());
        st.setString(2, barang.getJumlah());
        st.setString(3, barang.getHarga());
        st.setString(4, barang.getKodeBarang());
        st.executeUpdate();
    }
    
    public void delete(String kodeBarang) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "delete from barang where kode_barang = ?;"
        );
        st.setString(1, kodeBarang);
        st.executeUpdate();
    }
    
    public List<TableDataInterface> getAll() throws SQLException {
        Statement st = KoneksiSql.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from barang;");
        List<TableDataInterface> listBarang = new ArrayList<TableDataInterface>();
        
        while(rs.next()) {
            BarangEntity barang = new BarangEntity();
            
            barang.setKodeBarang(rs.getString("kode_barang"));
            barang.setNamaBarang(rs.getString("nama_barang"));
            barang.setJumlah(rs.getString("jumlah"));
            barang.setHarga(rs.getString("harga"));
            
            listBarang.add(barang);
        }
        return listBarang;
    }
    
}
