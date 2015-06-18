var waitningNumber = 8;

function setTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    document.getElementById('time').innerHTML = checkTime(h) + ":" + checkTime(m);
    var t = setTimeout(function () {
        setTime()
    }, 500);
}

function setDate() {
    var today = new Date();
    var d = today.getUTCDate();
    var m = today.getUTCMonth();
    var y = today.getFullYear();
    document.getElementById('date').innerHTML = checkTime(d) + "." + checkTime(m + 1) + "." + y;
    setTime();
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i
    }
    ;  // add zero in front of numbers < 10
    return i;
}

function removeRow(element) {
    var tr = $(element).parent().parent();
    tr.remove();
}

function invoke(element, str) {
    var tr = $(element).parent().parent();

    $.each($('table#waitning tr'), function (index, value) {
        if ($(value).hasClass('success')) {
            $(value).removeClass('success');
            $(value).addClass('warning');
        }
    });

    $.each($('table#ordered tr'), function (index, value) {
        if ($(value).hasClass('success')) {
            $(value).removeClass('success');
            $(value).addClass('warning');
        }
    });

    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/office/invoke",
        data: {
            id: $("input[name=office_id]").val(),
            str: str
        },
        success: function (data) {
            loginCheck(data);
            if ($(tr).hasClass('success')) {
                $(tr).removeClass('success');
                $(tr).addClass('warning');
            } else if (!$(tr).hasClass('warning')) {
                $(tr).addClass('success');
            }
            showMessage(data.message, data.type);
        },
        error: function () {
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
}

function newOrderNumber() {
    waitningNumber += 1;

    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();

    var iHtml = '<tr>';
    iHtml += '<td>' + waitningNumber + '</td>';
    iHtml += '<td>' + checkTime(h) + ":" + checkTime(m) + '</td>';
    iHtml += '<td class="text-center"><a href="#" style="color: black" onclick="invoke(this, \'' + waitningNumber + '\')"><span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span></a></td>';
    iHtml += '<td class="text-center"><a href="#" style="color: #c9302c" onclick="removeRow(this)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>';
    iHtml += '</tr>';

    console.log(iHtml);
    $('table#waitning').append(iHtml);
}