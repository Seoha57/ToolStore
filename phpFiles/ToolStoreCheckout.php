<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');
	
	if (mysqli_connect_errno())
	{
		printf("Connect failed: %s\n", mysqli_connect_errno());
	}
	
	if(isset($_POST["userID"]))
	{
		$userID = $_POST["userID"];

		$statement = mysqli_prepare($con, "INSERT INTO ORDERS (userID, toolName, toolMaker, toolSize, amount, price) SELECT userID, toolName, toolMaker, toolSize, amount, price FROM CART WHERE userID='$userID'");

		$response = array();

		if(mysqli_stmt_execute($statement))
		{
			$response["success"] = true;

			$stmt = mysqli_prepare($con, "DELETE FROM CART WHERE userID='$userID'");
			mysqli_stmt_execute($stmt);
		}
		else 
			$response["success"] = false;
	}
	echo json_encode($response);
?>