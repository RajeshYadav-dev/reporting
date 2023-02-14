$(document).ready(function() {
	$('.datetimepicker3').datetimepicker({
		dateFormat: 'Y/m/d',
		timepicker: true,
		timeFormat: 'hh:mm'
	});

	$("#view-data").click(function() {
		if (fromTime() != ":00" && toTime() != ":00")
			getReportData(fromTime(), toTime());
	});

	$("#report-download").click(function() {
		if (fromTime() != ":00" && toTime() != ":00")
			downloadReport(fromTime(), toTime());
	});

	enableDisableBtn();

	$("#queryFrom, #queryTo").change(function() {
		enableDisableBtn();
	});
});

function getReportData(startTime, endTime) {
	const endpoint = $("#base-path").val() + "call-detail/reporting?startTime=" + startTime + "&endTime=" + endTime;
	$.ajax
		({
			type: "GET",
			url: endpoint,
			success: function(data) {
				if (null != data && null != data.data && null != data.status && data.status === 200) {
					populateTableData(data.data);
					$("#no-result").hide()
				} else
					$("#no-result").show()
			}, error: function(error) {
				console.error(error);
			}
		});
}

function populateTableData(items) {
	const table = document.getElementById("report-table");
	$(".table-dynamic-data").remove();

	items.forEach(item => {
		let row = table.insertRow();

		let col1 = row.insertCell(0);
		col1.innerHTML = item.ucid;

		let col2 = row.insertCell(1);
		col2.innerHTML = item.ani;	
		
    	let col3 = row.insertCell(2);
		col3.innerHTML = item.sessionId;

		let col4 = row.insertCell(3);
		col4.innerHTML = item.dnis;

		let col5 = row.insertCell(4);
		col5.innerHTML = item.language;

		let col6 = row.insertCell(5);
		col6.innerHTML = item.callStartTime.toString().substring(0, 10) + " " + item.callStartTime.toString().substring(11, 19);

		let col7 = row.insertCell(6);
		col7.innerHTML = item.callEndTime.toString().substring(0, 10) + " " + item.callEndTime.toString().substring(11, 19);;

		let col8 = row.insertCell(7);
		col8.innerHTML = item.callDuration;
		
		let col9 = row.insertCell(8);
		col9.innerHTML = item.terminateReason;

		let col10 = row.insertCell(9);
		col10.innerHTML = item.terminateType;
	


	});
}

function downloadReport(fromTime, toTime) {
	window.open($("#base-path").val() + "call-detail/reporting/export?startTime=" + fromTime + "&endTime=" + toTime);
}

function fromTime() {
	let fromDate = $("#queryFrom").val();
	fromDate = fromDate.concat(':00').replaceAll('/', '-');
	return fromDate;
}

function toTime() {
	let toDate = $("#queryTo").val();
	toDate = toDate.concat(':00').replaceAll('/', '-');
	return toDate;
}

function enableDisableBtn() {
	if (fromTime() === ":00" && toTime() === ":00") {
		$("#view-data, #report-download").css("pointer-events", "none").css("color", "#66666645");
	} else if (fromTime() != ":00" && toTime() != ":00") {
		$("#view-data, #report-download").css("pointer-events", "").css("color", "");
	}
}