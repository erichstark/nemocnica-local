jQuery(document).ready(function ($) {
    $(".clickable-row").click(function () {
        window.document.location = $(this).data("href");
    });
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

function showMessage(message, type) {
    var alertType = "alert-";
    switch (type) {
        default:
        case 0:
            alertType = alertType + "warning";
            break;
        case 1:
            alertType = alertType + "danger";
            break;
        case 2:
            alertType = alertType + "success";
            break;
        case 3:
            alertType = alertType + "info";
            break;
    }
    $("#alertContainer").append('<div class="alert alert-dismissible ' + alertType + '"><button type="button" class="close" data-dismiss="alert">×</button>' + message + '</div>');
}


function deleteDisplay(id) {
    $.ajax({
        type: "POST",
        url: "/setup",
        data: form.serialize(),
        success: function (data) {
            spinner.hide();
            if (data == true) {
                admin.show();
            } else {
                showMessage("Nepodarilo sa nadviazať spojenie s Globálnym serverom. Prosím skontrolujte parametre synchronizácie. RELOAD");
                form.show();
            }
        },
        error: function (data) {
            spinner.hide();
            error.show();
            error.set(data);
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
        url: "/setup",
        data: form.serialize(),
        success: function (data) {
            spinner.hide();
            if (data == true) {
                admin.show();
            } else {
                showMessage("Nepodarilo sa nadviazať spojenie s Globálnym serverom. Prosím skontrolujte parametre synchronizácie. RELOAD");
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



