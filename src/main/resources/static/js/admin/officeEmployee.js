function search() {
    var employees = $("#employees");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var searchButton = $("#searchButton");
    var spinner = Ladda.create(document.querySelector("#searchButton"));
    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/office/employee/search",
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
                    if ($("employee-" + value.username).length == 0) {
                        noResults.hide();
                        searchResults.append(
                            '<tr id="employee-' + value.username + '">' +
                            '<td>' + index + '</td>' +
                            '<td>' + value.username + '</td>' +
                            '<td>' + value.name + '</td>' +
                            '<td><button class="btn btn-success btn-sm "> ' +
                            '<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>' +
                            '</button></td>' +
                            '</tr>');
                        var button = $("#employee-" + value.username).find("button");
                        button.click(function () {
                            addEmployee(value.username)
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

function addEmployee(id) {
    var employees = $("#employees");
    var searchResults = $("#search-results");
    var noResults = $("#no-results");
    var noEmployees = $("#no-employees");
    var employee = $("#employee-" + id);
    var button = employee.find("button");
    button.removeClass('btn-success').addClass('btn-danger');
    button.unbind('click').click(function () {
        removeEmployee(id);
    });
    var glyph = employee.find('span');
    glyph.removeClass('glyphicon-plus').addClass('glyphicon-minus');
    employee.appendTo("#employees");
    if (searchResults.has('tr').length == 0) {
        noResults.show();
    }
    noEmployees.hide();
}

function removeEmployee(id) {
    var employees = $("#employees");
    var noEmployees = $("#no-employees");
    $("#employee-" + id).remove();
    if (employees.has('tr').length == 0) {
        noEmployees.show();
    }
}

function save() {
    var spinner = Ladda.create(document.querySelector("#saveButton"));
    var employees = [];
    $("#employees tr").each(function () {
        var id = $(this).attr('id')
        employees.push(id.replace('employee-', ''));
    });

    spinner.start();
    $.ajax({
        type: "POST",
        timeout: 5000,
        dataType: 'json',
        url: "/admin/office/employee/save",
        data: {
            id: $("input[name=office_id]").val(),
            employees: employees.join()
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