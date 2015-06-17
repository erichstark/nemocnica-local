function deleteDisplay(id) {
    stopClickPropagation();
    if (confirm("Naozaj chcete zmazať túto obrazovku?")) {
        $.ajax({
            type: "POST",
            timeout: 5000,
            url: "/admin/display/delete",
            dataType: 'json',
            data: {
                id: id
            },
            success: function (data) {
                loginCheck(data);
                $("#" + id).remove();
                showMessage(data.message, data.type);
                if ($("#displays").has('tr').length == 0) {
                    $("#noDisplays").show();
                }
            },
            error: function () {
                showMessage("Zlyhala komunikácia so serverom.", 1);
            }
        });
    }
}

function search() {
    var offices = $("#offices");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var searchButton = $("#searchButton");
    var spinner = Ladda.create(document.querySelector("#searchButton"));
    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/display/search",
        data: {
            searchTerm: $("#searchTerm").val()
        },
        success: function (data) {
            spinner.stop();
            loginCheck(data);
            $("#search-container").show();
            if (data.length == 0) {
                noResults.show();
            } else {
                searchResults.empty();
                noResults.show();
                $.each(data, function (index, value) {
                    if ($("#office-" + value.id).length == 0) {
                        noResults.hide();
                        searchResults.append(
                            '<tr id="office-' + value.id + '">' +
                            '<td>' + value.name + '</td>' +
                            '<td>' + value.specializations + '</td>' +
                            '<td>' + value.employees + '</td>' +
                            '<td><button class="btn btn-success btn-sm "> ' +
                            '<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>' +
                            '</button></td>' +
                            '</tr>');
                        var button = $("#office-" + value.id).find("button");
                        button.click(function () {
                            addOffice(value.id)
                        });
                    }
                });
            }
        },
        error: function () {
            spinner.stop();
            showMessage("Zlyhala komunikácia so serverom.", 1);
        }
    });
}

function addOffice(id) {
    var offices = $("#offices");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var noOffices = $("#no-offices");
    var office = $("#office-" + id);
    var button = office.find("button");
    button.removeClass('btn-success').addClass('btn-danger');
    button.unbind('click').click(function () {
        removeOffice(id);
    });
    var glyph = office.find('span');
    glyph.removeClass('glyphicon-plus').addClass('glyphicon-minus');
    office.appendTo("#offices");
    if (searchResults.has('tr').length == 0) {
        noResults.show();
    }
    noOffices.hide();
}

function removeOffice(id) {
    var offices = $("#offices");
    var noOffices = $("#no-offices");
    $("#office-" + id).remove();
    if (offices.has('tr').length == 0) {
        noOffices.show();
    }
}

function save() {
    var spinner = Ladda.create(document.querySelector("#saveButton"));
    var offices = [];
    $("#offices tr").each(function () {
        var id = $(this).attr('id');
        offices.push(id.replace('office-', ''));
    });
    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/display/save",
        data: {
            id: $("#id").val(),
            originalID: $("#originalID").val(),
            offices: offices.join()
        },
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