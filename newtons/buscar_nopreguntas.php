<?php
include 'conexion.php';
$materia = $_GET['materia'];
$tema = $_GET['tema'];
// Modificar la consulta para contar los usuarios
$consulta = "SELECT COUNT(*) as total FROM reactivos WHERE materia='$materia' AND tema='$tema';";
$resultado = $conexion->query($consulta);
if ($fila = $resultado->fetch_array()) {
    $totalUsuarios = array_map('utf8_encode', $fila);
    echo json_encode($totalUsuarios);
}
$resultado->close();
?>