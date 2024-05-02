
package patron.servlets;

import patron.modelo.Producto;
import patron.servicios.ProductoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "RegistrarServlet", urlPatterns = {"/RegistrarServlet"})
public class RegistrarServlet extends HttpServlet {

    private String jdbcURL = "jdbc:mysql://localhost:3306/ventas";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        // Recuperar los parámetros del formulario
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        if (precio < 0 ) {
            // Los valores no son válidos
            String errorMessage = "Por favor, ingrese un valor positivo para el precio.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("registrar.jsp").forward(request, response);
            return;
        }
        if ( stock < 0) {
            // Los valores no son válidos
            String errorMessage = "Por favor, ingrese valores positivos para el stock.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("registrar.jsp").forward(request, response);
            return;
        }

        // Crear un objeto Producto con los datos
        Producto producto = new Producto(codigo, nombre, precio, stock);

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            // Llamar al servicio para agregar el producto
            ProductoService productoService = new ProductoService(connection);
            productoService.agregarProducto(producto);
        } catch (SQLException e) {
            // Manejar errores de base de datos
            e.printStackTrace(); // Puedes manejar esto mejor en tu aplicación
        }

        // Redirigir a una página de éxito o mostrar un mensaje
        response.sendRedirect("MostrarServlet");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}