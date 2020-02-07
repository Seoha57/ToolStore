<?php
	$con = mysqli_connect("localhost", "seoha57", "tjdgkr12!", "seoha57");
	mysqli_query($con, 'SET NAMES utf8');

	if (mysqli_connect_errno())
	{
		printf("Connect failed: %s\n", mysqli_connect_errno());
	}
	if (isset($_POST["table"], $_POST["name"], $_POST["maker"], $_POST["size"], $_POST["amount"], $_POST["price"]))
	{
		$tableName = $_POST["table"];
		$toolName = $_POST["name"];
		$toolMaker = $_POST["maker"];
		$toolSize = $_POST["size"];
		$toolAmount = $_POST["amount"];
		$toolPrice = $_POST["price"];

		if ($tableName == "BOLTS AND NUTS")
			$tableName = "BOLTS&NUTS";
		elseif ($tableName == 'TOOLS')
			$tableName = "TOOLS";
		elseif ($tableName == 'OTHERS')
			$tableName = "OTHERS";

		$statement = mysqli_prepare($con, "INSERT INTO `$tableName` (name, maker, size, amount, price) VALUES (?,?,?,?,?)");
		mysqli_stmt_bind_param($statement, "sssii", $toolName, $toolMaker, $toolSize, $toolAmount, $toolPrice);
		$response = array();

		if(mysqli_stmt_execute($statement))
			$response["success"] = true;
		else 
			$response["success"] = false;
	}
	echo json_encode($response);

?>