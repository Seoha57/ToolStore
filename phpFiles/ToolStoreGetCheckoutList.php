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
		$response = array(array());

		if($userID != "admin")
		{
			$statement = mysqli_prepare($con, "SELECT toolName, toolMaker, toolSize, amount, price, state FROM ORDERS WHERE userID='$userID'");

			if(mysqli_stmt_execute($statement))
			{
				mysqli_stmt_store_result($statement);
				mysqli_stmt_bind_result($statement, $toolName, $toolMaker, $toolSize, $amount, $price, $currState);

				while(mysqli_stmt_fetch($statement))
				{
					$response[]["toolName"] = $toolName;
					$response[]["toolMaker"] = $toolMaker;
					$response[]["toolSize"] = $toolSize;
					$response[]["amount"] = $amount;
					$response[]["price"] = $price;
					$response[]["state"] = $currState;
				}
			}
		}
		else
		{
			$statement = mysqli_prepare($con, "SELECT * FROM ORDERS ORDER BY checkout_date ASC");

			if(mysqli_stmt_execute($statement))
			{
				mysqli_stmt_store_result($statement);
				mysqli_stmt_bind_result($statement, $userID, $toolName, $toolMaker, $toolSize, $amount, $price, $currState, $orderDate);

				while(mysqli_stmt_fetch($statement))
				{
					$response[]["userID"] = $userID;
					$response[]["toolName"] = $toolName;
					$response[]["toolMaker"] = $toolMaker;
					$response[]["toolSize"] = $toolSize;
					$response[]["amount"] = $amount;
					$response[]["price"] = $price;
					$response[]["state"] = $currState;
					$response[]["checkout"] = $orderDate;
				}
			}
		}
	}
	echo json_encode($response);
?>