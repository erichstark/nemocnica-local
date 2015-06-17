function saveOffice() {
    var spinner = Ladda.create(document.querySelector("#saveButton"));
    var id = $('input[name=office_id]').val();
    var enabled = $('input[name=enabled]').is(':checked');
    var name = $('input[name=name]').val();
    var phone = $('input[name=phone]').val();

    spinner.start();

    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/office/save",
        data: {
            id: id,
            enabled: enabled,
            name: name,
            phone: phone
        },
        success: function (data) {
            loginCheck(data);
            spinner.stop();
            $.each($('a[name=link]'), function (index, value) {

                var href = $(value).attr('href');
                var link = href.substring(0, href.lastIndexOf('/') + 1);
                $(value).attr('href', link + data.id);
            });
            $('input[name=office_id]').val(data.id);
            showMessage(data.message, data.type);
        },
        error: function () {
            spinner.stop();
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
}

function check(element) {
    console.log($('input[name=office_id]').val())
    if (!$('input[name=office_id]').val()) {
        alert("Najprv ulož ambulanciu!");
        return false;
    } else {
        window.location = $(element).attr('href');
    }
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