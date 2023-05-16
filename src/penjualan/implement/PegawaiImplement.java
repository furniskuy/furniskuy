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
import penjualan.entity.PegawaiEntity;
import penjualan.koneksi.KoneksiSql;
import penjualan.interfc.PegawaiInterface;

/**
 *
 * @author fadil
 */
public class PegawaiImplement implements PegawaiInterface {
    public PegawaiEntity insert(PegawaiEntity pelanggan) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "insert into pelanggan values(?, ?, ?, ?, ?);"
        );
        st.setString(1, pelanggan.getIdPegawai());
        st.setString(2, pelanggan.getNama());
        st.setString(3, pelanggan.getJenisKelamin());
        st.setString(4, pelanggan.getAlamat());
        st.setString(5, pelanggan.getNoTelp());
        st.executeUpdate();
        
        return pelanggan;
    }
    
    public void update(PegawaiEntity pelanggan) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "update pelanggan set nama = ?, jk = ?, " +
                "alamat = ?, notlp = ? where id_pelanggan = ?;"
        );
        st.setString(1, pelanggan.getNama());
        st.setString(2, pelanggan.getJenisKelamin());
        st.setString(3, pelanggan.getAlamat());
        st.setString(4, pelanggan.getNoTelp());
        st.setString(5, pelanggan.getIdPegawai());
        st.executeUpdate();
    }
    
    public void delete(String idPegawai) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "delete from pelanggan where id_pelanggan = ?;"
        );
        st.setString(1, idPegawai);
        st.executeUpdate();
    }
    
    public List<PegawaiEntity> getAll() throws SQLException {
        Statement st = KoneksiSql.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery("select * from pelanggan;");
        List<PegawaiEntity> listPegawai = new ArrayList<PegawaiEntity>();
        
        while(rs.next()) {
            PegawaiEntity pelanggan = new PegawaiEntity();
            
            pelanggan.setIdPegawai(rs.getString("id_pelanggan"));
            pelanggan.setNama(rs.getString("nama"));
            pelanggan.setJenisKelamin(rs.getString("jk"));
            pelanggan.setAlamat(rs.getString("alamat"));
            pelanggan.setNoTelp(rs.getString("notlp"));
            
            listPegawai.add(pelanggan);
        }
        return listPegawai;
    }
    
}

