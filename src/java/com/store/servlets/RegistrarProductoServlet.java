/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.store.servlets;

import com.store.modelo.Producto;
import com.store.servicios.ProductoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource; // Para el DataSource
import javax.naming.Context; // Para el JNDI
import javax.naming.InitialContext; // Para el JNDI
import javax.naming.NamingException; // Para el JNDI
import java.sql.Connection; // Para la conexión a la base de datos
import java.sql.SQLException; // Para manejar excepciones de SQL

/**
 *
 * @author deyve
 */
@WebServlet(name = "RegistrarProductoServlet", urlPatterns = {"/RegistrarProductoServlet"})
public class RegistrarProductoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private DataSource dataSource; // Declarar el DataSource

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Obtener el DataSource configurado en context.xml
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
        } catch (NamingException e) {
            throw new ServletException("Error al obtener el DataSource", e);
        }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // Recuperar los parámetros del formulario
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        if (precio <= 0 || stock < 0) {
            // Los valores no son válidos
            String errorMessage = "Por favor, ingrese valores válidos para el precio y el stock.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("formulario.jsp").forward(request, response);
            return;
        }

        // Crear un objeto Producto con los datos
        Producto producto = new Producto(codigo, nombre, precio, stock);

        try (Connection connection = dataSource.getConnection()) {
            // Llamar al servicio para agregar el producto
            ProductoService productoService = new ProductoService(connection);
            productoService.agregarProducto(producto);
        } catch (SQLException e) {
            // Manejar errores de base de datos
            e.printStackTrace(); // Puedes manejar esto mejor en tu aplicación
        }

        // Redirigir a una página de éxito o mostrar un mensaje
        response.sendRedirect("MostrarProductosServlet");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
