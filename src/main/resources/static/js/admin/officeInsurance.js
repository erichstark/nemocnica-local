function search() {
    var insurances = $("#insurances");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var searchButton = $("#searchButton");
    var spinner = Ladda.create(document.querySelector("#searchButton"));
    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/office/insurance/search",
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
                    if ($("insurance-" + value.id).length == 0) {
                        noResults.hide();
                        searchResults.append(
                            '<tr id="insurance-' + value.id + '">' +
                            '<td>' + index + '</td>' +
                            '<td>' + value.name + '</td>' +
                            '<td><button class="btn btn-success btn-sm "> ' +
                            '<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>' +
                            '</button></td>' +
                            '</tr>');
                        var button = $("#insurance-" + value.id).find("button");
                        button.click(function () {
                            addInsurance(value.id)
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

function addInsurance(id) {
    var insurances = $("#insurances");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var noInsurances = $("#no-insurances");
    var insurance = $("#insurance-" + id);
    var button = insurance.find("button");
    button.removeClass('btn-success').addClass('btn-danger');
    button.unbind('click').click(function () {
        removeInsurance(id);
    });
    var glyph = insurance.find('span');
    glyph.removeClass('glyphicon-plus').addClass('glyphicon-minus');
    insurance.appendTo("#insurances");
    if (searchResults.has('tr').length == 0) {
        noResults.show();
    }
    noInsurances.hide();
}

function removeInsurance(id) {
    var insurances = $("#insurances");
    var noInsurances = $("#no-insurances");
    $("#insurance-" + id).remove();
    if (insurances.has('tr').length == 0) {
        noInsurances.show();
    }
}

function save() {
    var spinner = Ladda.create(document.querySelector("#saveButton"));
    var insurances = [];
    $("#insurances tr").each(function () {
        var id = $(this).attr('id')
        insurances.push(id.replace('insurance-', ''));
    });

    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/office/insurance/save",
        data: {
            id: $("input[name=office_id]").val(),
            insurances: insurances.join()
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