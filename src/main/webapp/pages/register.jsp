
<html>

<head>
 <link href="pages/style.css" rel="stylesheet">
	<script>

		var XMLHttp  = new XMLHttpRequest();
		
			function checkUsername(UserInput){
				var username = UserInput.value;
				console.log(username);
				XMLHttp.open("get","checkUser?username="+username);
				XMLHttp.send();
				XMLHttp.onload = display;
			}
			
			function display(){
				console.log("in display");
				var span = document.getElementById("show");
				console.log(XMLHttp.responseText);
				span.innerHTML=XMLHttp.responseText;
			}
	
	
	        function change(input)
			{
		       		input.style.color="white";
		       		input.style.background="black";
	        }
	        
    </script>
   
</head>
<center>
<div class="reg">

<h1 > Exam Registration <br>form </h1><br><br><br>

<form enctype="multipart/form-data" >
		
		<span id = "show" style="color: red;"></span><br>
		<input type="text" name="username" placeholder="Username" onblur="checkUsername(this)" onfocus="change(this)" class = "textbox" required="required"><br>
		<br>
		<input type="password" name="password" placeholder="Password" onfocus="change(this)" class = "textbox" required="required"><br><br>
		
		<input type="text" name="mobile" placeholder="Mobile Number" onfocus="change(this)" maxlength="10" class = "textbox" required="required"><br><br>
		
		<input type="email" name="emailid" placeholder="Email ID" onfocus="change(this)" class = "textbox" required="required"><br><br>
		
		<input type="file" name="image" required="required" class = "textbox"><br><br>
		
		<input type=submit value=Register formaction="saveDb" formmethod=post class="btn">
		
		<br><br>Already have Registered....?<br>Please <a href="login">Login</a> Here.
		

</form>
</div>
</center>
</html>
