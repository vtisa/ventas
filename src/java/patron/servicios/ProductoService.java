package patron.servicios;

import patron.dao.ProductoDAO;
import patron.modelo.Producto;
import java.sql.Connection;
import java.util.List;

public class ProductoService {
    private final ProductoDAO productoDAO;

    public ProductoService(Connection conexion) {
        productoDAO = new ProductoDAO(conexion);
    }

    public void agregarProducto(Producto producto) {
        productoDAO.insertarProducto(producto);
    }

    // Método para obtener la lista de productos desde la base de datos
    public List<Producto> obtenerProductos() {
        return productoDAO.obtenerTodosLosProductos();
    }

    // Método para buscar productos por nombre
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoDAO.buscarProductosPorNombre(nombre);
    }
}
