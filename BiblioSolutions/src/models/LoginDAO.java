/*
 * Clase LoginDAO para gestionar la autenticación de usuarios.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que define los métodos para realizar operaciones de inicio de sesión.
 * Autor: Alan Alexander Pérez
 */
public class LoginDAO {
    
    // Instancia de SQLiteConnect para gestionar la conexión a la base de datos.
    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Realiza el proceso de verificación de las credenciales de un usuario.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean realizarLogin(String username, String password) {
        con = conectar.conectar();  // Establece conexión con la base de datos.

        String sql = "select * from Users where username = ? and password = ?";  // Consulta SQL para buscar al usuario.

        try {
            ps = con.prepareStatement(sql);  // Prepara la consulta SQL.

            ps.setString(1, username);  // Establece el nombre de usuario en el primer parámetro de la consulta.
            ps.setString(2, password);  // Establece la contraseña en el segundo parámetro de la consulta.

            rs = ps.executeQuery();  // Ejecuta la consulta y obtiene los resultados.

            while (rs.next()) {  // Verifica si existe al menos un resultado.
                con.close();  // Cierra la conexión con la base de datos.
                return true;  // Retorna true, indicando que las credenciales son válidas.
            }

        } catch (SQLException ex) {  // Manejo de excepciones SQL.
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);  // Loguea el error.
            return false;  // Retorna false, indicando que ocurrió un error.
        }
        return false;  // Retorna false si no se encontraron coincidencias en la base de datos.
    }
}
