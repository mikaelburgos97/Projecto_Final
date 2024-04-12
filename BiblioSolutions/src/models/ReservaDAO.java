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
import models.utils.DateHandler;

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
        String sql = "SELECT * FROM Reservas";
        List<ReservaDTO> reservas = new ArrayList<>();

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ReservaDTO reserva = new ReservaDTO();
                reserva.setId(rs.getInt(1));
                reserva.setCreated_at(DateHandler.parseStringToDate(rs.getString(2)));
                reserva.setDelivered_at(DateHandler.parseStringToDate(rs.getString(3)));
                reserva.setStatus(rs.getString(4));
                reserva.setBook_id(rs.getString(5));
                reserva.setClient_id(rs.getString(6));

                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las reservas: " + e);
        }
        return reservas;
    }

    public int agregar(ReservaDTO reserva) {
        String sql = "INSERT INTO Reservas (created_at, delivered_at, status, book_id, client_id) VALUES (?, ?, ?, ?, ?)";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, reserva.getCreated_at());
            ps.setObject(2, reserva.getDelivered_at());
            ps.setString(3, reserva.getStatus());
            ps.setString(4, reserva.getBook_id());
            ps.setString(5, reserva.getClient_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al tratar de insertar datos: " + e);
            return 0; // 0 si ocurrió un error
        }
        return 1; // 1 si la inserción se realizó correctamente
    }

    public int actualizar(ReservaDTO reserva) {
        int resultado = 0;
        String sql = "UPDATE Reservas SET created_at=?, delivered_at=?, status=?, book_id=?, client_id=? WHERE id=?";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, reserva.getCreated_at());
            ps.setObject(2, reserva.getDelivered_at());
            ps.setString(3, reserva.getStatus());
            ps.setString(4, reserva.getBook_id());
            ps.setString(5, reserva.getClient_id());
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

        String sql = "delete from Reservas where id=" + id;

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
