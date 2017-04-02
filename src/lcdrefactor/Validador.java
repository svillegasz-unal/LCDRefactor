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
            contieneComa(datoDeEntrada);
            contieneSoloUnaComa(datoDeEntrada);
            validarParametrosDeEntrada(datoDeEntrada);
        }
        if (!stackDeErrores.isEmpty()) {
            imprimirErrores();
            System.exit(0);
        }
    }
    
    public void validarCantidadDeEspaciosEntreDigitos(String espaciosEntreDigitos) {
        esNumerico(espaciosEntreDigitos);
        validarRangoDeEspacioEntreDigitos(espaciosEntreDigitos);        
        if (!stackDeErrores.isEmpty()) {
            imprimirErrores();
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
            String error = "Cadena " + cadena + " no contiene caracter ,";
            stackDeErrores.add(error);
        }
    }
    
    private void contieneSoloUnaComa(String cadena){
        int longitudOriginal = cadena.length();
        int longitudSinComas = cadena.replace(",", "").length();
        int numeroDeOcurrencias = longitudOriginal - longitudSinComas;
        if (numeroDeOcurrencias > 1) {
            String error = "Cadena " + cadena + " contiene mas caracter ,";
            stackDeErrores.add(error);
        }
    }
    
    private void esNumerico(String cadena){
        try {
            Integer.parseInt(cadena);
        } catch (NumberFormatException ex) {
            String error = "Cadena " + cadena + " no es un entero";
            stackDeErrores.add(error);
        }
    }
    
    private void validarRangoDeEspacioEntreDigitos(String espaciosEntreDigitos){
        int valorEspacioEntreDigitos = Integer.parseInt(espaciosEntreDigitos);
        if (valorEspacioEntreDigitos < this.MINIMO_VALOR_ESPACIO_ENTRE_DIGITOS || 
            valorEspacioEntreDigitos > this.MAXIMO_VALOR_ESPACIO_ENTRE_DIGITOS) {
            String error = "El espacio entre digitos debe estar entre 0 y 5";
            stackDeErrores.add(error);
        }
    }
    
    private void validarRangoDeTamanno(String tamanno){
        int valorTamanno = Integer.parseInt(tamanno);
        if (valorTamanno < this.MINIMO_VALOR_TAMANNO || 
            valorTamanno > this.MAXIMO_VALOR_TAMANNO) {
            String error = "El parametro size ["+ tamanno + "] debe estar entre 1 y 10";
            stackDeErrores.add(error);
        }
    }
    
    private void validarCaracteresNumericos(String cadena){
        char[] caracteres = cadena.toCharArray();
        for (char caracter : caracteres) {
            if( ! Character.isDigit(caracter))
            {
                String error = "Caracter " + caracter + " no es un digito";
                stackDeErrores.add(error);
            }
        }
    }

    private void imprimirErrores() {
        for (String error : stackDeErrores) {
            System.out.println(error);
        }
    }
}
