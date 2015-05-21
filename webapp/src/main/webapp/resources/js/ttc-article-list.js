/*
	Author: BauerMitFackel
*/

$(document).ready(function() {

    $(document).on("change", ".article-list-item-checkbox", onArticleListItemCheckboxChanged);
    $(document).on("change", ".article-list-item-discount", onArticleListItemDiscountChanged);

    function onArticleListItemCheckboxChanged () {

        var articleListItem = $(this).closest(".article-list-item");
        var url = $(articleListItem).find("input[name='articles-url']").val();
        var itemId = $(this).val();

        var data = {
            itemId: itemId
        }

        if (this.checked) {
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                success: function (data, status) {
                    $(articleListItem).replaceWith($(data).find(".article-list-item"));
                },
                error: function (status) {
                    alert("Cant create article");
                }
            });
        } else {

            url = url + "/" + itemId;

            $.ajax({
                type: "DELETE",
                url: url,
                success: function (data, status) {
                    $(articleListItem).replaceWith($(data).find(".article-list-item"));
                },
                error: function (status) {
                    alert("Cant delete article");
                }
            });
        }
    }


    function onArticleListItemDiscountChanged () {

        var articleListItem = $(this).closest(".article-list-item");

        var url = $(articleListItem).find("input[name='articles-url']").val();
        var itemId = $(articleListItem).find("input[name='item-id']").val();
        var discount = $(this).val();

        url = url + "/" + itemId;

        var data = {
            discount: discount
        }

        $.ajax({
            type: "PATCH",
            contentType: "application/json; charset=utf-8",
            url: url,
            data: JSON.stringify(data),
            success: function (data, status) {
            },
            error: function (status) {
                alert("Cant update discount");
            }
        });
    }
});