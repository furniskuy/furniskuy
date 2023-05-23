/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.entity;

import penjualan.interfc.TableDataInterface;

/**
 *
 * @author fadil
 */
public class BarangEntity implements TableDataInterface {
    private String kodeBarang, namaBarang;
    private int stokBarang;
    private double hargaBarang;
    
    String jumlah = String.valueOf(stokBarang);
    String harga = String.valueOf(hargaBarang);
    
    public BarangEntity() {}
    
    public BarangEntity(String kodeBarang, String namaBarang, String jumlah, String harga) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.setJumlah(jumlah);
        this.setHarga(harga);
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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
    public TableDataInterface newObject(String[] args) {
        return new BarangEntity(args[0], args[1], args[2], args[3]);
    }
    
}
