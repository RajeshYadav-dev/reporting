$(document).ready(function() {
});

function deleteUserAlert(userId) {
	swal({
		title: "Are you sure ?",
		text: "User will be removed from your system permanently!!",
		icon: "warning",
		buttons: ["cancel", true],
		dangerMode: true
	}).then((willDelete) => {
		if (willDelete) {
			deleteUser(userId);
		}
	});
}

function deleteUser(userId) {
	let endpoint = $("#base-path").val() + "user?userId="+userId;
	$.ajax
		({
			type: "DELETE",
			url: endpoint,
			success: function(data) {
				if (null != data && null != data.responseCode && data.responseCode === '200') {
					$(".user-row-"+userId).remove();
					swal({
						title: "User Deleted Successfully!!",
						text: "",
						icon: "success",
						button: "Done"
					})
				} else {
					swal({
						title: "Unable to Delete User!!",
						text: "Please try after some time",
						icon: "error",
						button: "Done"
					});
				}

			}, error: function(error) {
				console.error(error);
			}
		});
}