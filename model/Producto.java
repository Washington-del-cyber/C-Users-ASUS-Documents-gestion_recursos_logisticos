package com.logistica.model;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double peso;
    private double volumen;
    private String categoria;
    private boolean activo = true;

    public Producto() {}
    public Producto(int id, String nombre, String descripcion, double peso, double volumen, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.volumen = volumen;
        this.categoria = categoria;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public double getVolumen() { return volumen; }
    public void setVolumen(double volumen) { this.volumen = volumen; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', categoria='%s', activo=%s}", id, nombre, categoria, activo);
    }
}

