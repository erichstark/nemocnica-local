function loadHours() {
    if ($('table tr').length != 6) {
        location.reload()
    }
};

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
        url: "/admin/office/hours/save",
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