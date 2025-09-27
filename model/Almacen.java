package com.logistica.model;

public class Almacen {
    private int id;
    private String nombre;
    private String ubicacion;
    private double capacidad;
    public Almacen() {}
    public Almacen(int id, String nombre, String ubicacion, double capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public double getCapacidad() { return capacidad; }
    public void setCapacidad(double capacidad) { this.capacidad = capacidad; }
    @Override
    public String toString() { return "Almacen{id=" + id + ", nombre='" + nombre + "'}"; }
}

