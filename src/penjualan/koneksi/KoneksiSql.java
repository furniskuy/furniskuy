/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import penjualan.interfc.EntityInterfc;

/**
 *
 * @author fadil
 */
public class KoneksiSql {

    private static Connection con = null;
    private String databaseArgs = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcString = "jdbc:mysql://localhost:3306/db_jual";
    private String databaseUser = "root";
    private String databasePassword = "";

    public Connection getConnect() throws Exception {
        if (con == null) {
            try {
                con = getConnection();
                return con;
            } catch (Exception e) {
                throw e;
            }
        }
        return con;
    }

    private Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Koneksi pada database berhasil");
            return DriverManager.getConnection(
                    jdbcString + databaseArgs, databaseUser, databasePassword);
        } catch (Exception e) {
            System.out.println("Koneksi pada database error: " + e.getMessage());
            throw new Exception("Tidak dapat koneksi ke database");
        }
    }

    public ArrayList<EntityInterfc> select(String query, EntityInterfc entity) throws Exception {

        ArrayList<EntityInterfc> entities = new ArrayList();
        int colLenght = entity.getNamaKolom().length;
        String[] colName = entity.getNamaKolom();

        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String[] values = new String[colLenght];
                for (int i = 0; i < colLenght; i++) {
                    values[i] = rs.getString(colName[i]);
                }
                EntityInterfc newEntity = entity.newObject(values);
                entities.add(newEntity);
            }
            
            return entities;

        } catch (Exception e) {
            throw e;
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    throw sqlEx;
                }

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    throw sqlEx;
                } // ignore

                stmt = null;
            }
        }

    }
    
    public boolean update(String query) throws Exception {
        
        Statement stmt = null;

        try {
            con = getConnection();

            stmt = con.createStatement();
            int status = stmt.executeUpdate(query);
            
            if (status == 1) {
                return true;
            }
            
            return false;
            
        } catch (Exception e) {
            throw e;
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    throw sqlEx;
                } // ignore

                stmt = null;
            }
        }

    }

}
