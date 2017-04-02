/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdrefactor;

import java.util.ArrayList;

/**
 *
 * @author Villegas
 */
public class LCDImpresor {
    private ArrayList<String> datosDeEntrada;
    private int espaciosEntreDigitos;
    private ArrayList<Numero> numeros;
    
    public LCDImpresor(ArrayList<String> datosDeEntrada, int espaciosEntreDigitos){
        this.datosDeEntrada = datosDeEntrada;
        this.espaciosEntreDigitos = espaciosEntreDigitos;
        this.numeros = new ArrayList<>();
    }
    
    public void imprimir() {
        formatearNumeros();
        for (Numero numero : numeros) {
            System.out.println(numero);
        }
    }
    
    private void formatearNumeros(){
        String[] parametrosDeEntrada;
        int tamannoSegmento;
        String numeroLeido;
        
        for (String datoDeEntrada : datosDeEntrada) {
            parametrosDeEntrada = datoDeEntrada.split(",");
            tamannoSegmento = Integer.parseInt(parametrosDeEntrada[0]);
            numeroLeido = parametrosDeEntrada[1];
            Numero numero = new Numero(tamannoSegmento, espaciosEntreDigitos);
            numero.parsearCadenaADigitos(numeroLeido);
            numeros.add(numero);
        }
    }
}
