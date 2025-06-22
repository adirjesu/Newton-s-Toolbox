<?php
include 'conexion.php';
$materia = $_GET['materia'];
$tema = $_GET['tema'];

$consulta = "SELECT * FROM reactivos WHERE materia='$materia' AND tema='$tema';";
$resultado = $conexion ->query($consulta);

while ($fila = $resultado -> fetch_array()){
    $reac[]=array_map('utf8_encode',$fila);
}

echo json_encode($reac);
$resultado -> close();
?>