<?php

class DB_Functions
{
	private $db;

	function __construct()
	{
		include_once './db_connect.php';
		//connecting to database
		$this->db = new DB_Connect();
		$this->db->connect();
	}

	function __destruct()
	{
	
	}

	public function getAllCustomers()
	{
		$result = mysql_query("select * FROM CUSTOMER");
		return $result;
	}
}

?>