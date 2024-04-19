/*
 * Plantilla de clase ClienteDAO para manejar operaciones de base de datos para clientes.
 */
package models;

// Importaciones necesarias para el manejo de SQL y listas.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 * Definición de la clase ClienteDAO para realizar operaciones CRUD en la base de datos.
 * Autor: Alan Alexander Pérez
 */
public class ClienteDAO {

    // Instancia de SQLiteConnect para gestionar la conexión a la base de datos.
    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Método para listar todos los clientes de la tabla Clientes.
    public List<ClienteDTO> listar(){
        String sql = "Select * FROM Clientes";
        List<ClienteDTO> datos = new ArrayList<>();
        
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            // Iterar sobre los resultados para crear y añadir objetos ClienteDTO a la lista.
            while (rs.next()){
                ClienteDTO cliente = new ClienteDTO();
                cliente.setId(Integer.parseInt(rs.getString(1)));
                cliente.setCedula(rs.getString(2));
                cliente.setNombreCompleto(rs.getString(3));
                cliente.setCalle(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                cliente.setCorreo(rs.getString(6));
                
                datos.add(cliente);
            }
        } catch(SQLException ex) {
            System.out.println("Error al listar los clientes: " + ex);
        }
        
        return datos;
    }

    // Método para agregar un nuevo cliente a la base de datos.
    public int agregar(ClienteDTO cliente) {
        String sql = "INSERT INTO Clientes (Cedula, NombreCompleto, Calle, Telefono, Correo) VALUES (?, ?, ?, ?, ?)";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getCalle());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al tratar de insertar datos: " + e);
            return 0; // Retornar 0 en caso de error.
        }
        return 1; // Retornar 1 si la operación fue exitosa.
    }

    // Método para actualizar la información de un cliente existente.
    public int actualizar(ClienteDTO cliente) {
        int resultado = 0;
        String sql = "UPDATE Clientes SET Cedula=?, NombreCompleto=?, Calle=?, Telefono=?, Correo=? WHERE Id=?";
        
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getCalle());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.setInt(6, cliente.getId());
            
            resultado = ps.executeUpdate();
            return resultado; // Retorna 1 si fue exitoso, 0 si no.
        } catch (SQLException e) {
            System.out.println("Error al tratar de actualizar datos: " + e);
        }
        return resultado;
    }
    
    // Método para eliminar un cliente por su ID.
    public int eliminar(int id) {
        int r = 0;
        String sql = "DELETE FROM Clientes WHERE Id=" + id;
        
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
            return r; // Retorna 1 si la eliminación fue exitosa, 0 si no.
        } catch (SQLException e) {
            System.out.println("Error al tratar de borrar datos: " + e);
        }
        return r;
    }
}
