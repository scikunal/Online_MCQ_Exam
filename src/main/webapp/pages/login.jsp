<html>

<head>

<link href="pages/style.css" rel="stylesheet">


		<style>
				input,select
				{
						padding:10px;
				}
				
				
		</style>


</head>


<body >
<center>
	<div class="reg">
	<h1> Online Exam Login </h1><br><br><br>	
<form>
	
	
<span style="color:red">  ${message} </span><br>
		<input type="text" name="username" id="textbox" placeholder="Enter username" class = "textbox" required="required"> <br><br>
		<input type="password" name="password" id="textbox" placeholder="Enter password" class = "textbox" required="required"> <br><br>

		<select name="subject" id="textbox"  required="required" style="font-size: 20; font-family: monospace;"> 
			
			<option>select subject</option>
			<option value="java">java</option>
			<option value="gk">general knowldge</option>
		
		</select>
	
		<br><br>
	
	
		<input type="submit" value='Login' formaction="validate" formmethod="post" class="btn">
		
    	<br><br>Not Registered Yet...?<br>Please <a href="register" >REGISTER </a>Here.
    
    
	</div>		

</form>
</center>
</body>

</html>