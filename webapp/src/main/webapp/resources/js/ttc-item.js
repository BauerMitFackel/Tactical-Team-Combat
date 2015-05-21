/*
	Author: BauerMitFackel
*/

$(document).ready(function() {

    $(document).on("change", ".item-name", onItemNameChanged);
    $(document).on("change", ".item-price", onItemPriceChanged);


    function onItemNameChanged () {

        var url = $(document).find("input[name='item-url']").val();
        var name = $(this).val();

        var data = {
            name: name
        }

        patchItem(url, data);
    }


    function onItemPriceChanged () {

        var url = $(document).find("input[name='item-url']").val();
        var price = $(this).val();

        var data = {
            price: price
        }

        patchItem(url, data);
    }


    function patchItem (url, data) {

        $.ajax({
            type: "PATCH",
            contentType: "application/json; charset=utf-8",
            url: url,
            data: JSON.stringify(data),
            success: function (data, status) {
            },
            error: function (status) {
            }
        });
    }
});