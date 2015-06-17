jQuery(document).ready(function ($) {
    $(".clickable-row").click(function () {
        window.document.location = $(this).data("href");
    });
    setNavigation();

    if (window.location.href.indexOf("admin/office/edit") > -1) {
        minutesToHour();
    } else if (window.location.href.indexOf("admin/office/hours/") > -1) {
        loadHours();
    }
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

function stopClickPropagation() {
    var evt = window.event || arguments.callee.caller.arguments[0];
    evt.stopPropagation();
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


$("#facility-setup").submit(function () {
    var form = $("#facility");
    var spinner = $("#spinner");
    var admin = $("#create-admin");
    var success = $("#success");
    form.hide();
    spinner.show();
    $.ajax({
        type: "POST",
        url: "/setup",
        data: form.serialize(),
        success: function (data) {
            spinner.hide();
            if (data == true) {
                spinner.hide();
                success.show();
            } else {
                showMessage("Nepodarilo sa nadviazať spojenie s Globálnym serverom. Prosím skontrolujte parametre synchronizácie.", 1);
                form.show();
            }
        },
        error: function (data) {
            spinner.hide();
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
    return false;
});

function saveFacility() {
    var form = $("#facility");
    var spinner = Ladda.create(document.querySelector("#saveButton"));
    spinner.start();
    $.ajax({
        type: "POST",
        url: "/admin/facility",
        data: form.serialize(),
        success: function (data) {
            spinner.stop();
            if (data == true) {
                showMessage("Zmeny uložené.", 2);
            } else {
                showMessage("Nepodarilo sa uložiť zmeny.", 1);
            }
        },
        error: function (data) {
            spinner.stop();
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
}

function loginCheck(data) {
    if (typeof data == 'string' && data.indexOf("<html>") == 0) {
        location.reload();
    }
}



