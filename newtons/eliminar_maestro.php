<?php
include 'conexion.php';
$usuario=$_POST['usuario'];


$consulta="DELETE FROM maestro WHERE usuario='$usuario'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);


?>