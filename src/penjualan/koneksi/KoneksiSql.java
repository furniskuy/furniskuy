/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KoneksiSql {

    private static Connection con = null;
    private static String databaseArgs = 
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String jdbcString = "jdbc:mysql://localhost:3306/pbo_barang";
    private static String databaseUser = "root";
    private static String databasePassword = "secret";

    public static Connection getKoneksi() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                    jdbcString + databaseArgs, databaseUser, databasePassword);
            } catch (Exception e) {
                Logger.getLogger(KoneksiSql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return con;
    }

}
