<?php

$response = array();
$payload = array();
$response["data"]=array();


$payload["lat"]=-6.88;
$payload["lang"]=107.68;

array_push($response["data"],$payload);

$response["success"]=1;
$response["message"]="Successfuly retrived";

echo json_encode($response, JSON_UNESCAPED_SLASHES)
?>