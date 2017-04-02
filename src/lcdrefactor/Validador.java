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
public class Validador {
    
    private final int MAXIMO_VALOR_ESPACIO_ENTRE_DIGITOS;
    private final int MINIMO_VALOR_ESPACIO_ENTRE_DIGITOS;
    private final int MAXIMO_VALOR_TAMANNO;
    private final int MINIMO_VALOR_TAMANNO;
    private ArrayList<String> stackDeErrores;

    public Validador(){
        this.MAXIMO_VALOR_ESPACIO_ENTRE_DIGITOS = 5;
        this.MINIMO_VALOR_ESPACIO_ENTRE_DIGITOS = 0;
        this.MAXIMO_VALOR_TAMANNO = 10;
        this.MINIMO_VALOR_TAMANNO = 1;
        stackDeErrores = new ArrayList<>();
    }
    public void validarDatosDeEntrada(ArrayList<String> datosDeEntrada) {
        for (String datoDeEntrada : datosDeEntrada) {
            try {
                contieneComa(datoDeEntrada);
                contieneSoloUnaComa(datoDeEntrada);
                validarParametrosDeEntrada(datoDeEntrada);
            } catch (Exception error){
                stackDeErrores.add(error.getMessage());
            }
        }
        if (!stackDeErrores.isEmpty()) {
            imprimirErrores();
            System.exit(0);
        }
    }
    
    public void validarCantidadDeEspaciosEntreDigitos(String espaciosEntreDigitos) {
        try {
            esNumerico(espaciosEntreDigitos);
            validarRangoDeEspacioEntreDigitos(espaciosEntreDigitos);        
        } catch(Exception error){
            System.out.println(error.getMessage());
            System.exit(0);
        }
    }
    
    private void validarParametrosDeEntrada(String datoDeEntrada){
        String[] parametrosDeEntrada = datoDeEntrada.split(",");
        String tamanno = parametrosDeEntrada[0];
        String numero = parametrosDeEntrada[1];
        esNumerico(tamanno);
        validarRangoDeTamanno(tamanno);
        validarCaracteresNumericos(numero);
    }
    
    private void contieneComa(String cadena){
        if (!cadena.contains(",")) {
            throw new IllegalArgumentException("Cadena " + cadena + " no contiene caracter ,");
        }
    }
    
    private void contieneSoloUnaComa(String cadena){
        int longitudOriginal = cadena.length();
        int longitudSinComas = cadena.replace(",", "").length();
        int numeroDeOcurrencias = longitudOriginal - longitudSinComas;
        if (numeroDeOcurrencias > 1) {
            throw new IllegalArgumentException("Cadena " + cadena + " contiene mas caracter ,");
        }
    }
    
    private void esNumerico(String cadena){
        try {
            Integer.parseInt(cadena);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Cadena " + cadena + " no es un entero");
        }
    }
    
    private void validarRangoDeEspacioEntreDigitos(String espaciosEntreDigitos){
        int valorEspacioEntreDigitos = Integer.parseInt(espaciosEntreDigitos);
        if (valorEspacioEntreDigitos < this.MINIMO_VALOR_ESPACIO_ENTRE_DIGITOS || 
            valorEspacioEntreDigitos > this.MAXIMO_VALOR_ESPACIO_ENTRE_DIGITOS) {
            throw new IllegalArgumentException("El espacio entre digitos debe estar entre 0 y 5");
        }
    }
    
    private void validarRangoDeTamanno(String tamanno){
        int valorTamanno = Integer.parseInt(tamanno);
        if (valorTamanno < this.MINIMO_VALOR_TAMANNO || 
            valorTamanno > this.MAXIMO_VALOR_TAMANNO) {
            throw new IllegalArgumentException("El parametro size ["+ tamanno + "] debe estar entre 1 y 10");
        }
    }
    
    private void validarCaracteresNumericos(String cadena){
        char[] caracteres = cadena.toCharArray();
        for (char caracter : caracteres) {
            if( ! Character.isDigit(caracter))
            {
                throw new IllegalArgumentException("Caracter " + caracter + " no es un digito");
            }
        }
    }

    private void imprimirErrores() {
        for (String error : stackDeErrores) {
            System.out.println(error);
        }
    }
}
