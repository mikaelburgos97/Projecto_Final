/*
 * Clase ReservaDAO para gestionar las operaciones de base de datos de reservaciones.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que define los métodos para realizar operaciones CRUD en la tabla de Reservaciones.
 * Autor: Alan Alexander Pérez
 */
public class ReservaDAO {

    // Instancia de SQLiteConnect para gestionar la conexión a la base de datos.
    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Obtiene todas las reservaciones de la base de datos y las devuelve como una lista de objetos ReservaDTO.
     *
     * @return lista de reservas
     */
    public List<ReservaDTO> listar() {
        String sql = "SELECT * FROM Reservaciones";
        List<ReservaDTO> reservas = new ArrayList<>();

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ReservaDTO reserva = new ReservaDTO();
                reserva.setId(rs.getInt(1));
                reserva.setFecha_peticion(rs.getString(2));
                reserva.setFecha_entrega(rs.getString(3));
                reserva.setLibro_id(rs.getInt(4));
                reserva.setEstado(rs.getString(5));
                reserva.setCliente_id(rs.getInt(6));

                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las reservas: " + e);
        }
        return reservas;
    }

    /**
     * Agrega una nueva reserva a la base de datos.
     *
     * @param reserva la reserva a agregar
     * @return 1 si la operación es exitosa, 0 si hay un error
     */
    public int agregar(ReservaDTO reserva) {
        String sql = "INSERT INTO Reservaciones (fecha_peticion, fecha_entrega, libro_id, estado, cliente_id) VALUES (?, ?, ?, ?, ?)";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, reserva.getFecha_peticion());
            ps.setString(2, reserva.getFecha_entrega());
            ps.setInt(3, reserva.getLibro_id());
            ps.setString(4, reserva.getEstado());
            ps.setInt(5, reserva.getCliente_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al tratar de insertar datos: " + e);
            return 0; // Indica un error
        }
        return 1; // Indica éxito
    }

    /**
     * Actualiza una reserva existente en la base de datos.
     *
     * @param reserva la reserva a actualizar
     * @return 1 si la operación es exitosa, 0 si hay un error
     */
    public int actualizar(ReservaDTO reserva) {
        String sql = "UPDATE Reservaciones SET fecha_peticion=?, fecha_entrega=?, libro_id=?, estado=?, cliente_id=? WHERE id=?";

        try (Connection con = conectar.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reserva.getFecha_peticion());
            ps.setString(2, reserva.getFecha_entrega());
            ps.setInt(3, reserva.getLibro_id());
            ps.setString(4, reserva.getEstado());
            ps.setInt(5, reserva.getCliente_id());
            ps.setInt(6, reserva.getId());

            return ps.executeUpdate(); // Retorna el número de filas afectadas
        } catch (SQLException e) {
            System.out.println("Error al tratar de actualizar datos: " + e);
            return 0; // Indica un error
        }
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id el ID de la reserva a eliminar
     * @return 1 si la operación es exitosa, 0 si hay un error
     */
    public int eliminar(int id) {
        int r = 0;
        String sql = "DELETE FROM Reservaciones WHERE id=" + id;

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
            return (r == 1) ? 1 : 0; // Retorna 1 si es exitoso, 0 si no
        } catch (SQLException e) {
            System.out.println("Error al tratar de borrar datos: " + e);
            return 0; // Indica un error
        }
    }
}
