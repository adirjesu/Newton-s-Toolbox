<?php
include 'conexion.php';

$id_reactivo=$_POST['id_reactivo'];
$reactivo=$_POST['reactivo'];
$respuesta=$_POST['respuesta'];

$consulta = "UPDATE reactivos SET reactivo ='$reactivo', respuesta = '$respuesta' 
WHERE id_reactivo ='$id_reactivo';";

mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);


?>