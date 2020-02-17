<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');

	if (mysqli_connect_errno())
	{
		printf("Connect failed: %s\n", mysqli_connect_errno());
	}

	$tableName = $_POST["table"];

	$statement = mysqli_prepare($con, "SELECT * FROM `$tableName`");
	
	$response = array(array());

	if(mysqli_stmt_execute($statement))
	{
		mysqli_stmt_store_result($statement);
		mysqli_stmt_bind_result($statement, $name, $maker, $size, $amount, $price);

		while(mysqli_stmt_fetch($statement))
		{
			$response[]["name"] = $name;
			$response[]["maker"] = $maker;
			$response[]["size"] = $size;
			$response[]["amount"] = $amount;
			$response[]["price"] = $price;
		}
	}

	echo json_encode($response);

?>