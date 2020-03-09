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

		$statement = mysqli_prepare($con, "SELECT * FROM CART WHERE userID='$userID'");

		$response = array(array());

		if(mysqli_stmt_execute($statement))
		{
			mysqli_stmt_store_result($statement);
			mysqli_stmt_bind_result($statement, $user, $toolCategory, $toolName, $toolMaker, $toolSize, $amount, $price);

			while(mysqli_stmt_fetch($statement))
			{
				$response[]["toolName"] = $toolName;
				$response[]["toolMaker"] = $toolMaker;
				$response[]["toolSize"] = $toolSize;
				$response[]["amount"] = $amount;
				$response[]["totalPrice"] = $price;
			}
		}
	}
	echo json_encode($response);
?>