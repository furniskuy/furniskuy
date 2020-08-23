/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utama;

import java.util.ArrayList;
import penjualan.entity.BarangModel;
import penjualan.interfc.EntityInterfc;
import penjualan.koneksi.KoneksiSql;

/**
 *
 * @author fadil
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            KoneksiSql koneksiSql = new KoneksiSql();
            
            ArrayList<EntityInterfc> entities = koneksiSql.select("SELECT * FROM barang", new BarangModel());
            System.out.println(((BarangModel) entities.get(0)).getNamaBarang());
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Todo: Display Error (" + e.getMessage() + ")");
        }
    }

}
