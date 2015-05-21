/*
	Author: BauerMitFackel
*/

$(document).ready(function() {

	var jumbotronHeight = $(".jumbotron").outerHeight();

    function parallax () {
    	var scrolled = $(window).scrollTop();
    	$(".jumbotron-bg").css("height", (jumbotronHeight - scrolled) + "px");
    }

    $(window).scroll(function(e) {
        parallax();
    });
});


