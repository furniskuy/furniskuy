/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.interfc;

import java.sql.SQLException;
import java.util.List;
import penjualan.entity.PelangganEntity;

public interface PelangganInterface {
    PelangganEntity insert(PelangganEntity pelanggan) throws SQLException;
    void update(PelangganEntity pelanggan) throws SQLException;
    void delete(String idPelanggan) throws SQLException;
    List<TableDataInterface> getAll() throws SQLException;
}
