/*
 * Clase ClienteDTO utilizada para representar los datos de un cliente.
 */
package models;

/**
 * Definición de la clase ClienteDTO que encapsula los datos de un cliente.
 * Autor: Alan Alexander Pérez
 */
public class ClienteDTO {

    // Variables privadas para almacenar las propiedades de un cliente.
    int Id;
    String cedula;
    String nombreCompleto;
    String calle;
    String telefono;
    String correo;
    boolean hasPendingOrders;  // Indica si el cliente tiene pedidos pendientes.

    // Método para obtener el ID del cliente.
    public int getId() {
        return Id;
    }

    // Método para establecer el ID del cliente.
    public void setId(int Id) {
        this.Id = Id;
    }

    // Método para obtener el estado de pedidos pendientes del cliente.
    public boolean getHasPendingOrders() {
        return hasPendingOrders;
    }

    // Método para establecer el estado de pedidos pendientes del cliente.
    public void setHasPendingOrders(boolean hasPendingOrders) {
        this.hasPendingOrders = hasPendingOrders;
    }

    // Método para obtener la cédula del cliente.
    public String getCedula() {
        return cedula;
    }

    // Método para establecer la cédula del cliente.
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // Método para obtener el nombre completo del cliente.
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    // Método para establecer el nombre completo del cliente.
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    // Método para obtener la calle de residencia del cliente.
    public String getCalle() {
        return calle;
    }

    // Método para establecer la calle de residencia del cliente.
    public void setCalle(String calle) {
        this.calle = calle;
    }

    // Método para obtener el teléfono del cliente.
    public String getTelefono() {
        return telefono;
    }

    // Método para establecer el teléfono del cliente.
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método para obtener el correo electrónico del cliente.
    public String getCorreo() {
        return correo;
    }

    // Método para establecer el correo electrónico del cliente.
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
