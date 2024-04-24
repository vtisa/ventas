<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Registro de Productos</title>
    <style>
        body {
            background-color: #f8f9fa;
        }

        .centered-form {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 40px;
        }

        .form-control {
            border-radius: 8px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            border-radius: 8px;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
            border-radius: 8px;
        }
    </style>
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
                        <input type="number" class="form-control" id="codigo" name="codigo" required>
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" pattern="^[^0-9]+$" name="nombre" required oninput="return soloLetras(event)">
                    </div>
                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio:</label>
                        <input type="number" class="form-control" id="precio" name="precio" required min="0.01" max="1000.00" step="0.01">
                    </div>
                    <div class="mb-3">
                        <label for="stock" class="form-label">Stock:</label>
                        <input type="number" class="form-control" id="stock" name="stock" required>
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Agregar Producto</button>
                        <a href="/ventas/" class="btn btn-secondary">Volver</a>
                    </div>
                </form>

                <script>
                    function validarFormulario() {
                        var precio = document.getElementById("precio").value;
                        var stock = document.getElementById("stock").value;

                        if (isNaN(precio) || precio <= 0) {
                            alert("El precio debe ser un número positivo.");
                            return false;
                        }

                        if (isNaN(stock) || stock < 0) {
                            alert("El stock debe ser un número entero positivo.");
                            return false;
                        }

                        return true; 
                    }

                    function soloLetras(event) {
                        var input = event.target;
                        var valor = input.value;

                        valor = valor.replace(/[0-9]/g, '');

                        input.value = valor;

                        return true;
                    }
                </script>
            </div>
        </div>
    </div>
</body>
</html>
