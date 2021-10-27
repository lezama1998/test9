/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebalevador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Usuario
 */
public class elevador {

    public elevador() {
    }

    ArrayList<Integer> pisosvisita = new ArrayList<Integer>();

    int pisoactual;
    String sentido;
    double diferencia;
    String primersentido;
    Map<Integer, Integer> ingresos = new HashMap<Integer, Integer>();
    // metodo para convertir el arreglo inicial en una lista (para facilitar el manejo de los datos)
    public ArrayList convertir(int[] pisos) {
        ArrayList convertir = new ArrayList();
        for (int i = 0; i < pisos.length; i++) {
            convertir.add(pisos[i]);
        }

        return convertir;
    }
  //metodo para calcular el sentido del asensor deacuerdo a la diferencia
    // entre el piso actual y el piso que sigue
    public String sentidos(double d) {
        String mensaje;

        if (d >= 0) {
            mensaje = "subiendo";
        } else {
            mensaje = "bajando";
        }
        return mensaje;
    }
   // metodo que calcula los piso a visitar de manera optima 
    //(que piso ingresado le queda de camino) si el asensor sube 
    
    public ArrayList<Integer> recorridoSubida(int pisoinicial, ArrayList<Integer> pisosvisita) {
      
        
        for (int i = 0; i < pisosvisita.size(); i++) {
            diferencia = pisosvisita.get(i) - pisoactual;

            sentido = sentidos(diferencia);

            
            if (pisoactual <= pisosvisita.get(i)) {

                pisoactual = pisosvisita.get(i);

                System.out.println(sentido);
                System.out.println("eleavador en el piso" + pisoactual);

                if (ingresos.containsKey(pisosvisita.get(i)) == true) {

                    for (int j = 0; j < pisosvisita.size(); j++) {

                        if (pisosvisita.get(j) > ingresos.get(pisosvisita.get(i))) {
                            if (!pisosvisita.contains(ingresos.get(pisosvisita.get(i)))) {
                                System.out.println("ingresan piso" + ingresos.get(pisosvisita.get(i)));
                                pisosvisita.add(j, ingresos.get(pisosvisita.get(i)));

                                ingresos.remove(pisosvisita.get(i));
                                

                                if (j <= i) {
                                    i++;
                                }
                            }
                            break;
                        }

                    }

                   
                }

             
            } else {
                
            }

            pisosvisita.remove(i);
            i--;

        }

        return pisosvisita;
    }
  // metodo que calcula los piso a visitar de manera optima 
    //(que piso ingresado le queda de camino) si el asensor baja 
    public ArrayList<Integer> recorridoBajada(int pisoinicial, ArrayList<Integer> pisosvisita) {
      
        for (int i = 0; i < pisosvisita.size(); i++) {
            diferencia = pisosvisita.get(i) - pisoactual;

            sentido = sentidos(diferencia);

   
            if (pisoactual >= pisosvisita.get(i)) {
                pisoactual = pisosvisita.get(i);
                System.out.println(sentido);
                System.out.println("elevador en el piso" + pisoactual);

                if (ingresos.containsKey(pisosvisita.get(i)) == true) {
                    pisosvisita.add(ingresos.get(pisosvisita.get(i)));
                    for (int j = 0; j < pisosvisita.size(); j++) {
                        if (pisosvisita.get(j) < ingresos.get(pisosvisita.get(i))) {
                            if (!pisosvisita.contains(ingresos.get(pisosvisita.get(i)))) {
                                System.out.println("ingresan piso" + ingresos.get(pisosvisita.get(i)));
                                pisosvisita.add(j, ingresos.get(pisosvisita.get(i)));

                                ingresos.remove(pisosvisita.get(i));

                                if (j <= i) {
                                    i++;
                                }
                            }
                            break;
                        }

                    }

                }

            } else {
                
            }
            pisosvisita.remove(i);
            i--;

        }

        return pisosvisita;
    }
 // metodo el cual se llama desde el main y comienza el programa
    
    public void comenzar(int[] pisos, int pisoinicial, Map<Integer, Integer> in) {
        System.out.println("piso inciial " + pisoinicial);
        ingresos = in;
 //se convierte el arreglo en una lita
        pisosvisita = convertir(pisos);
        System.out.println("pisos  " + pisosvisita);
        System.out.println("ingresos " + ingresos);
        // se ordena la lista de menor a mayor
        Collections.sort(pisosvisita);
      
        pisoactual = pisoinicial;
        
        double sentidoinicial = 0;
        //se calcula el sentido inicial del elevador deacuerdo al piso mas cercano
        //usando el valor absoluto de la diferencias y comparandolas
        // en caso tal de que un piso arriba y un piso abajo esten a igual ditancia
        //inicia subiendo
        sentidoinicial = Math.abs(pisosvisita.get(0) - pisoactual);
        for (int i = 0; i < pisosvisita.size(); i++) {

            if (sentidoinicial >= Math.abs(pisosvisita.get(i) - pisoactual)) {
                sentidoinicial = Math.abs(pisosvisita.get(i) - pisoactual);
                diferencia = pisosvisita.get(i) - pisoactual;
                sentido = sentidos(diferencia);

            }

        }

        // se ejecuta el while mientras en la lista queden valores
        while (pisosvisita.size() > 0) {

            //deacuerdo al sentido se ejecutan los recorridos en diferente orden
            
            if (sentido == "subiendo") {
              // si esta subiendo ejecuta el recorrido de subida
              
                pisosvisita = recorridoSubida(pisoactual, pisosvisita);
             // se ordena la lista de mayor a menor para ir de bajada
                Comparator<Integer> comparador = Collections.reverseOrder();
                Collections.sort(pisosvisita, comparador);
            //se ejecuta el reocorrido de bajada
                pisosvisita = recorridoBajada(pisoactual, pisosvisita);

            } else {
                // se ordena la lista de mayor a menor para ir de bajada
                Comparator<Integer> comparador = Collections.reverseOrder();
                Collections.sort(pisosvisita, comparador);
                //se ejecuta el reocorrido de bajada
                pisosvisita = recorridoBajada(pisoactual, pisosvisita);
                //se ordena el arreglo de menor a mayor
                Collections.sort(pisosvisita);
                //se ejecuta el recorrido de subibida
                pisosvisita = recorridoSubida(pisoactual, pisosvisita);

            }
           

        }

        
    }

}
