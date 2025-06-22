<?php
include 'conexion.php';
$usuario=$_POST['usuario'];
$pass=$_POST['pass'];


$consulta = "UPDATE maestro SET pass = '$pass' WHERE usuario = '$usuario';";

mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);


?>