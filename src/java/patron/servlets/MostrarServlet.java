package patron.servlets;

import patron.modelo.Producto;
import patron.servicios.ProductoService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "MostrarServlet", urlPatterns = {"/MostrarServlet"})
public class MostrarServlet extends HttpServlet {

    private String jdbcURL = "jdbc:mysql://localhost:3306/ventas";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            ProductoService productoService = new ProductoService(connection);
            List<Producto> productos = productoService.obtenerProductos();

            // Puedes almacenar la lista de productos en el alcance de solicitud
            request.setAttribute("productos", productos);

            // Redirigir a la página JSP que muestra la lista de productos
            request.getRequestDispatcher("productos.jsp").forward(request, response);
        } catch (SQLException e) {
            // Manejar errores de base de datos
            e.printStackTrace(); // Puedes manejar esto mejor en tu aplicación
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
