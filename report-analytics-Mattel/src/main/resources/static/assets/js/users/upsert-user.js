$(document).ready(function() {
	$('#errorDiv').css('display', 'none');
	upsertUserFormSubmit();
	$("#user-create").trigger("reset");
});

function closeError() {
	$('#errorDiv').css('display', 'none');
}

function upsertUserFormSubmit() {
	$("#user-create").submit(function(event) {
		event.preventDefault();
		var userMap = new Map();

		let username = $("#username").val();
		userMap.set("username", username);

		let loginId = $("#login-id").val();
		userMap.set("loginId", loginId);

		let orgEmailId = $("#org-email-id").val();
		userMap.set("orgEmailId", orgEmailId);

		let empId = $("#emp-id").val();
		userMap.set("empId", empId);

		let contact = $("#contact").val();
		userMap.set("contact", contact);

		let gender = document.getElementById("gender").value;
		userMap.set("gender", gender);

		let password = $("#password").val();
		userMap.set("password", password);

		let role = document.getElementById("role").value;
		userMap.set("role", role);

		let userId = document.getElementById("userId").value;
		userMap.set("userId", (null != userId && userId !== 'undefined' && userId !== '') ? userId : null);

		if (null != userId && userId !== 'undefined' && userId !== '') {
			let active = document.getElementById("active").value;
			userMap.set("isActive", active);

			let locked = document.getElementById("locked").value;
			userMap.set("isLocked", locked);
		}

		upsertUserAPI(userMap);
	});
}

function upsertUserAPI(userMap) {
	const endpoint = $("#base-path").val() + "user/upsert-user";

	var swalHeadingSuccess = "";
	var swalHeadingError = "";
	var update = false;

	if (null != userMap.get("userId") && userMap.get("userId") !== 'undefined') {
		swalHeadingSuccess = "User Updated Successfully!!";
		update = true;
		
	} else {
		swalHeadingSuccess = "User Created Successfully!!";
	}

	$.ajax
		({
			type: "PUT",
			url: endpoint,
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(Object.fromEntries(userMap)),
			success: function(data) {
				if (null != data && null != data.responseCode) {

					if (data.responseCode === '200') {
						let loginId = data.data.loginId;
						let username = data.data.username;
						swal({
							title: swalHeadingSuccess,
							text: "User Name : " + username + ", Login Id : " + loginId,
							icon: "success",
							button: "Done",
						}).then(() => {
							if(update)
								location.reload();
							else
								$("#user-create").trigger("reset");
						});
					} else if (data.responseCode === '409') {
						swal({
							title: "User Already Exists",
							text: "Please try again with different login id",
							icon: "error",
							button: "Done",
						}).then(() => {

						});
					} else {
						swal({
							title: "Somethng went wrong",
							text: "Please try again after some time",
							icon: "error",
							button: "Done",
						}).then(() => {

						});
					}



				}

			}, error: function(error) {
				console.error(error);
			}
		});
}