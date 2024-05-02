<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/css-bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="css/encontrar.css" rel="stylesheet">
    
    <title>Resultados de Búsqueda</title>
</head>
<body>

    <h1>Resultados de Búsqueda</h1>

    <c:if test="${not empty encontrarproduc}">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Stock</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="producto" items="${encontrarproduc}">
                                <tr>
                                    <td>${producto.codigo}</td>
                                    <td>${producto.nombre}</td>
                                    <td>${producto.precio}</td>
                                    <td>${producto.stock}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="row">
                        <div class="col-md-6 text-start">
                            <a href="/ventas/" class="btn btn-primary">Volver</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${empty encontrarproduc}">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-12">
                    <div class="no-productos">No hay productos disponibles.</div>
                </div>
            </div>
        </div>
        <a href="/ventas/" class="volver">Volver al inicio</a>
    </c:if>

</body>
</html>
