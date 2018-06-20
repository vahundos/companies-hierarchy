var ajaxUrl = "ajax/companies";
var form;

window.onload = function (ev) {
    form = $('#detailsForm');

    loadCompaniesWithChildren();
};

function add() {
    form.find(":input").val("");
    $("#removeButton").addClass("invisible");
    $("#editRow").modal();
    loadCompanyListToSelect();
}

function update(company) {
    loadCompanyListToSelect(company.parent);

    $("#id").val(company.id);
    $("#name").val(company.name);
    $("#annualEarnings").val(company.annualEarnings);
    $("#parentId").val(company.parent);

    $("#removeButton").removeClass("invisible");
    $("#editRow").modal();
}

function save() {
    var company = {
        id: $("#id").val(),
        name: $("#name").val(),
        annualEarnings: $("#annualEarnings").val(),
        parentId: $("#parentId").val()
    };

    var method;
    if (company.id === "") {
        method = 'POST';
    } else {
        method = 'PUT';
    }

    $.ajax({
        url: ajaxUrl,
        type: method,
        data: JSON.stringify(company),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function () {
        $("#editRow").modal("hide");
        loadCompaniesWithChildren()
    }).fail(function () {
        $("#editRow").modal("hide");
        loadCompaniesWithChildren()
    });
}

function loadCompanyListToSelect(selectedId) {
    $.ajax({
        url: ajaxUrl,
        dataType: 'json',
        type: 'GET',
        success: function (response) {
            var parentSelect = $("#parentId");
            parentSelect.empty();
            parentSelect.append("<option value='null'> </option>");
            for (i in response) {
                if (selectedId !== undefined && selectedId === response[i].id) {
                    parentSelect.append("<option selected='selected' value='" + response[i].id + "'>" + response[i].name + "</option>");
                } else {
                    parentSelect.append("<option value='" + response[i].id + "'>" + response[i].name + "</option>");
                }
            }
        }
    });
}

function remove() {
    $.ajax({
        url: ajaxUrl + "/" + $("#id").val(),
        type: 'DELETE',
        success: function () {
            $("#editRow").modal("hide");
            loadCompaniesWithChildren();
        }
    })
}

function loadCompaniesWithChildren() {
    $.ajax({
        url: ajaxUrl + "/children",
        dataType: 'json',
        type: 'GET',
        success: function (response) {
            updateTree(response);
            $('#tree').on('nodeSelected', function(event, data) {
                update(data);
            });
        }
    })
}

function updateTree(response) {
    recursiveUpdateResponse(response);
    $("#tree").treeview({
        data: response
    })
}

function recursiveUpdateResponse(response) {
    for (i in response) {
        var company = response[i];
        company.text = "<b>" + company.name + "</b> | " + company.annualEarnings + "$";
        company.selectable = true;
        company.parent = company.parentId;
        if (company.children.length > 0) {
            company.text += " | " + company.annualEarningsWithChildren + "$";
            company.nodes = company.children;
            recursiveUpdateResponse(company.children);
        }
    }
}