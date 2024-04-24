<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Buscar Producto</title>
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            max-width: 400px;
            margin: 100px auto;
        }

        h1 {
            font-size: 28px;
            text-align: center;
            margin-bottom: 30px;
            color: #007bff;
        }

        form {
            padding: 20px;
            border: 1px solid #ced4da;
            border-radius: 10px;
            background-color: #fff;
        }

        label, input {
            display: block;
            width: 100%;
            margin-bottom: 15px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .volver {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 18px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Buscar Producto (por nombre)</h1>

    <form action="BuscarServlet" method="post">
        <label for="nombre">Nombre del Producto:</label>
        <input type="text" id="nombre" pattern="^[^0-9]+$" name="nombre" required oninput="return soloLetras(event)">
        <button type="submit">Buscar</button>
    </form>

    <a href="/ventas/" class="volver">Volver al inicio</a>
</div>

<script>
    function soloLetras(event) {
        var input = event.target;
        var valor = input.value;
        valor = valor.replace(/[0-9]/g, '');
        input.value = valor;
        return true;
    }
</script>

</body>
</html>
