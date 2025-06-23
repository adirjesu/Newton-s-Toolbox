<?php
include 'conexion.php';
$id_reactivo = $_GET['id_reactivo'];

$consulta = "SELECT * FROM reactivos WHERE id_Reactivo='$id_reactivo';";
$resultado = $conexion ->query($consulta);

while ($fila = $resultado -> fetch_array()){
    $reac[]=array_map('utf8_encode',$fila);
}

echo json_encode($reac);
$resultado -> close();
?>