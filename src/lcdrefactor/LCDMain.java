/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdrefactor;

/**
 *
 * @author Villegas
 */
public class LCDMain {

    /**
     * @param args the command line arguments
     */
    
    private static LCDImpresor impresor;
    private static LCDLector lector;
    
    public static void main(String[] args) {
        lector = new LCDLector();
        lector.leer();
        
        impresor = new LCDImpresor(lector.getDatosDeEntrada(), lector.getEspaciosEntreDigitos());
        impresor.imprimir();       
    }
}
