/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.entity;

import penjualan.interfc.EntityInterfc;

/**
 *
 * @author fadil
 */
public class BarangModel implements EntityInterfc {
    private String kodeBarang, namaBarang;
    
    int jumlah, harga;
    
    public BarangModel() {
        
    }

    public BarangModel(String kodeBarang, String namaBarang, String jumlah, String harga) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.jumlah = Integer.parseInt(jumlah);
        this.harga = Integer.parseInt(harga);
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getHarga() {
        return harga;
    }
    
    
    @Override
    public String[] getNamaKolom() {
        return new String[] {"kode_barang", "nama_barang", "jumlah", "harga"};
    }
    
    @Override
    public String[] getHeader() {
        return new String[] {"No", "Kode Barang", "Nama Barang", "Jumlah", "Harga"};
    }
    
    @Override
    public Object[] toTableRow() {
        return new Object[] {kodeBarang, namaBarang, jumlah, harga};
    }
    
    @Override
    public Object[] toTableRowWithIndex(int index) {
        return new Object[] {index+1, kodeBarang, namaBarang, jumlah, harga};
    }
    

    @Override
    public EntityInterfc newObject(String[] args) {
        return new BarangModel(args[0], args[1], args[2], args[3]);
    }
}
