package com.mycompany.crud_api_web.resources;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;

    public Producto(int id, String nombre, double precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

}
