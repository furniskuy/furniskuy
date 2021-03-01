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
import java.util.logging.Level;
import java.util.logging.Logger;
import penjualan.interfc.EntityInterface;

/**
 *
 * @author fadil
 */
public class KoneksiSql {

    private static Connection con = null;
    private String databaseArgs = 
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcString = "jdbc:mysql://localhost:3306/db_inventaris";
    private String databaseUser = "root";
    private String databasePassword = "";
    
    public static Connection getKoneksi() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_inventaris", "root", "");
            } catch (Exception e) {
                Logger.getLogger(KoneksiSql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return con;
    }

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
            System.out.println(e);
            System.out.println("Koneksi pada database error: " + e.getMessage());
            throw new Exception("Tidak dapat koneksi ke database");
        }
    }

    public ArrayList<EntityInterface> select(String query, EntityInterface entity) throws Exception {

        ArrayList<EntityInterface> entities = new ArrayList();
        int colLenght = entity.getNamaKolom().length;
        String[] colName = entity.getNamaKolom();

        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnect();

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String[] values = new String[colLenght];
                for (int i = 0; i < colLenght; i++) {
                    values[i] = rs.getString(colName[i]);
                }
                EntityInterface newEntity = entity.newObject(values);
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
    
    public void update(String query) throws Exception {
        
        Statement stmt = null;

        try {
            con = getConnect();

            stmt = con.createStatement();
            int status = stmt.executeUpdate(query);
            
            if (status == 0) {
                throw new Exception("Query data gagal");
            }
            
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
