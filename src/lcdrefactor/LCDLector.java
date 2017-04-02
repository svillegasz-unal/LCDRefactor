/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdrefactor;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Villegas
 */
public class LCDLector {

    private final String CONDICION_DE_SALIDA;
    private Scanner lector;
    private ArrayList<String> datosDeEntrada;
    private int espaciosEntreDigitos;
    private Validador validador;
    
    public LCDLector(){
        this.CONDICION_DE_SALIDA = "0,0";
        this.lector = new Scanner(System.in);
        this.datosDeEntrada = new ArrayList<>();
        this.validador = new Validador();
    }
    
    public void leer() {
        leerEspaciosEntreDigitos();
        leerDatosDeEntrada();
    }
    
    /**
     * @return the datosDeEntrada
     */
    public ArrayList<String> getDatosDeEntrada() {
        return datosDeEntrada;
    }

    /**
     * @return the espaciosEntreDigitos
     */
    public int getEspaciosEntreDigitos() {
        return espaciosEntreDigitos;
    }

    private void leerDatosDeEntrada(){
        System.out.print("Entrada: ");
        String lineaLeida = lector.next();
        while (!lineaLeida.equalsIgnoreCase(CONDICION_DE_SALIDA)){
            datosDeEntrada.add(lineaLeida);
            
            System.out.print("Entrada: ");
            lineaLeida = lector.next();
        }
        validador.validarDatosDeEntrada(datosDeEntrada);
    }
    
    private void leerEspaciosEntreDigitos(){
        System.out.print("Espacio entre Digitos (0 a 5): ");
        String lineaLeida = lector.next();
        validador.validarCantidadDeEspaciosEntreDigitos(lineaLeida);
        espaciosEntreDigitos = Integer.parseInt(lineaLeida);
    }
}
