/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penjualan.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import penjualan.entity.UserEntity;
import penjualan.koneksi.KoneksiSql;

/**
 *
 * @author fadildesk
 */
public class AuthImplement {

    public AuthImplement() {
    }
    
    public UserEntity getUser(String email) throws SQLException {
        PreparedStatement st = KoneksiSql.getKoneksi().prepareStatement(
                "select * from user" +
                " where email = ?;"
        );
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        UserEntity user = new UserEntity();
        
        if (rs.next()) {
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
        }
        
        return user;
    }
}
