<?php
date_default_timezone_set('Europe/Moscow');
session_start();
$start = microtime(true);
$current_time = date("H:i:s");
$x = $_GET['x'];
$y = $_GET['y'];
$r = $_GET['r'];
function validate($x, $y, $r){
    if ($x <= -5 || $x >= 3) {
        return false;
    }
    if (!(($y == -2) || ($y == -1.5) || ($y == -1) || ($y == -0.5) || ($y == 0) || ($y == 0.5) || ($y == 1) || ($y == 1.5) || ($y == 2))) {
        return false;
    }
    if (!(($r == 1) || ($r == 1.5) || ($r == 2) || ($r == 2.5) || ($r == 3))) {
        return false;
    }
    return true;
}
function checkPointInArea($r, $x, $y): string
{

    $area1 = (((-($r)) <= ($x)) && (($x) <= (0))) && (((0) <= ($y)) && (($y) <= ($r/2)));
    $area2 = ($x >= 0) && ($y >= 0) && ($x<=$r) && ($y<=$r/2);

    $x1=pow($x,2);
    $y1=pow($y,2);
    $area3 = (($x1+$y1 =$r) && ($x>=0) && ($y<=0));
    if ($area1 || $area2 || $area3){
        return 'Success';}
    else{
        return 'Failed';
    }
}
$validation = validate($x, $y, $r);
if ($validation=true) {
    $execution_time = number_format(microtime(true) - $start, 8, ".", "") * 1000000;
    $inArea = checkPointInArea($r, $x, $y);
    $result = '<tr>';
    $result .= "<td>{$r}</td>";
    $result .= "<td>{$x}</td>";
    $result .= "<td>{$y}</td>";
    $result .= "<td>{$inArea}</td>";
    $result .= "<td>{$current_time}</td>";
    $result .= "<td>{$execution_time}</td>";
    $result .= '</tr>';
}else{
    return 'Failed';
}
$_SESSION['results'][] = $result;
echo $result;
?>