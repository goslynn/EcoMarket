package cl.duocuc.ecomarket.util.validacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public abstract class Validador {

    public boolean esFechaValida(String fecha, String formato) {
        if (fecha == null || fecha.isEmpty()) return false;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            LocalDate.parse(fecha, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public boolean esNumeroValido(String numero) {
        if (numero == null || numero.isEmpty()) return false;
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean esNumeroTelefonicoValido(String numero){
        if (numero == null || numero.isEmpty()) return false;
        if (numero.length() > 9 && numero.startsWith("+")){
            numero = numero.substring(3);
        }
        String regex = "^[0-9]{9}$";
        return numero.matches(regex);
    }

    public boolean esStringNormalizado(String cadena){
        if (cadena == null || cadena.isEmpty()) return false;
        if (cadena.contains(" ")) return false;
        return cadena.matches("^[a-z]+$");
    }

    public boolean esRutValido(String rut){
        if (rut == null || rut.isEmpty()) return false;

        String[] partes = rut.split("-");
        if (partes.length != 2) return false;

        String numero = partes[0];
        String dv = partes[1].toUpperCase();

        if (!numero.matches("\\d+") || !dv.matches("[0-9K]")) return false;

        int suma = 0;
        int multiplicador = 2;
        for (int i = numero.length() - 1; i >= 0; i--) {
            suma += Integer.parseInt(String.valueOf(numero.charAt(i))) * multiplicador;
            multiplicador++;
            if (multiplicador > 7) multiplicador = 2;
        }

        int dvCalculado = 11 - (suma % 11);
        String dvEsperado;
        if (dvCalculado == 11) {
            dvEsperado = "0";
        } else if (dvCalculado == 10) {
            dvEsperado = "K";
        } else {
            dvEsperado = String.valueOf(dvCalculado);
        }

        return dv.equals(dvEsperado);
    }

}
