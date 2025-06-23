<?php
include 'conexion.php';

$materia=$_POST['materia'];
$tema=$_POST['tema'];
$reactivo=$_POST['reactivo'];
$respuesta=$_POST['respuesta'];

$consulta="INSERT INTO reactivos(materia,tema,reactivo,respuesta) 
VALUES ('$materia','$tema','$reactivo','$respuesta');";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);


?>