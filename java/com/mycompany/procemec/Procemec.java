/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.procemec;

import Ventanas.Login;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
/**
 *
 * @author familiar
 */
public class Procemec {
public static void main(String[] args) {
   // Crear una instancia de la ventana de registro
   
        Login menu = new Login();
        // Hacer visible la ventana de registro
        menu.setVisible(true);
                // Crear un SpinnerNumberModel para valores double
        SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.1); // Valor inicial, mínimo, máximo, incremento

        // Crear el JSpinner con el modelo
        JSpinner spinnerPesoBobina = new JSpinner(model);
    }
  
}
//funcion para eliminar usuario