<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');
	
	$statement = mysqli_prepare($con, "SELECT * FROM CUSTOMER");
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $userID, $userPassword, $userName, $city, $ZIPCode, $contact);

	$response = array();
	$response["success"] = false;

	while(mysqli_stmt_fetch($statement))
	{
		$response["success"] = true;
		$response["userID"] = $userID;
		$response["userPassword"] = $userPassword;
		$response["userName"] = $userName;
		$response["city"] = $city;
		$response["ZIPCode"] = $ZIPCode;
		$response["contact"] = $contact;
	}

	echo json_encode($response);

?>