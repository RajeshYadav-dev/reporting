function logoutUser(){
	document.cookie = "token=null;path=/";
	window.location.replace($("#asset-path").val() + "/login");
}