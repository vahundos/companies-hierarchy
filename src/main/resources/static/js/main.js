var ajaxUrl = "ajax/companies";
var form;

window.onload = function (ev) {
    form = $('#detailsForm');
};

function add() {
    form.find(":input").val("");
    $("#editRow").modal();
    loadCompanyListToSelect();
}

function save() {
    var company = {
        id: $("#id").val(),
        name: $("#name").val(),
        annualEarnings: $("#annualEarnings").val(),
        parentId: $("#parentId").val()
    };

    $.ajax({
        url: ajaxUrl,
        type: 'POST',
        data: JSON.stringify(company),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function () {
        $("#editRow").modal("hide");
    }).fail(function () {
        $("#editRow").modal("hide");
    });
}

function loadCompanyListToSelect() {
    $.ajax({
        url: ajaxUrl,
        dataType: 'json',
        type: 'GET',
        success: function (response) {
            var parentSelect = $("#parentId");
            parentSelect.empty();
            for (i in response) {
                parentSelect.append("<option value='" + response[i].id + "'>" + response[i].name + "</option>")
            }
        }
    });
}