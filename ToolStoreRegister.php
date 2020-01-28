<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');

	$userID = $_POST["userID"];
	$userPassword = $_POST["userPassword"];
	$userName = $_POST["userName"];
	$city = $_POST["city"];
	$ZIPCode = $_POST["ZIPCode"];
	$contact = $_POST["contact"];

	$statement = mysqli_prepare($con, "INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?)");
	mysqli_stmt_bind_param($statement, "ssssii", $userID, $userPassword, $userName, $city, $ZIPCode, $contact);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);


?>