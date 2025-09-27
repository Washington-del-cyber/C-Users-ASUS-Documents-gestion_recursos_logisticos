package com.logistica.ui;
import com.logistica.db.DBInit;
import com.logistica.model.Producto;
import com.logistica.service.ProductoService;
import java.util.List;
import java.util.Scanner;

public class ConsolaApp {
    public static void main(String[] args) {
        try {
            DBInit.createTables();
            Scanner sc = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("\n *** GESTOR LOGISTICO (MENU) *** ");
                System.out.println("1. Registrar producto");
                System.out.println("2. Listar productos");
                System.out.println("3. Eliminar producto");
                System.out.println("4. Salir");
                System.out.print("Opción: ");
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: "); String nombre = sc.nextLine();
                        System.out.print("Descripción: "); String desc = sc.nextLine();
                        System.out.print("Peso: "); double peso = Double.parseDouble(sc.nextLine());
                        System.out.print("Volumen: "); double vol = Double.parseDouble(sc.nextLine());
                        System.out.print("Categoría: "); String cat = sc.nextLine();
                        Producto p = new Producto(0, nombre, desc, peso, vol, cat);
                        ProductoService.crearProducto(p);
                        System.out.println("Producto creado con ID: " + p.getId());
                    }
                    case 2 -> {
                        List<Producto> lista = ProductoService.listarProductos();
                        lista.forEach(System.out::println);
                    }
                    case 3 -> {
                        System.out.print("ID a eliminar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        ProductoService.eliminarProducto(id);
                        System.out.println("Eliminado.");
                    }
                    case 4 -> System.out.println(" FINALIZADO ");
                    default -> System.out.println("Opción inválida");
                }
            } while (opcion != 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
