/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit; 

/**
 *
 * @author Alan Alexander Perez
 */
public  class DateHandler {

    public static LocalDate parseStringToDate(String dateInput) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        boolean fechaValida = Pattern.matches(regex, dateInput);
        if (fechaValida) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(dateInput, formato);
            return fecha;
        } else {
            System.out.println("La fecha no es válida.");
            return null;
        }
    }
    
    
    public static String parseDateToString(LocalDate dateObj) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = dateObj.format(formato);
        return fechaFormateada;
    }
    
    public static String differenceInDays(LocalDate dateObj) {
        LocalDate fechaActual = LocalDate.now();    
        long diferenciaEnDias = ChronoUnit.DAYS.between(dateObj, fechaActual);
        return diferenciaEnDias + " dias";
    }
}
