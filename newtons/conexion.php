<?php
$hostname="localhost";
$database="newtons_toolbox";
$username="root";
$password="";
$conexion= new mysqli($hostname,$username,$password,$database);
if($conexion ->connect_errno){
    echo "Lo sentimos, el sitio web está experimentando problemas. Por favor, inténtelo de nuevo más tarde.";

}
?>