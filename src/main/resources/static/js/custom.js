jQuery(document).ready(function ($) {
    $(".clickable-row").click(function () {
        window.document.location = $(this).data("href");
    });
    setNavigation();
});

function customConfirm(message) {
    var evt = window.event || arguments.callee.caller.arguments[0];
    evt.stopPropagation();
    return confirm(message);
}

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


$("#facility").submit(function () {
    var form = $("#facility");
    var spinner = $("#spinner");
    var admin = $("#create-admin");
    var success = $("#success");
    var errorContainer = $("#error-container");
    form.hide();
    spinner.show();
    errorContainer.html("");
    $.ajax({
        type: "POST",
        url: "/setup/facility",
        data: form.serialize(),
        success: function (data) {
            spinner.hide();
            if (data == true) {
                admin.show();
            } else {
                showError("Nepodarilo sa nadviazať spojenie s Globálnym serverom. Prosím skontrolujte parametre synchronizácie. RELOAD")
                form.show();
            }
        },
        error: function (data) {
            spinner.hide();
            error.show();
            error.set(data);
        }
    });
    return false;
});


function showError(message) {
    var errorContainer = $("#error-container");
    errorContainer.html('<div id="error" class="alert alert-dismissible alert-danger"><button type="button" class="close" data-dismiss="alert">×</button>' + message + '</div>');
}