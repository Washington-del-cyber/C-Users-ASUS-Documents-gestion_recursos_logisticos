package com.logistica.service;

import com.logistica.db.DBConnection;
import com.logistica.model.Pedido;
import com.logistica.model.PedidoItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    public static void crearPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido(cliente, estado, prioridad, destino) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pedido.getCliente());
            ps.setString(2, pedido.getEstado());
            ps.setString(3, pedido.getPrioridad());
            ps.setString(4, pedido.getDestino());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) pedido.setId(rs.getInt(1));
            }
        }
        for (PedidoItem item : pedido.getItems()) {
            crearPedidoItem(pedido.getId(), item);
        }
    }
    private static void crearPedidoItem(int pedidoId, PedidoItem item) throws SQLException {
        String sql = "INSERT INTO pedido_item(pedido_id, producto_id, cantidad) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pedidoId);
            ps.setInt(2, item.getProductoId());
            ps.setInt(3, item.getCantidad());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) item.setId(rs.getInt(1));
            }
        }
    }
    public static List<Pedido> listarPedidos() throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setCliente(rs.getString("cliente"));
                p.setEstado(rs.getString("estado"));
                p.setPrioridad(rs.getString("prioridad"));
                p.setDestino(rs.getString("destino"));
                // cargar items
                lista.add(p);
            }
        }
        return lista;
    }
    public static void eliminarPedido(int id) throws SQLException {
        String sqlItems = "DELETE FROM pedido_item WHERE pedido_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlItems)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        String sqlPedido = "DELETE FROM pedido WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlPedido)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
