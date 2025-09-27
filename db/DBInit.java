package com.logistica.db;

import java.sql.Connection;
import java.sql.Statement;

public class DBInit {
    public static void createTables() throws Exception {
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement()) {

            s.execute("CREATE TABLE IF NOT EXISTS producto (" +
                      "id INT AUTO_INCREMENT PRIMARY KEY, " +
                      "nombre VARCHAR(200) NOT NULL, " +
                      "descripcion VARCHAR(1000), " +
                      "peso DOUBLE, " +
                      "volumen DOUBLE, " +
                      "categoria VARCHAR(100), " +
                      "activo BOOLEAN DEFAULT TRUE" +
                      ")");

            s.execute("CREATE TABLE IF NOT EXISTS almacen (" +
                      "id INT AUTO_INCREMENT PRIMARY KEY, " +
                      "nombre VARCHAR(200), " +
                      "ubicacion VARCHAR(300), " +
                      "capacidad DOUBLE" +
                      ")");

            s.execute("CREATE TABLE IF NOT EXISTS pedido (" +
                      "id INT AUTO_INCREMENT PRIMARY KEY, " +
                      "cliente VARCHAR(300), " +
                      "estado VARCHAR(50), " +
                      "prioridad VARCHAR(50), " +
                      "destino VARCHAR(500), " +
                      "fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                      ")");

            s.execute("CREATE TABLE IF NOT EXISTS pedido_item (" +
                      "id INT AUTO_INCREMENT PRIMARY KEY, " +
                      "pedido_id INT, " +
                      "producto_id INT, " +
                      "cantidad INT, " +
                      "FOREIGN KEY(pedido_id) REFERENCES pedido(id), " +
                      "FOREIGN KEY(producto_id) REFERENCES producto(id)" +
                      ")");
        }
    }
}
