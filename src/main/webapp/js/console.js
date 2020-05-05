function optionchange(page) {
    $.get(page, function (resp) {
        $("#sistema-console").html(resp);
    })
}