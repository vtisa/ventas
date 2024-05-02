<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/css-bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="css/registrar.css" rel="stylesheet">
    <title>Registro de Productos</title>
</head>
<body>
    <div class="container centered-form">
        <div class="card">
            <div class="card-body">
                <h1 class="card-title text-center">Registro de Productos</h1>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
                <% } %>

                <form action="RegistrarServlet" method="post" onsubmit="return validarFormulario();">
                    <div class="mb-3">
                        <label for="codigo" class="form-label">Código:</label>
                        <input type="text" class="form-control" id="codigo" name="codigo" required>
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" pattern="^[^0-9]+$" name="nombre" title="solo letras">
                    </div>
                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio:</label>
                        <input type="number" class="form-control" id="precio" name="precio" step="0.01">
                    </div>
                    <div class="mb-3">
                        <label for="stock" class="form-label">Stock:</label>
                        <input type="number" class="form-control" id="stock" name="stock" >
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Agregar Producto</button>
                        <button type="button" class="btn btn-danger" onclick="limpiarCampos()">Limpiar</button>
                        <a href="/ventas/" class="btn btn-secondary">Volver</a>
                    </div>
                </form>

            </div>
        </div>
    </div>
 <script src="js/registro.js"></script>
</body>
</html>

