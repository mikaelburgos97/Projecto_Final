/*
 * Clase LibroDAO para gestionar operaciones de la base de datos para libros.
 */
package models;

// Importaciones necesarias para el manejo de SQL y listas.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Definición de la clase LibroDAO que realiza operaciones CRUD en la tabla de Libros.
 * Autor: Alan Alexander Pérez
 */
public class LibroDAO {

    // Instancia de SQLiteConnect para gestionar la conexión a la base de datos.
    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Obtiene todos los libros de la base de datos y los devuelve como una lista de objetos LibroDTO.
     *
     * @return Lista de libros.
     */
    public List listar() {
        String sql = "select * from Libros";
        List<LibroDTO> datos = new ArrayList<>();
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Iterar sobre los resultados y crear objetos LibroDTO para añadirlos a la lista.
            while (rs.next()) {
                System.out.println(rs.getString(2));  // Imprime el precio diario de cada libro.
                LibroDTO l = new LibroDTO();
                l.setId(rs.getInt(1));
                l.setDaily_price(Double.parseDouble(rs.getString(2)));
                l.setBuyed_price(Double.parseDouble(rs.getString(3)));
                l.setTitle(rs.getString(4));
                l.setAutorName(rs.getString(5));
                l.setTotal_stock(Integer.parseInt(rs.getString(6)));
                l.setMora_aument(Double.parseDouble(rs.getString(7)));
                l.setCreated_at(rs.getString(8));
                datos.add(l);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los libros: " + ex);
        }
        return datos;
    }

    // Método para agregar un nuevo libro a la base de datos.
    public int agregar(LibroDTO libro) {
        String sql = "INSERT INTO Libros (daily_price, buyed_price, title, autor, total_stock, mora_aument, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, libro.getDaily_price());
            ps.setDouble(2, libro.getBuyed_price());
            ps.setString(3, libro.getTitle());
            ps.setString(4, libro.getAutorName());
            ps.setInt(5, libro.getTotal_stock());
            ps.setDouble(6, libro.getMora_aument());
            ps.setString(7, libro.getCreated_at());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al tratar de insertar datos: " + e);
            return 0; // Retornar 0 en caso de error.
        }
        return 1; // Retornar 1 si la operación fue exitosa.
    }

    // Método para actualizar la información de un libro existente.
    public int actualizar(LibroDTO libro) {
        int r = 0;
        String sql = "UPDATE Libros SET daily_price=?, buyed_price=?, title=?, autor=?, total_stock=?, mora_aument=?, created_at=? WHERE ID=?";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, libro.getDaily_price());
            ps.setDouble(2, libro.getBuyed_price());
            ps.setString(3, libro.getTitle());
            ps.setString(4, libro.getAutorName());
            ps.setInt(5, libro.getTotal_stock());
            ps.setDouble(6, libro.getMora_aument());
            ps.setString(7, libro.getCreated_at());
            ps.setInt(8, libro.getId());
            r = ps.executeUpdate();

            return (r == 1) ? 1 : 0; // Retorna 1 si fue exitoso, 0 si no.
        } catch (SQLException e) {
            System.out.println("Error al tratar de actualizar datos: " + e);
        }
        return r;
    }

    // Método para eliminar un libro por su ID.
    public int eliminar(int id) {
        int r = 0;
        String sql = "DELETE FROM Libros WHERE id=" + id;

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
            return (r == 1) ? 1 : 0; // Retorna 1 si la eliminación fue exitosa, 0 si no.
        } catch (SQLException e) {
            System.out.println("Error al tratar de borrar datos: " + e);
        }
        return r;
    }

}
