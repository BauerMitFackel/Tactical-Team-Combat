/*
	Author: BauerMitFackel
*/

$(document).ready( function() {

	$(".role-row").click(
		function() {
			window.document.location = $(this).attr("href");
		}
	);
});