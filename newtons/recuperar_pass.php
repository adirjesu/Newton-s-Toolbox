<?php
include 'conexion.php';
$usuario=$_GET['usuario'];

$consulta = "SELECT correo FROM admins WHERE usuario='$usuario';";
$resultado = $conexion ->query($consulta);

while ($fila = $resultado -> fetch_array()){
    $admins[]=array_map('utf8_encode',$fila);
}

echo json_encode($admins);
$resultado -> close();
?>