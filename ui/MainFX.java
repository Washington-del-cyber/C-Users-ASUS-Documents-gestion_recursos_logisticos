package com.logistica.ui;

import com.logistica.db.DBInit;
import com.logistica.model.Producto;
import com.logistica.service.ProductoService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class MainFX extends Application {
    private TableView<Producto> table;
    private ObservableList<Producto> data;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        try {
            DBInit.createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new TableView<>();
        TableColumn<Producto, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Producto, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Producto, String> catCol = new TableColumn<>("Categoría");
        catCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        TableColumn<Producto, Integer> descripcionCol = new TableColumn<>("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        table.getColumns().addAll(idCol, nameCol, catCol, descripcionCol);
        refreshTable();
        TextField tfNombre = new TextField();
        tfNombre.setPromptText("Nombre");
        TextField tfDescripcion = new TextField();
        tfDescripcion.setPromptText("descripcion");
        TextField tfCategoria = new TextField();
        tfCategoria.setPromptText("Categoría");
    
        Button btnAgregar = new Button(" AGREGAR ");
        btnAgregar.setOnAction(ev -> {
            try {
                Producto p = new Producto();
                p.setNombre(tfNombre.getText());
                p.setCategoria(tfCategoria.getText());
                p.setDescripcion(tfDescripcion.getText());
                p.setPeso(0);
                p.setVolumen(0);
                ProductoService.crearProducto(p);
                tfNombre.clear(); tfCategoria.clear();
                refreshTable();
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });
        Button btnEliminar = new Button(" ELIMINAR ");
        btnEliminar.setOnAction(ev -> {
            Producto sel = table.getSelectionModel().getSelectedItem();
            if (sel != null) {
                try {
                    ProductoService.eliminarProducto(sel.getId());
                    refreshTable();
                } catch (Exception ex) {
                    showAlert(" >>> Error <<< ", ex.getMessage());
                }
            }
        });
        HBox form = new HBox(8, tfNombre, tfCategoria,tfDescripcion, btnAgregar, btnEliminar);
        form.setPadding(new Insets(10));
        VBox root = new VBox(10, table, form);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle(" GESTION DE PRODUCTOS - JavaFX");
        stage.show();
    }
    private void refreshTable() {
        try {
            List<Producto> list = ProductoService.listarProductos();
            data = FXCollections.observableArrayList(list);
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setTitle(title);
        a.showAndWait();
    }
}
