/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebalevador;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Pruebalevador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // pisos ingresados
         int []pisos={5,29,13,10};
         // piso inicial
         int pisoinicial=4;
         Map<Integer, Integer> ingresos = new HashMap<Integer, Integer>();
         //mapa de ingresos ingresados primer valor piso al que se ingresa 
         // segundo valor piso que ingresa
         ingresos.put(5,7);
         ingresos.put(29,10);
         ingresos.put(13,1);
         ingresos.put(10,1);
       elevador elevador1= new elevador();
       elevador1.comenzar(pisos,pisoinicial,ingresos);
    }
    
}
