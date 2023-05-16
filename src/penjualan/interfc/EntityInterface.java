/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.interfc;

/**
 *
 * @author fadil
 */
public interface EntityInterface {
    String[] getNamaKolom();
    String[] getHeader();
    Object[] toTableRow();
    Object[] toTableRowWithIndex(int index);
    EntityInterface newObject(String[] args);
    
}
