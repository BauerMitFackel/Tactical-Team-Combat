/*
	Author: BauerMitFackel
*/

$(document).ready(function() {

	$(".panel").on("hidden.bs.collapse", function (e) {
		var panel = $(e.currentTarget).closest(".panel");
		panel.addClass("panel-default");
		panel.removeClass("panel-active");
	})

	$(".panel").on("show.bs.collapse", function (e) {
		var panel = $(e.currentTarget).closest(".panel");
		panel.addClass("panel-active");
		panel.removeClass("panel-default");
	});

});

