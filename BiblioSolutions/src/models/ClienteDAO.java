/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Alan Alexander Pérez
 */
public class ClienteDAO {

    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<ClienteDTO> listar() {
        String sql = "SELECT DISTINCT c.*, \n" +
                    "    CASE WHEN EXISTS (\n" +
                    "        SELECT 1 \n" +
                    "        FROM Reservas r \n" +
                    "        WHERE r.cliente_id = c.Id \n" +
                    "        AND r.status = 'Pendiente'\n" +
                    "    ) THEN TRUE ELSE FALSE END AS tiene_reserva_pendiente\n" +
                    "FROM Clientes c";
        List<ClienteDTO> datos = new ArrayList<>();

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setId(Integer.parseInt(rs.getString(1)));
                cliente.setCedula(rs.getString(2));
                cliente.setNombreCompleto(rs.getString(3));
                cliente.setCalle(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                cliente.setCorreo(rs.getString(6));
                System.out.println(rs.getString(7));
                cliente.setHasPendingOrders(rs.getBoolean(7));
                
                datos.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los clientes: " + ex);
        }
        return datos;
    }

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
            return 0; // 0 error
        }
        return 1; // 1 todo nice
    }

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

            if (resultado == 1) {
                return 1; // 1 si no dio error
            } else {
                return 0; // 0 si ocurrió un error
            }
        } catch (SQLException e) {
            System.out.println("Error al tratar de actualizar datos: " + e);
        }
        return resultado;
    }
    
    
       public int eliminar(int id) {

        int r = 0;

        String sql = "delete from Clientes where id=" + id;

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
