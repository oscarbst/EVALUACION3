package com.mycompany.api.dao;

import com.mycompany.crud_api_web.resources.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection con;

    public void establecerConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=crud_database";
            String user = "tu_usuario";
            String password = "tu_contraseña";
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void agregarProducto(Producto producto) {
        try {
            String query = "INSERT INTO productos (nombre, precio, descripcion) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, producto.getPrecio());
            pst.setString(3, producto.getDescripcion());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public void actualizarProducto(Producto producto) {
        try {
            String query = "UPDATE productos SET nombre = ?, precio = ?, descripcion = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, producto.getPrecio());
            pst.setString(3, producto.getDescripcion());
            pst.setInt(4, producto.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        try {
            String query = "DELETE FROM productos WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");
            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;
        try {
            String query = "SELECT * FROM productos WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener producto por ID: " + e.getMessage());
        }
        return producto;
    }
}

