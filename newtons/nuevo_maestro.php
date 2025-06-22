<?php
include 'conexion.php';

$nombre=$_POST['nombre'];
$ap_p=$_POST['ap_p'];
$ap_m=$_POST['ap_m'];
$correo=$_POST['correo'];
$usuario=$_POST['usuario'];
$pass=$_POST['pass'];
$materia=$_POST['materia'];

$consulta="INSERT INTO maestro( nombres,ap_p, ap_m,correo, usuario, pass, materia) 
VALUES ('$nombre','$ap_p','$ap_m','$correo','$usuario','$pass','$materia');";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);


?>