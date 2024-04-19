/*
 * Clase LibroDTO utilizada para representar y manipular los datos de libros en la aplicación.
 */
package models;
import java.time.LocalDate;
import models.utils.DateHandler; // Utilizado para manejar las conversiones de fechas.

/**
 * Clase que define los atributos y métodos para manipular los datos de los libros.
 * Autor: Alan Alexander Pérez
 */
public class LibroDTO {
    
    // Atributos de la clase con su tipo y propósito.
    int id;
    double daily_price; // Precio diario de alquiler del libro.
    double buyed_price; // Precio de compra del libro.
    String title; // Título del libro.
    String autorName; // Nombre del autor del libro.
    int total_stock = 0; // Cantidad total de ejemplares disponibles.
    String desc_ubicacion; // Descripción de la ubicación del libro.
    double mora_aument; // Incremento de la mora por día de retraso.
    double total_ganancies; // Ganancias totales generadas por el libro.
    LocalDate created_at; // Fecha de registro del libro.

    // Métodos getters y setters para cada atributo, permitiendo obtener y establecer sus valores.
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }
 
    public String getDesc_ubicacion() {
        return desc_ubicacion;
    }

    public void setDesc_ubicacion(String desc_ubicacion) {
        this.desc_ubicacion = desc_ubicacion;
    }

    public double getTotal_ganancies() {
        return total_ganancies;
    }

    public void setTotal_ganancies(double total_ganancies) {
        this.total_ganancies = total_ganancies;
    }

    public String getCreated_at() {
        return DateHandler.parseDateToString(created_at);
    }

    public void setCreated_at(String created_at) {
        this.created_at = DateHandler.parseStringToDate(created_at);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(double daily_price) {
        this.daily_price = daily_price;
    }

    public double getBuyed_price() {
        return buyed_price;
    }

    public void setBuyed_price(double buyed_price) {
        this.buyed_price = buyed_price;
    }

    public int getTotal_stock() {
        return total_stock;
    }

    public void setTotal_stock(int total_stock) {
        this.total_stock = total_stock;
    }

    public double getMora_aument() {
        return mora_aument;
    }

    public void setMora_aument(double mora_aument) {
        this.mora_aument = mora_aument;
    }
    
    // Método que verifica si el libro está disponible para ser alquilado.
    public boolean disponible() {
        return this.getTotal_stock() > 0;
    }
    
    // Método que calcula el tiempo en días desde que el libro fue registrado.
    public String tiempoRegistrado() {
        return DateHandler.differenceInDays(DateHandler.parseStringToDate(this.getCreated_at()));
    }
}
