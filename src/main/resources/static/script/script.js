var ids = new Array();

function addIds(ths){
	console.log(ths);
	console.log(ths.parentElement.nextElementSibling.textContent);
	if(ids == null){
		ids.push(ths.parentElement.nextElementSibling.textContent);
	}else{
		var index = ids.indexOf(ths.parentElement.nextElementSibling.textContent);
		if(index<0){
			ids.push(ths.parentElement.nextElementSibling.textContent);
		}else{
			ids.splice(index,1);
		}
	}
	console.log(ids);
}

function runTest(){
	 var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function()
        {
            if(xmlHttp.readyState == 4 && xmlHttp.status == 200)
            {
                alert(xmlHttp.responseText);
				document.getElementById("testResult").style="display:block";
				setTimeout(function(){ document.getElementById("testResult").style="display:none"; }, 5000);
            }
        }
		var url = "/runTest?ids="+ ids.toString();
        xmlHttp.open("get", url); 
        xmlHttp.send(); 
	
}