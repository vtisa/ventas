<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="css/css-bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="css/producto.css" rel="stylesheet">
    <title>Lista de Productos</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Lista de Productos</h1>

            <c:choose>
                <c:when test="${not empty productos}">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th>Código</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Stock</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="producto" items="${productos}">
                                <tr>
                                    <td>${producto.codigo}</td>
                                    <td>${producto.nombre}</td>
                                    <td>${producto.precio}</td>
                                    <td>${producto.stock}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="no-productos">No hay productos disponibles.</p>
                </c:otherwise>
            </c:choose>

            <div class="row justify-content-end">
                <div class="col-md-12 text-end">
                    <a href="registrar.jsp" class="btn btn-primary btn-lg">Agregar</a>
                    <a href="buscarproduc.jsp" class="btn btn-primary btn-lg">Buscar producto</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>