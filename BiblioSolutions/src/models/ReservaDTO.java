/*
 * Clase ReservaDTO utilizada para representar y manipular los datos de reservaciones.
 */
package models;

/**
 * Clase que define los atributos y métodos para manipular los datos de las reservas.
 * Autor: Alan Alexander Pérez
 */
public class ReservaDTO {
    // Atributos de la clase con su tipo y propósito.
    int id; // Identificador único para la reserva.
    String fecha_peticion; // Fecha en la que se realizó la petición de la reserva.
    String fecha_entrega; // Fecha prevista para la entrega del libro reservado.
    int libro_id; // Identificador del libro reservado.
    String estado; // Estado actual de la reserva.
    int cliente_id; // Identificador del cliente que realizó la reserva.

    // Métodos getters y setters para cada atributo, permitiendo obtener y establecer sus valores.
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_peticion() {
        return fecha_peticion;
    }

    public void setFecha_peticion(String fecha_peticion) {
        this.fecha_peticion = fecha_peticion;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }
}
