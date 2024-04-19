/*
 * Clase SQLiteConnect para gestionar la conexión con la base de datos SQLite.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que define el método para conectar con la base de datos local.
 * Autor: Alan Alexander Pérez
 */
public class SQLiteConnect {
    public Connection cn = null;

    /**
     * Realiza la conexión con la base de datos local 'biblioSolutions.db' utilizando el motor SQLite.
     *
     * @return cn Objeto de conexión a la base de datos.
     */
    public Connection conectar() {
        try {
            // Carga el driver JDBC para SQLite.
            Class.forName("org.sqlite.JDBC");
            // Obtiene el directorio actual del sistema para construir la ruta de la base de datos.
            String directorioActual = System.getProperty("user.dir");
            // Establece la conexión a la base de datos específica en el directorio del proyecto.
            cn = DriverManager.getConnection("jdbc:sqlite:" + directorioActual +  "\\src\\models\\filesBD\\biblioSolutions.db");
            // Mensaje de éxito en la conexión.
            System.out.println("Estamos conectados con éxito!");

        } catch (ClassNotFoundException | SQLException sqlex) {
            // Maneja errores tanto del driver como de conexión SQL.
            System.out.println("Error al tratar de conectar: " + sqlex);
        }
        return cn; // Retorna el objeto de conexión.
    }
}
