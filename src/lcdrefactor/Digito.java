/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdrefactor;

import java.util.Arrays;

/**
 *
 * @author Villegas
 */
public class Digito {    
    private int tamannoSegmento;
    private int filas;
    private int columnas;
    private char digitoLeido;
    private char[][] digito;
    private Segmentador segmentador;
    
    public Digito(char digitoLeido, int tamanno){
        this.tamannoSegmento = tamanno;
        this.filas = (2 * tamanno) + 3;
        this.columnas = tamanno + 2;
        this.digitoLeido = digitoLeido;
        this.digito = new char[this.filas][this.columnas];
        this.segmentador = new Segmentador(this);
        crearMatriz();
    }
    
    /**
     * @return the tamanno
     */
    public int getTamannoSegmento() {
        return tamannoSegmento;
    }
    
    /**
     * @return the numeroLeido
     */
    public char getDigitoLeido() {
        return digitoLeido;
    }
    
        /**
     * @return the filas
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @return the columnas
     */
    public int getColumnas() {
        return columnas;
    }
    
    public void agregarCaracter(int fila, int columna, char caracter){
        digito[fila][columna] = caracter; 
    }
    
    public String getFila(int fila){
        StringBuilder salida = new StringBuilder();
        for (int j = 0; j < getColumnas(); j++) {
            salida.append(digito[fila][j]);
        }
        return salida.toString();
    }
    
    private void crearMatriz(){
        for (char[] fila : digito) {
            Arrays.fill(fila, ' ');
        }
        segmentador.segmentarDigito();
    }
}
