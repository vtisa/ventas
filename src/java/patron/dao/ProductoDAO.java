
package patron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import patron.modelo.Producto;


public class ProductoDAO {
    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarProducto(Producto producto) {
        String sql = "INSERT INTO producto (codigo, nombre, precio, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones
        }
    }

    // Método para obtener todos los productos desde la base de datos
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Producto producto = new Producto(
                    resultSet.getString("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("stock")
                );
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
        
        }
        return listaProductos;
    }

    // Método para buscar productos por nombre en la base de datos
    public List<Producto> buscarProductosPorNombre(String nombre) {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE nombre LIKE ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, "%" + nombre + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Producto producto = new Producto(
                        resultSet.getString("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio"),
                        resultSet.getInt("stock")
                    );
                    listaProductos.add(producto);
                }
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
        return listaProductos;
    }
}
