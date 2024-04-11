/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alan Alexander Perez
 */
public class SQLiteConnect {
    public  Connection cn = null;

    /**
     * Interconexion con base de datos local (biblioSolutions.db) en 
     * motor SQLite
     * @return cn
     */
    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            String directorioActual = System.getProperty("user.dir");
            cn = DriverManager.getConnection("jdbc:sqlite:" + directorioActual +  "\\src\\models\\filesBD\\biblioSolutions.db");
            System.out.println("Estamos conectados con exito!");

        } catch (ClassNotFoundException | SQLException sqlex) {
            System.out.println("Error al tratar de conectar: " + sqlex);
        }
        return cn;
    }
}
