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
public class LoginDTO {
    int id;
    String username;
    String password;
    LocalDate last_login;
    LocalDate created_at;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        LocalDate dateObj = DateHandler.parseStringToDate(last_login);
        this.last_login = dateObj;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        LocalDate dateObj = DateHandler.parseStringToDate(created_at);
        this.created_at = dateObj;
    }
    
    


}
