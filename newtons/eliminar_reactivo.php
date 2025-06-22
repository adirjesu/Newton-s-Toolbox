<?php
include 'conexion.php';
$id_reactivo=$_POST['id_reactivo'];


$consulta="DELETE FROM reactivos WHERE id_reactivo='$id_reactivo'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);


?>