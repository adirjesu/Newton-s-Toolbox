<?php
include 'conexion.php';

$usu_usuairo=$_POST['usuario'];
$usu_pass=$_POST['pass'];

$sentencia=$conexion->prepare("SELECT * FROM admins WHERE usuario=? AND pass=?;");
$sentencia->bind_param('ss',$usu_usuairo,$usu_pass);

$sentencia->execute();

$resultado = $sentencia ->get_result();
if($fila = $resultado->fetch_assoc()){
    echo json_encode($fila,JSON_UNESCAPED_UNICODE);
}
$sentencia -> close();
$conexion -> close();
?>