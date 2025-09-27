package com.logistica.service;

import com.logistica.db.DBConnection;
import com.logistica.model.Almacen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlmacenService {
    public static void crearAlmacen(Almacen a) throws SQLException {
        String sql = "INSERT INTO almacen(nombre, ubicacion, capacidad) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getUbicacion());
            ps.setDouble(3, a.getCapacidad());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setId(rs.getInt(1));
            }
        }
    }
    public static List<Almacen> listarAlmacenes() throws SQLException {
        List<Almacen> lista = new ArrayList<>();
        String sql = "SELECT * FROM almacen";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Almacen(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDouble("capacidad")
                ));
            }
        }
        return lista;
    }
    public static void eliminarAlmacen(int id) throws SQLException {
        String sql = "DELETE FROM almacen WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

