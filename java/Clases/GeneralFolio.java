/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author familiar
 */
public class GeneralFolio {
 
   public static String generarFolioBase() {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String timestamp = formatoFecha.format(new Date()); // Fecha y hora actual
    return timestamp + "." + UUID.randomUUID().toString().substring(0, 4); // Folio base
}
}
