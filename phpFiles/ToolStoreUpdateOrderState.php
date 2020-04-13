<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');

	if (mysqli_connect_errno())
	{
		printf("Connect failed: %s\n", mysqli_connect_errno());
	}
	if (isset($_POST["state"], $_POST["userID"], $_POST["name"], $_POST["maker"], $_POST["size"], $_POST["amount"]))
	{
		$updateState = $_POST["state"];
		$userID = $_POST["userID"];
		$toolName = $_POST["name"];
		$toolMaker = $_POST["maker"];
		$toolSize = $_POST["size"];
		$toolAmount = $_POST["amount"];
		
		$statement = mysqli_prepare($con, "UPDATE ORDERS SET state='$updateState' WHERE userID='$userID' AND toolName='$toolName' AND toolMaker='$toolMaker' AND toolSize='$toolSize' AND amount='$toolAmount'");
		$response = array();

		if(mysqli_stmt_execute($statement))
			$response["success"] = true;
		else 
			$response["success"] = false;
	}
	echo json_encode($response);
?>