<?php
include 'conexion.php';

$nombre=$_POST['nombre'];
$ap_p=$_POST['ap_p'];
$ap_m=$_POST['ap_m'];
$correo=$_POST['correo'];
$usuario=$_POST['usuario'];
$pass=$_POST['pass'];
$materia=$_POST['materia'];

$consulta = "UPDATE maestro SET nombres='$nombre', ap_p='$ap_p', ap_m='$ap_m', correo='$correo', usuario='$usuario', pass='$pass', materia='$materia'
WHERE usuario='$usuario';";

mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);


?>