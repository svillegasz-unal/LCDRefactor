/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdrefactor;

import java.util.HashMap;

/**
 *
 * @author Villegas
 */
public class Segmentador {
    private final HashMap<Character, int[]> SEGMENTOS;
    private final int INFERIOR = 1;
    private final int CENTRO = 2;
    private final int SUPERIOR = 3;    
    private final int INFERIOR_IZQUIERDO = 4;
    private final int INFERIOR_DERECHO = 5;
    private final int SUPERIOR_IZQUIERDO = 6;
    private final int SUPERIOR_DERECHO = 7;
    private final char CARACTER_VERTICAL = '|';
    private final char CARACTER_HORIZONTAL = '-';
    private Digito digito;
    
    public Segmentador(Digito digito){
        this.SEGMENTOS = new HashMap<>();
        this.digito = digito;
        setSegmentos();
    }
    
    private void setSegmentos(){
        SEGMENTOS.put('1', new int[] {INFERIOR_DERECHO, SUPERIOR_DERECHO});
        SEGMENTOS.put('2', new int[] {SUPERIOR, INFERIOR, CENTRO, INFERIOR_IZQUIERDO, SUPERIOR_DERECHO});
        SEGMENTOS.put('3', new int[] {INFERIOR, SUPERIOR, CENTRO, INFERIOR_DERECHO, SUPERIOR_DERECHO});
        SEGMENTOS.put('4', new int[] {CENTRO, INFERIOR_DERECHO,SUPERIOR_DERECHO, SUPERIOR_IZQUIERDO});
        SEGMENTOS.put('5', new int[] {INFERIOR, SUPERIOR, CENTRO, INFERIOR_DERECHO, SUPERIOR_IZQUIERDO});
        SEGMENTOS.put('6', new int[] {INFERIOR, SUPERIOR, CENTRO, INFERIOR_DERECHO, INFERIOR_IZQUIERDO, SUPERIOR_IZQUIERDO});
        SEGMENTOS.put('7', new int[] {SUPERIOR, INFERIOR_DERECHO, SUPERIOR_DERECHO});
        SEGMENTOS.put('8', new int[] {INFERIOR, SUPERIOR, CENTRO, INFERIOR_DERECHO, INFERIOR_IZQUIERDO, SUPERIOR_DERECHO, SUPERIOR_IZQUIERDO});
        SEGMENTOS.put('9', new int[] {SUPERIOR, CENTRO, INFERIOR_DERECHO, SUPERIOR_DERECHO, SUPERIOR_IZQUIERDO});
        SEGMENTOS.put('0', new int[] {INFERIOR, SUPERIOR, INFERIOR_DERECHO, INFERIOR_IZQUIERDO, SUPERIOR_DERECHO, SUPERIOR_IZQUIERDO});        
    }
    
    public void segmentarDigito(){
        int[] segmentosDelDigito = SEGMENTOS.get(digito.getDigitoLeido());
        for (int segmento : segmentosDelDigito) {
            definirSegmento(segmento);
        }
    }

    private void definirSegmento(int segmento) {
        int filaDeInicio;
        int columnaDeInicio;
        switch(segmento){
            case INFERIOR:
                filaDeInicio = digito.getFilas() - 1;
                columnaDeInicio = 1;
                agregarSegmentoHorizontal(filaDeInicio, columnaDeInicio);
                break;
            case CENTRO:
                filaDeInicio = (int) Math.ceil((digito.getFilas() - 1)/2);
                columnaDeInicio = 1;
                agregarSegmentoHorizontal(filaDeInicio, columnaDeInicio);
                break;
            case SUPERIOR:
                filaDeInicio = 0;
                columnaDeInicio = 1;
                agregarSegmentoHorizontal(filaDeInicio, columnaDeInicio);
                break;
            case INFERIOR_IZQUIERDO:
                filaDeInicio = (int) Math.ceil((digito.getFilas() - 1)/2) + 1;
                columnaDeInicio = 0;
                agregarSegmentoVertical(filaDeInicio, columnaDeInicio);
                break;
            case INFERIOR_DERECHO:
                filaDeInicio = (int) Math.ceil((digito.getFilas() - 1)/2) + 1;
                columnaDeInicio = digito.getColumnas() - 1;
                agregarSegmentoVertical(filaDeInicio, columnaDeInicio);
                break;
            case SUPERIOR_IZQUIERDO:
                filaDeInicio = 1;
                columnaDeInicio = 0;
                agregarSegmentoVertical(filaDeInicio, columnaDeInicio);
                break;
            case SUPERIOR_DERECHO:
                filaDeInicio = 1;
                columnaDeInicio = digito.getColumnas() - 1;
                agregarSegmentoVertical(filaDeInicio, columnaDeInicio);
                break; 
            default:
                break;
        }
    }

    private void agregarSegmentoHorizontal(int filaDeInicio, int columnaDeInicio) {
        int fila = filaDeInicio;
        int columna = columnaDeInicio;
        for (int i = 0; i < digito.getTamannoSegmento(); i++) {
            digito.agregarCaracter(fila, columna, CARACTER_HORIZONTAL);
            columna++;
        }
    }

    private void agregarSegmentoVertical(int filaDeInicio, int columnaDeInicio) {
        int fila = filaDeInicio;
        int columna = columnaDeInicio;
        for (int i = 0; i < digito.getTamannoSegmento(); i++) {
            digito.agregarCaracter(fila, columna, CARACTER_VERTICAL);
            fila++;
        }
    }
}
