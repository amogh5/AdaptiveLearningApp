function survey(){
	document.getElementById("que1").innerHTML = "Q1:How Would you Rate yourself";
	// document.getElementById("radioText").innerHTML = "2000";//To be used for changing the contents 
 }

function SubmitForm(PrepareString){
	var radios = document.getElementsByName(PrepareString);
	for (var i = 0, length = radios.length; i < length; i++) {
		if (radios[i].checked) {
			callPostMethod(PrepareString,radios[i].value);		
			break;
		}
	 }
  }


 function callPostMethod(ResponseToBeSent,TheSelectedValue) {
	 debugger;
	$.ajax({
	  method: "POST",
	  url: "https://gurujsonrpc.appspot.com/guru", //test url
	  data: { "method" : "guru.test", "params" : [ "Guru" ], "id" : 123 },
	  success: function(responseData){
		  console.log(responseData);
		// CaptureResponse(responseData); //Function For responseData
	  }
	});
 };


 /*
 function CaptureResponse(ReturnResponse){
		   if(ReturnResponse.QuestionNO == 1)//
		   {
          Substitute(ReturnResponse.Question);
			  //This function will substitute the Response with new content
		   }
	 }
		
*/

/*	function Substitute(QuestionContent){
			var element, newElement, parent;

			// Get the original element
			element = document.getElementById("Question");
			
			// Assuming it exists...
			if (element) {
				// Get its parent
				parent = element.parentNode;
			
				// Create the new element
				newElement = document.createElement('div');
			
				// Set its ID and content
				newElement.id = "Question";
				newElement.innerHTML = QuestionContent.Question;
				parent.insertBefore(newElement, element);
				parent.removeChild(element);
			}
	}
*/

/*function CallPostMethod(){
	debugger;
	var hr = new XMLHttpRequest();
   var url = "https://gurujsonrpc.appspot.com/guru";//Host Url  
   hr.open("POST", url, true);
   hr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   hr.onreadystatechange = function(){
	 if (hr.readyState == 4 && hr.status == 200) {
		debugger;
	   var resp = console.log(response);
	   if (resp == "true") {
         debugger;
	 }
   }
   hr.send({ "method" : "guru.test", "params" : [ "Guru" ], "id" : 123 });
   }
}*/