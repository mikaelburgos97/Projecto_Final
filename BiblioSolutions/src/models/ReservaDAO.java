/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alan Alexander Pérez
 */
public class ReservaDAO {

    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

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
                reserva.setFecha_entrega(rs.getInt(3));
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

    public int agregar(ReservaDTO reserva) {
        String sql = "INSERT INTO Reservaciones (fecha_peticion, fecha_entrega, libro_id, estado, cliente_id) VALUES (?, ?, ?, ?, ?)";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, reserva.getFecha_peticion());
            ps.setInt(2, reserva.getFecha_entrega());
            ps.setInt(3, reserva.getLibro_id());
            ps.setString(4, reserva.getEstado());
            ps.setInt(5, reserva.getCliente_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al tratar de insertar datos: " + e);
            return 0; // 0 si ocurrió un error
        }
        return 1; // 1 si la inserción se realizó correctamente
    }

    public int actualizar(ReservaDTO reserva) {
        int resultado = 0;
        String sql = "UPDATE Reservaciones SET fecha_peticion=?, fecha_entrega=?, libro_id=?, estado=?, cliente_id=? WHERE id=?";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, reserva.getFecha_peticion());
            ps.setInt(2, reserva.getFecha_entrega());
            ps.setInt(3, reserva.getLibro_id());
            ps.setString(4, reserva.getEstado());
            ps.setInt(5, reserva.getCliente_id());
            ps.setInt(6, reserva.getId());

            resultado = ps.executeUpdate();

            if (resultado == 1) {
                return 1; // 1 si la actualización se realizó correctamente
            } else {
                return 0; // 0 si ocurrió un error durante la actualización
            }
        } catch (SQLException e) {
            System.out.println("Error al tratar de actualizar datos: " + e);
        }
        return resultado;
    }

    public int eliminar(int id) {
        int r = 0;
        String sql = "DELETE FROM Reservaciones WHERE id=" + id;

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
}