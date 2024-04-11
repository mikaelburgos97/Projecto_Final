/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan Alexander Pérez
 */


public class LoginDAO {
    
    SQLiteConnect conectar = new SQLiteConnect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Este metodo se encarga de listar un registro.El mismo devuelve un objeto
     * tipo arrayList.
     *
     * @param username
     * @param password
     * @return datos
     */
    public boolean realizarLogin(String username, String password) {
        con = conectar.conectar();

        String sql = "select * from Users where username = ? and password = ?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                con.close();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
}
