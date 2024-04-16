/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.store.servicios;

import com.store.dao.ProductoDAO;
import com.store.modelo.Producto;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author deyve
 */
public class ProductoService {
    private final ProductoDAO productoDAO;

    public ProductoService(Connection conexion) {
        productoDAO = new ProductoDAO(conexion);
    }

    // Método para agregar un producto a la base de datos
    public void agregarProducto(Producto producto) {
        // Puedes agregar lógica de validación aquí si es necesario
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
