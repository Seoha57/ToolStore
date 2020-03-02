<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');

	$userID = $_POST["userID"];
	$userID = strtoupper($userID);

	$sql = "CREATE TABLE IF NOT EXISTS $userID (toolName VARCHAR(30), toolMaker VARCHAR(30), toolSize VARCHAR(30), amount INT, price INT)";
	$result = mysqli_query($con, $sql) or die ("Bad Create: $sql");

?>