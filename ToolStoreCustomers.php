<html>
<head><title>View Customers</title>
<style>
body {
	font: normal medium/1.4 sans-serif;
}
table {
	border-collapse: collapse;
	width: 20%;
	margin-left: auto;
	margin-right: auto;
}
tr > td {
	padding: 0.25rem;
	text-align: center;
	border: 1px solid #ccc;
}
tr:nth-child(even) {
	background: #FAE1EE;
}
tr:nth-child(odd) {
	background: #edd3ff;
}
tr#header{
	background: #cle2ff;
}
div.header{
	padding: 10px;
	background: #e0ffc1;
	width:30%;
	color: #008000;
	margin: 5px;
}
div.refrech{
	margin-top:10px;
	width: 5%;
	margin-left: auto;
	margin-right: auto;
}
div#norecord{
	margin-top:10px;
	width:15%;
	margin-left: auto;
	margin-right: auto;
}
</style>
<script>
function refreshPage(){
	location.reload();
}
</script>
</head>
<body>
<center>
<div class="header">
Android SQLite and MySQL Sync Results
</div>
</center>
<?php
	include_once 'db_functions.php';
	$db = new DB_Functions();
	$customer = $db->getAllCustomers();
	if($customer != false)
		$no_of_customers = mysql_num_rows($customer);
	else
		$no_of_customers = 0;

?>
<?php
	if($no_of_customers > 0){
?>
<table>
<tr id="header"><td>userID</td><td>userName</td><td>userCity</td><td>userZIPCode</td><td>userContact</td></tr>
<?php
	while ($row = mysql_fetch_array($customer) {
?>
<tr>
<td><span><?php echo $row["userID"] ?></span><td>
<td><span><?php echo $row["userName"] ?></span><td>
<td><span><?php echo $row["city"] ?></span><td>
<td><span><?php echo $row["ZIPCode"] ?></span><td>
<td><span><?php echo $row["contact"] ?></span><td>
</tr>
<?php } ?>
</table>
<?php }else{ ?>
<div id="norecord">
No records in MySQL DB
</div>
<?php } ?>
<div class="refresh">
<button onclick="refreshPage()">Refresh</button>
</div>
</body>
</html>
