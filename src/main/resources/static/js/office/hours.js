function formDataToJsonHours() {
    var inputs = $('input');
    var selects = $('select');

    var json = []

    $.each(inputs, function (index, value) {
        var name = $(value).attr('name');
        var id = name.substring(0, 1);
        var columnName = name.substring(2);
        if (jQuery.isEmptyObject(json[id]))
            json[id] = {}
        json[id][columnName] = $(value).val();

    });

    $.each(selects, function (index, value) {
        var name = $(value).attr('name');
        var id = name.substring(0, 1);
        var columnName = name.substring(2);
        json[id][columnName] = $(value).val().replace(/\s+/g, '');
    });

    return json;
}

function saveHours() {
    var hours = formDataToJsonHours();
    var spinner = Ladda.create(document.querySelector("#saveButton"));

    spinner.start();
    $.ajax({
        url: "/office/hours/save",
        method: 'POST',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        data: JSON.stringify(hours),
        success: function (data) {
            loginCheck(data);
            spinner.stop();
            showMessage(data.message, data.type);
        },
        error: function () {
            spinner.stop();
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
}

function minutesToHour() {
    $.each($('td span'), function (index, value) {
        if ($(value).text() != "") {
            var num = parseInt($(value).text().replace(/\s+/g, ''));
            $(value).text(minutesToStr(num));
        }
    });
}
function minutesToStr(minutes) {
    var hours = leftPad(Math.floor(Math.abs(minutes) / 60));
    var minutes = leftPad(Math.abs(minutes) % 60);
    return hours + ':' + minutes;
}
function leftPad(number) {
    return ((number < 10 && number >= 0) ? '0' : '') + number;
}