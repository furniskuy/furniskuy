/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.interfc;

public interface TableDataInterface {
    String[] getNamaKolom();
    String[] getHeader();
    Object[] toTableRow();
    Object[] toTableRowWithIndex(int index);
    TableDataInterface newObject(String[] args);
    
}
