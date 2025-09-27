package com.logistica.model;

import java.util.ArrayList;
import java.util.List;
public class Pedido {
    private int id;
    private String cliente;
    private String estado;
    private String prioridad;
    private String destino;
    private List<PedidoItem> items = new ArrayList<>();

    public Pedido() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public List<PedidoItem> getItems() { return items; }
    public void setItems(List<PedidoItem> items) { this.items = items; }
    public void addItem(PedidoItem item) { this.items.add(item); }
    @Override
    public String toString() {
        return "Pedido{id=" + id + ", cliente='" + cliente + "', destino='" + destino + "', items=" + items + "}";
    }
}


