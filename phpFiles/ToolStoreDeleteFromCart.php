<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');

	if (mysqli_connect_errno())
	{
		printf("Connect failed: %s\n", mysqli_connect_errno());
	}
	
	if (isset($_POST["userID"], $_POST["toolName"], $_POST["toolMaker"], $_POST["toolSize"]))
	{
		$userID = $_POST["userID"];
		$toolName = $_POST["toolName"];
		$toolMaker = $_POST["toolMaker"];
		$toolSize = $_POST["toolSize"];
		
		$statement = mysqli_prepare($con, "DELETE FROM CART WHERE userID='$userID' AND toolName='$toolName' AND toolMaker='$toolMaker' AND toolSize='$toolSize'");

		$response = array();

		if(mysqli_stmt_execute($statement))
			$response["success"] = true;
		else 
			$response["success"] = false;
	}
	
	echo json_encode($response);
?>