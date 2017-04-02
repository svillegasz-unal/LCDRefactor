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
public class Numero {
    private ArrayList<Digito> digitos;
    private int tamannoSegmento;
    private String espacioEntreDigitos;
    
    public Numero(int tamanno, int espaciosEntreDigitos){
        this.tamannoSegmento = tamanno;
        this.espacioEntreDigitos = "";
        for (int i = 0; i < espaciosEntreDigitos; i++) {
            this.espacioEntreDigitos += " ";
        }       
    }
    
    public void parsearCadenaADigitos(String cadena) {
        this.digitos = new ArrayList<>();
        char[] caracteres;
        caracteres = cadena.toCharArray();
        for (char caracter : caracteres) {
            Digito digito = new Digito(caracter, tamannoSegmento);
            this.digitos.add(digito);
        }
    }
    
    @Override
    public String toString(){
        StringBuilder salida = new StringBuilder();
        int numeroDeFilas = (tamannoSegmento * 2) + 3;
        String filaDigito;
        for (int fila = 0; fila < numeroDeFilas; fila++) {            
            for (Digito digito : digitos) {
                filaDigito = digito.getFila(fila);
                salida.append(filaDigito).append(espacioEntreDigitos);
            }
            salida.append("\n");
        }
        return salida.toString();
    }
}
