//setup of clickable table rows
jQuery(document).ready(function ($) {
    $(".clickable-row").click(function () {
        window.document.location = $(this).data("href");
    });
});

//custom confirm function which will stop futher even propagation in case of nested onclick events
function customConfirm(message) {
    var evt = window.event || arguments.callee.caller.arguments[0];
    evt.stopPropagation();
    return confirm(message);
}

$(function () {
    setNavigation();
});

function setNavigation() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);
    $(".nav a").each(function () {
        var href = $(this).attr('href');
        if (path === href) {
            $(this).closest('li').addClass('active');
        }
    });
}