
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
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "BuscarServlet", urlPatterns = {"/BuscarServlet"})
public class BuscarServlet extends HttpServlet {

    
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
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

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
         String nombre = request.getParameter("nombre"); // Obtener el nombre a buscar desde el formulario
        
        try (Connection connection = dataSource.getConnection()) {
            ProductoService productoService = new ProductoService(connection);
            List<Producto> productos = productoService.buscarProductosPorNombre(nombre);
            
            // Puedes almacenar la lista de productos encontrados en el alcance de solicitud
            request.setAttribute("encontrarproduc", productos);
            
            // Redirigir a la página JSP que muestra los productos encontrados
            request.getRequestDispatcher("encontrarproduc.jsp").forward(request, response);
        } catch (SQLException e) {
            // Manejar errores de base de datos
            e.printStackTrace(); // Puedes manejar esto mejor en tu aplicación
        }
        
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
