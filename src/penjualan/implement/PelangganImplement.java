/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.implement;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import penjualan.entity.PelangganEntity;
import penjualan.interfc.PelangganInterface;
import penjualan.koneksi.KoneksiSql;
import penjualan.interfc.TableDataInterface;

/**
 *
 * @author fadil
 */
public class PelangganImplement implements PelangganInterface  {
    public PelangganEntity insert(PelangganEntity pelanggan) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "insert into pelanggan values( ?, ?, ?, ?);"
        );
        st.setString(2, pelanggan.getNama());
        st.setString(3, pelanggan.getJenisKelamin());
        st.setString(4, pelanggan.getAlamat());
        st.setString(5, pelanggan.getNoTelp());
        st.executeUpdate();
        
        return pelanggan;
    }
    
    public void update(PelangganEntity pelanggan) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "update pelanggan set nama = ?, jk = ?, " +
                "alamat = ?, notlp = ? where id_pelanggan = ?;"
        );
        st.setString(1, pelanggan.getNama());
        st.setString(2, pelanggan.getJenisKelamin());
        st.setString(3, pelanggan.getAlamat());
        st.setString(4, pelanggan.getNoTelp());
        st.setString(5, pelanggan.getIdPelanggan());
        st.executeUpdate();
    }
    
    public void delete(String idPelanggan) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "delete from pelanggan where id_pelanggan = ?;"
        );
        st.setString(1, idPelanggan);
        st.executeUpdate();
    }
    
    public ArrayList<TableDataInterface> getAll() throws SQLException {
        Statement st = KoneksiSql.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from pelanggan;");
        ArrayList<TableDataInterface> listPelanggan = new ArrayList<TableDataInterface>();
        
        while(rs.next()) {
            PelangganEntity pelanggan = new PelangganEntity();
            
            pelanggan.setIdPegawai(rs.getString("id_pelanggan"));
            pelanggan.setNama(rs.getString("nama"));
            pelanggan.setJenisKelamin(rs.getString("jk"));
            pelanggan.setAlamat(rs.getString("alamat"));
            pelanggan.setNoTelp(rs.getString("notlp"));
            
            listPelanggan.add(pelanggan);
        }
        return listPelanggan;
    }
    
}

