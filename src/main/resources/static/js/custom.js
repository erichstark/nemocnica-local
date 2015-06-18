jQuery(document).ready(function ($) {
    $(".clickable-row").click(function () {
        window.document.location = $(this).data("href");
    });
    setNavigation();

    if (window.location.href.indexOf("admin/office/edit") > -1 || window.location.href.indexOf("patientdetail") > -1
        || window.location.href.indexOf("orders") > -1) {
        minutesToHour();
    } else if (window.location.href.indexOf("admin/office/hours/") > -1) {
        loadHours();
    } else if (window.location.href.indexOf("call") > -1) {
        setDate();
        minutesToHour();
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


function registerFacility() {
    var spinner = Ladda.create(document.querySelector("#setupFacility"));
    spinner.start();
    $.ajax({
        type: "POST",
        url: "/setup/facility",
        data: $("#facility-setup").serialize(),
        success: function (data) {
            spinner.stop();
            if (data.type == 2) {
                $("#facility-setup").hide();
                $("#admin").show();
            } else {
                showMessage(data.message, data.type);
            }
        },
        error: function () {
            spinner.stop();
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
}

function registerAdmin() {
    var spinner = Ladda.create(document.querySelector("#createAdmin"));
    spinner.start();
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: "/setup/admin",
        data: {
            username: $("#admin-name").val(),
            password: $("#admin-password").val()
        },
        success: function (data) {
            spinner.stop();
            if (data.type == 2) {
                $("#admin").hide();
                $("#success").show();
            } else {
                showMessage(data.message, data.type);
            }
        },
        error: function () {
            spinner.stop();
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
}

function saveFacility() {
    var spinner = Ladda.create(document.querySelector("#saveButton"));
    spinner.start();
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: "/admin/facility",
        data: $("#facility").serialize(),
        success: function (data) {
            spinner.stop();
            if (data == true) {
                showMessage("Zmeny uložené.", 2);
            } else {
                showMessage("Nepodarilo sa uložiť zmeny.", 1);
            }
        },
        error: function () {
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



