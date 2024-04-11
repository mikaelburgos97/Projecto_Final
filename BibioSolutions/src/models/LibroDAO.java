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
 * @author Alan Alexander Perez
 */
public class LibroDAO {

    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Extrae de la BD todos los libros (incluyendo propiedades calculadas) y
     * los devuelve como objetos DTO
     *
     * @return Lista de libros
     */
    public List listar() {
        String sql = "select * from Libros";
        List<LibroDTO> datos = new ArrayList<>();
        try {

            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(2));
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
            System.out.println("Error al listar los contactos: " + ex);
        }
        return datos;
    }

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
            return 0; //  0 si se cayo algo
        }
        return 1; // 1 si no dio error
    }

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

            if (r == 1) {
                return 1; // 1 si no dio error
            } else {
                return 0; // 0 si se cayo algo
            }

        } catch (SQLException e) {
            System.out.println("Error al tratar de actualizar datos: " + e);
        }
        return r;
    }

    public int eliminar(int id) {

        int r = 0;

        String sql = "delete from Libros where id=" + id;

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
