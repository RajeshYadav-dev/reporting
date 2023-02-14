$(document).ready(function() {
	$('#errorDiv').css('display', 'none');
	setPasswordFormSubmit();
});

function closeError() {
	$('#errorDiv').css('display', 'none');
}

function setPasswordFormSubmit() {
	$("#set-password").submit(function(event) {
		event.preventDefault();
		
		let password = $("#password").val();

		let confPassword = $("#re-password").val();

		if (password === confPassword)
			createNewUserAPI(password, confPassword);
		else
			errorMsg("Confirm Password Did Not Match!")
	});
}

function createNewUserAPI(password, confPassword,token) {
	const endpoint = $("#base-path").val() + "user/set-password?password=" + password + "&confirmPassword=" + confPassword;
	$.ajax
		({
			type: "PUT",
			url: endpoint,
			success: function(data) {
				if (null != data && null != data.responseCode && data.responseCode === '200') {
					swal({
						title: "Password Updated Successfully!",
						text: "Redirecting to Dashboard",
						icon: "success",
						button: "Done",
					})

					setTimeout(function() {
						window.location.replace($("#asset-path").val()+"/dashboard");
					}, 1000);
				}

			}, error: function(error) {
				console.error(error);
			}
		});
}

function errorMsg(msg) {
	$('#errorDiv').css('display', 'block');
	$($('#errorDiv').children()[0]).html(msg);
}

function getCookie(cname) {
	let name = cname + "=";
	let decodedCookie = decodeURIComponent(document.cookie);
	let ca = decodedCookie.split(';');
	for (let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}