/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package models;


public class mainExec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginDAO ins = new LoginDAO();
        System.out.println( ins.realizarLogin("test_user", "12345"));
    }
    
    

    
}
    
