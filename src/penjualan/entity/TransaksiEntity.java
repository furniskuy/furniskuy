/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penjualan.entity;

import penjualan.interfc.TableDataInterface;

/**
 *
 * @author fadildesk
 */
public class TransaksiEntity implements TableDataInterface {
    private int idTransaksi, idPelanggan;
    private String idBarang;
    private int jumlah;
    private long subTotal;
    
    private String namaPelanggan, alamat, noTelp;
    private String kodeBarang, namaBarang;
    private double hargaBarang;
    
    public TransaksiEntity() {}
 
    public TransaksiEntity(int idTransaksi, int idPelanggan, String idBarang, int jumlah, long subTotal, String namaPelanggan, String alamat, String noTelp, String kodeBarang, String namaBarang, double hargaBarang) {
        this.idTransaksi = idTransaksi;
        this.idPelanggan = idPelanggan;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.subTotal = subTotal;
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
   

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
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

    public double getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(double hargaBarang) {
        this.hargaBarang = hargaBarang;
    }
    
     @Override
    public String[] getNamaKolom() {
        return new String[] {"kode_barang", "nama_barang", "jumlah", "harga",  "sub_total", "nama_pelanggan", "alamat", "no_telp"};
    }
    
    @Override
    public String[] getHeader() {
        return new String[] {"No", "Kode Barang", "Nama Barang", "Jumlah", "Harga", "Sub Total", "Nama Pelanggan", "Alamat", "No Telp"};
    }
    
    @Override
    public Object[] toTableRow() {
        return new Object[] {kodeBarang, namaBarang, jumlah, hargaBarang, subTotal, namaPelanggan, alamat, noTelp};
    }
    
    @Override
    public Object[] toTableRowWithIndex(int index) {
        return new Object[] {index+1, kodeBarang, namaBarang, jumlah, hargaBarang, subTotal, namaPelanggan, alamat, noTelp};
    }
    

    @Override
    public TableDataInterface newObject(String[] args) {
        return new TransaksiEntity(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), Long.parseLong(args[4]), args[5], args[7], args[8], args[9], args[10], Double.parseDouble(args[11]));
    }
}
    
