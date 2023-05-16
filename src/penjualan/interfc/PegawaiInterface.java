/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.interfc;

import java.sql.SQLException;
import java.util.List;
import penjualan.entity.PegawaiEntity;

/**
 *
 * @author fadil
 */
public interface PegawaiInterface {
    PegawaiEntity insert(PegawaiEntity pelanggan) throws SQLException;
    void update(PegawaiEntity pelanggan) throws SQLException;
    void delete(String idPelanggan) throws SQLException;
    List<PegawaiEntity> getAll() throws SQLException;
}
