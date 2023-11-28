

<html>
	<link href="pages/style.css" rel="stylesheet">
	<script>
	
		sessionStorage.setItem("duration",1200);
		
	</script>
	<body>
		<center>
			<form>
			
			<img src="${imagepath}" width=100 height=100 style="border-radius: 10px; margin: 7%;">
			
			<h1>${message}</h1>
			<h3>Welcome</h3> <h2>${user}</h2><br>
			<h3>click here to start the test</h3><br>
			<input type = submit value = "Start" formaction = "question" class="btn">
			</form>
		</center>
	</body>	

</html>