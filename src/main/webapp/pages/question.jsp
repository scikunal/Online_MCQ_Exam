<html>

<head>

   <link href="pages/style.css" rel="stylesheet">
   
	   <style>
			body {
			  background-color: linen;
			}
			
			h1 {
			  color: maroon;
			  margin-left: 40px;
			}
			
			}
	</style>

		<script> 

					function displayTime()
					{
						sessionStorage.setItem("duration",sessionStorage.getItem("duration")-1);
						
						var remainingSeconds=sessionStorage.getItem("duration");//125

						var minutes=Math.floor(remainingSeconds/60);//125/60=2
						
						var seconds=remainingSeconds%60;//125/60=5

						//<p style="color:red" id="remainingtime">  </p>
						
						document.getElementById("remainingtime").innerHTML=" remaining time " + minutes + ":" + seconds;

						if(remainingSeconds==0)
						{
							location.href="/endexam";
						}
						
					}

					
					setInterval(displayTime,1000);/* 1000 ms - 1 sec . It means displayTime() will be called after every 1 second  */
					

					function changeColor()
					{
											
						var allAnswers=document.getElementsByTagName("span");
			
						var allRadioButtons=document.getElementsByName("submittedAnswer");	

						var previousAnswer=document.getElementById("previousAnswer").value;
					    
					    var nextAnswer=document.getElementById("nextAnswer").value;
						
						for(var i=0;i<4;i++)
						{
								console.log("Previous = " + previousAnswer);
								console.log("Next = " + nextAnswer);
								console.log(allAnswers[i].innerText.length)
								console.log(previousAnswer.length)
								console.log(nextAnswer.length)
								
								if(allAnswers[i].innerText.trim()==previousAnswer.trim())
								{
									
									allAnswers[i].style.color="red";
									allRadioButtons[i].checked=true;
									break;
								}
								/* if(allAnswers[i].innerText.trim()==nextAnswer.trim()){
									allAnswers[i].style.color="red";
									allRadioButtons[i].checked=true;
									break; 
								} */
						}
						
						for(var i=0;i<4;i++)
						{
								console.log("Previous = " + previousAnswer);
								console.log("Next = " + nextAnswer);
								console.log(allAnswers[i].innerText.length)
								console.log(previousAnswer.length)
								console.log(nextAnswer.length)
								
								if(allAnswers[i].innerText.trim()==nextAnswer.trim())
								{
									
									allAnswers[i].style.color="red";
									allRadioButtons[i].checked=true;
									break;
								}
						}
				     }
		
		</script>
</head>

<body onload="changeColor()">

<img src="${imagepath}" width=100 height=100>

<p style="color:red;position:absolute;top:0px;right:50px;color:green;font-size:30px" id="remainingtime"></p>

<h1> Questions </h1>


<form>

<div id="maincontent">

<span style="font-size: 25;">Q.</span><input type="text"  name="qno" value="${question.qno}" id ="qno" readonly>&nbsp;
<input type="text"  name="qtext" value="${question.qtext}" id="qtext" readonly><br><br>

<br><br>


<input type="radio" name="submittedAnswer" value="${question.op1}" class="radio1"><span class="que">${question.op1}</span>
<input type="radio" name="submittedAnswer" value="${question.op2}" class="radio2"><span class="que">${question.op2}</span><br>
<input type="radio" name="submittedAnswer" value="${question.op3}" class="radio1"><span class="que">${question.op3}</span> 
<input type="radio" name="submittedAnswer" value="${question.op4}"class="radio2"><span class="que">${question.op4}</span>

<input style="display: none;" type="text" name="originalAnswer" value="${question.answer}"><br>
<input style="display: none;" type= "text" id="previousAnswer" value="${previousAnswer}"><br><br>
<input style="display: none;" type= "text" id="nextAnswer" value="${nextAnswer}"><br><br>



<input type=submit value="previous" formaction="previous" class="btn qb">
<input type=submit value="endexam" formaction="endexam" class="btn qb">
<input type=submit value="next" formaction="next" class="btn qb">
</div>

</form>
<h2 style="color:red" align="center">${message}</h2>
</body>

</html>