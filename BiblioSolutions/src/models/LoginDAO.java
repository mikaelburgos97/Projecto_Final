/*
 * Clase LoginDAO para gestionar la autenticación de usuarios.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.List;
import java.util.ArrayList;
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

        String sql = "select * from Users where username = ? and password = ? and active=1";  // Consulta SQL para buscar al usuario.

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

    public List<LoginDTO> listUsers() {
        String sql = "SELECT * FROM Users";

        List<LoginDTO> datos = new ArrayList<LoginDTO>();
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoginDTO usuario = new LoginDTO();
                usuario.setId(rs.getInt(1));
                usuario.setCedula(rs.getString(2));
                usuario.setUsername(rs.getString(3));
                usuario.setIsActive(rs.getBoolean(4));

                datos.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los usuarios: " + ex);
        }
        return datos;
    }

    public int updateUsuario(LoginDTO user) {
        int resultado = 0;

        String sql = "UPDATE Users SET  username=?, active=?, password=? WHERE id=?";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setBoolean(2, user.isIsActive());
            ps.setString(3, user.getPassword());

            ps.setInt(4, user.getId());
            resultado = ps.executeUpdate();
            System.out.println(resultado);
            if (resultado == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar los datos del usuario: " + e);
        }
        return resultado;
    }

    public int eliminar(String id) {

        int r = 0;

        String sql = "delete from Users where id=" + id;

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al tratar de borrar datos: " + e);
        }
        return r;
    }

    public int agregar(LoginDTO usuario) {
        String sql = "INSERT INTO Users (cedula, username, active, created_at) VALUES (?, ?,?,?)";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getUsername());
            ps.setBoolean(3,false );
            ps.setString(4, LocalDate.now().toString());

            ps.executeUpdate();
            return 1; // Retorna 1 si la inserción fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al tratar de insertar datos: " + e);
            return 0; // Retorna 0 en caso de error
        }
    }

}
