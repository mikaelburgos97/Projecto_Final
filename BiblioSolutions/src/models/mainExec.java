/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package models;

import java.time.LocalDate;
import models.utils.DateHandler;

public class mainExec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Funciones de login
        LoginDAO ins = new LoginDAO();

        // Devuelve true o false usanla para determinar si pasa o no el usuario
        System.out.println(ins.realizarLogin("test_user", "12345"));

        LibroDAO insLib = new LibroDAO();

        // Listar los libros existentes, hay algunas propiedades calculadas pero todos se devolveran
        // en el tipo List<LibroDTO> 
        System.out.println(insLib.listar());

        
        
        
        
        
        // Agregar un libro a la BD, descomentenlo o copienlo cuando quieran usarlo.
        /*
        LibroDTO libro = new LibroDTO();
        libro.setDaily_price(10.99);
        libro.setBuyed_price(20.50);
        libro.setTitle("El Gran Libro de Java");
        libro.setAutorName("Juan Pérez");
        libro.setTotal_stock(50);
        libro.setDesc_ubicacion("Estantería A1");
        libro.setMora_aument(0.5);
        libro.setTotal_ganancies(500.0);
        libro.setCreated_at(DateHandler.parseDateToString(LocalDate.now()));
        int resultado = insLib.agregar(libro);

        if (resultado == 1) {
            System.out.println("Libro insertado");
        } else {
            System.out.println("Error");
        }*/
        
        
        
        
        
        // Actualizar datos en la BD, para que funcione sin errores el id 2 debe existir (validenlo con DB Browse),
        //descomentenlo o copienlo cuando quieran usarlo.
        /*
         LibroDTO libro = new LibroDTO();
        libro.setId(2);
        libro.setDaily_price(12.99);
        libro.setBuyed_price(20.50);
        libro.setTitle("El Gran Libro de Python");
        libro.setAutorName("Juan Pérez");
        libro.setTotal_stock(50);
        libro.setDesc_ubicacion("Estantería A1");
        libro.setMora_aument(0.5);
        libro.setTotal_ganancies(500.0);
        libro.setCreated_at(DateHandler.parseDateToString(LocalDate.now()));
        int resultado = insLib.actualizar(libro);
        
         if (resultado == 1) {
            System.out.println("Libro actualizado ");
        } else {
            System.out.println("Error");
        }
         */
        
        
        
        
        
        // Eliminar datos de la BD, para que funcione sin errores el id 2 debe existir (validenlo con DB Browse)
        // descomentenlo o copienlo cuando quieran usarlo.
        /*
        int resultado = insLib.eliminar(2);
        
         if (resultado == 1) {
            System.out.println("Libro Borrado ");
        } else {
            System.out.println("Error");
        }
         */
    }

}
