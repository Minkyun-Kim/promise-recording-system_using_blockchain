function isEmpty(){
	var date =document.forms["promise"]["date"].value;
	if(date == ""){
		alert("날짜를 입력해주세요");
		return false;
	}
	var location =document.forms["promise"]["location"].value;
	if(location == ""){
		alert("장소를 입력해주세요");
		return false;
	}
	var fund =document.forms["promise"]["fund"].value;
	if(fund == ""){
		alert("공금을 입력해주세요");
		return false;
	}
	var participants =document.forms["promise"]["participants"].value;
	if(participants == ""){
		alert("날짜를 입력해주세요");
		return false;
	}
	var content =document.forms["promise"]["content"].value;
	if(content == ""){
		alert("내용을 입력해주세요");
		return false;
	}
	
	return true;
}