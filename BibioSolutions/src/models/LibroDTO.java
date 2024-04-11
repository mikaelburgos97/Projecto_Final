/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.time.LocalDate;
import models.utils.DateHandler;
/**
 *
 * @author Alan Alexander Perez
 */
public class LibroDTO {
    
    int id;
    double daily_price;
    double buyed_price;
    String title;
    String autorName;
       int total_stock = 0;
    String desc_ubicacion;
    double mora_aument;
    double total_ganancies;
        LocalDate created_at;

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
    
    
    public boolean disponible() {
        return this.getTotal_stock() >0;
    }
    
    public String tiempoRegistrado() {
        return DateHandler.differenceInDays(DateHandler.parseStringToDate(this.getCreated_at()));
    }
    
    

    
    

}
