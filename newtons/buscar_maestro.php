<?php
include 'conexion.php';
$usuario=$_GET['usuario'];

$consulta = "select * from maestro where usuario='$usuario';";
$resultado = $conexion ->query($consulta);

while ($fila = $resultado -> fetch_array()){
    $maestro[]=array_map('utf8_encode',$fila);
}

echo json_encode($maestro);
$resultado -> close();
?>