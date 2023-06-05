/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utama;

import penjualan.view.LoginView;

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
            new LoginView().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Todo: Display Error (" + e.getMessage() + ")");
        }
    }

}
