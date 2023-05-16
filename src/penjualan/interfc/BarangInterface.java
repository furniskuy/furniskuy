/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.interfc;

import java.sql.SQLException;
import java.util.List;
import penjualan.entity.BarangEntity;
/**
 *
 * @author fadil
 */
public interface BarangInterface {
    BarangEntity insert(BarangEntity barang) throws SQLException;
    void update(BarangEntity barang) throws SQLException;
    void delete(String kodeBarang) throws SQLException;
    List<EntityInterface> getAll() throws SQLException;
}
