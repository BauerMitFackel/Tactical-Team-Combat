/*
	Author: BauerMitFackel
*/

$(document).ready(function() {

    $(document).on("click", ".item-tile", onItemTileClicked);

    $("#article-search").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("div .tile").each(function(index) {
            if (index >= 0) {
                $tile = $(this);
                $tileContainer = $tile.parent();
                var articleName = $tile.find("h5:first").text().toLowerCase();
                if (articleName.indexOf(value) > -1) {
                    $tileContainer.show();
                } else {
                    $tileContainer.hide();
                }
            }
        });
    });

    function onItemTileClicked () {

        var url = $(this).find("input[name='item-url']").val();
        window.location = url;
    }

});