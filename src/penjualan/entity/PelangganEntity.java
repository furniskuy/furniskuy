/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.entity;

import penjualan.interfc.TableDataInterface;

public class PelangganEntity implements TableDataInterface {
    private int idPelanggan;
    private String nama, jenisKelamin, alamat, noTelp;
    
    private String idPel = String.valueOf(idPelanggan);
    
    public PelangganEntity() {}

    public PelangganEntity(String idPelanggan, String nama, String jenisKelamin, String alamat, String noTelp) {
        this.idPelanggan = Integer.parseInt(idPelanggan);
        this.idPel  = String.valueOf(idPelanggan);
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public String getIdPelanggan() {
        return idPel;
    }

    public void setIdPegawai(String idPel) {
        this.idPel = idPel;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
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

     @Override
    public String[] getNamaKolom() {
        return new String[] {"id", "nama", "jk", "alamat", "notlp"};
    }
    
    @Override
    public String[] getHeader() {
        return new String[] {"No", "ID", "Nama", "Jenis Kelamin", "Alamat", "No Telp."};
    }
    
    @Override
    public Object[] toTableRow() {
        return new Object[] {idPel, nama, jenisKelamin, alamat, noTelp};
    }
    
    @Override
    public Object[] toTableRowWithIndex(int index) {
        return new Object[] {index+1, idPel, nama, jenisKelamin, alamat, noTelp};
    }
    

    @Override
    public TableDataInterface newObject(String[] args) {
        return new PelangganEntity(args[0], args[1], args[2], args[3], args[4]);
    }
}
