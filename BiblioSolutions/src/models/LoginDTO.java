/*
 * Clase LoginDTO utilizada para representar y manipular los datos de login de los usuarios.
 */
package models;
import java.time.LocalDate;
import models.utils.DateHandler; // Utilizado para manejar las conversiones de fechas.

/**
 * Clase que define los atributos y métodos para manipular los datos de login de los usuarios.
 * Autor: Alan Alexander Pérez
 */
public class LoginDTO {
    // Atributos de la clase con su tipo y propósito.
    int id; // Identificador único para el usuario.
    String username; // Nombre de usuario.
    String password; // Contraseña del usuario.
    LocalDate last_login; // Fecha del último inicio de sesión.
    LocalDate created_at; // Fecha de creación del registro del usuario.
    
    // Métodos getters y setters para cada atributo, permitiendo obtener y establecer sus valores.
    
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

    // Método para establecer la fecha del último login, convirtiendo un String a LocalDate.
    public void setLast_login(String last_login) {
        this.last_login = DateHandler.parseStringToDate(last_login);
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    // Método para establecer la fecha de creación del registro, convirtiendo un String a LocalDate.
    public void setCreated_at(String created_at) {
        this.created_at = DateHandler.parseStringToDate(created_at);
    }
}
