function search() {
    var specializations = $("#specializations");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var searchButton = $("#searchButton");
    var spinner = Ladda.create(document.querySelector("#searchButton"));
    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/office/specialization/search",
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
                    if ($("specialization-" + value.id).length == 0) {
                        noResults.hide();
                        searchResults.append(
                            '<tr id="specialization-' + value.id + '">' +
                            '<td>' + index + '</td>' +
                            '<td>' + value.name + '</td>' +
                            '<td><button class="btn btn-success btn-sm "> ' +
                            '<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>' +
                            '</button></td>' +
                            '</tr>');
                        var button = $("#specialization-" + value.id).find("button");
                        button.click(function () {
                            addSpecialization(value.id)
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

function addSpecialization(id) {
    var specializations = $("#specializations");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var noSpecializations = $("#no-specializations");
    var specialization = $("#specialization-" + id);
    var button = specialization.find("button");
    button.removeClass('btn-success').addClass('btn-danger');
    button.unbind('click').click(function () {
        removeSpecialization(id);
    });
    var glyph = specialization.find('span');
    glyph.removeClass('glyphicon-plus').addClass('glyphicon-minus');
    specialization.appendTo("#specializations");
    if (searchResults.has('tr').length == 0) {
        noResults.show();
    }
    noSpecializations.hide();
}

function removeSpecialization(id) {
    var specializations = $("#specializations");
    var noSpecializations = $("#no-specializations");
    $("#specialization-" + id).remove();
    if (specializations.has('tr').length == 0) {
        noSpecializations.show();
    }
}

function save() {
    var spinner = Ladda.create(document.querySelector("#saveButton"));
    var specializations = [];
    $("#specializations tr").each(function () {
        var id = $(this).attr('id')
        specializations.push(id.replace('specialization-', ''));
    });

    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/office/specialization/save",
        data: {
            id: $("input[name=office_id]").val(),
            specializations: specializations.join()
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