$(document).ready(function() {
	$('#errorDiv').css('display', 'none');
	loginFormSubmit();
});

function closeError() {
	$('#errorDiv').css('display', 'none');
}


function loginFormSubmit() {
	$("#user-login").submit(function(event) {
		event.preventDefault();

		let username = $("#username").val();
		let password = $("#password").val();
		getUserLogin(username, password)
	});
}


function getUserLogin(username, password) {
	const endpoint = $("#base-path").val() + "login/user?username=" + username + "&password=" + password;
	$.ajax
		({
			type: "GET",
			url: endpoint,
			success: function(data) {
				if (null != data && null != data.responseCode) {

					if (data.responseCode === '200') {
						document.cookie = "token=" + data.data + ";path=/";
						window.location.replace($("#asset-path").val()+"/dashboard");
					}else if(data.responseCode === '403'){
						errorMsg("Account is locked, Please contact Admin")
					}else if(data.responseCode === '405'){
						errorMsg("Account is not active, Please contact Admin")
					}else
						errorMsg("Invalid User Name or Password")
				} else
					errorMsg("Invalid Login Id or Password")
			}, error: function(error) {
				console.error(error);
				errorMsg("Something went wrong!!")
			}
		});
}

function errorMsg(msg) {
	$('#errorDiv').css('display', 'block');
	$($('#errorDiv').children()[0]).html(msg);
}