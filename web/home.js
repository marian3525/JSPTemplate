$(document).ready(function() {
    console.log("loaded");
    $("#updateModal").on("hidden.bs.modal", onUpdateModalShowing);
    $("#addModal").on("hidden.bs.modal", onAddModalShowing);

    $("#update-confirm-button").click(onUpdateConfirmClick);
    //$("#add-car-button").click(onAddCarClick);
    $("#add-entity-confirm-button").click(onAddEntityConfirmClick);

    // populate the list
    populateList();
});

function populateList() {
    data = "";
    $.get("/JSPTemplate_war_exploded/homeService",
        {},
        onEntityListResponse
    );
}

function onEntityListResponse(data) {
    console.log("Received server response to the filter" + data);
    //console.log("Map: " + JSON.parse(data));
    // insert the divs in the 'list' div
    //car_no1 = JSON.parse(data)[0];

    // build html
    var entities = data;

    // ID in the row needed for the jquery selector
    var html_str = '<table class="table table-striped"> <tr> <th>Header1</th> <th>Header2</th> <th>Header3</th></tr>';

    entities.forEach(function(entity) {
        console.log(entity);
        // create a line for each car returned

        // set the ID of the line to the ID of the entity
        html_str = html_str + '<tr id="' + entity["id"] + '">';

        html_str = html_str + '<td>';
        html_str = html_str + entity["username"];
        html_str = html_str + '</td>';

        html_str = html_str + '<td>';
        html_str = html_str + entity["password"];
        html_str = html_str + '</td>';

        // add the buttons, row with bootstrap
        var deleteButton_html = '<button type="button" class="btn btn-danger col-4" onclick="onDeleteClick('+ entity["id"] + ')">Delete</button>';  // send delete request to the server
        var updateButton_html = '<button type="button" id="update-button" class="btn btn-info col-4" data-toggle="modal" data-target="#updateModal" onclick="onUpdateClick(' + entity["id"] + ')">Update</button>';

        html_str = html_str + '<td class="container"> <div class="row">';
        html_str = html_str + deleteButton_html;
        html_str = html_str + updateButton_html;
        html_str = html_str + '</div> </td>';

        html_str = html_str + '</tr>';
    });

    html_str = html_str + '</table>';


    $("#entity-list").html(html_str);
}

function onAddEntityConfirmClick(ev) {
    console.log("Add button pressed");

    errors = "";

    username = $("#header1_a")[0].value;
    if(username.length === 0) {
        errors += "Header1 must be a number\n";
    }

    pass = $("#header2_a")[0].value;
    if(pass.length === 0) {
        errors += "Header2 must have a value\n";
    }

    if(errors.length>0) {
        alert(errors);
        return;
    }

    // send the request to the server

    $.ajax({
        type: 'PUT',
        url: '/JSPTemplate_war_exploded/homeService?' + $.param({
            "username": username,
            "password": pass
        }),
        success: function() {
            populateList();
        }
    });
}

function onAddModalShowing() {
    console.log("Add modal showing");

    // clear fields
    $("#header1_a")[0].value = "";

    $("#header2_a")[0].value="";
}

function onUpdateModalShowing() {
    console.log("Update modal showing");

    // clear fields

    $("#header2")[0].value="";

    $("#header3")[0].value="";
}




function onDeleteClick(eid) {
    $.ajax({
        type: 'DELETE',
        url: '/JSPTemplate_war_exploded/homeService?' + $.param({
                "id": eid
            }),
        success: function() {
            populateList();
        }
    });
}

function onUpdateClick(eid) {
    console.log("Updating car " + eid);
    currentlyUpdatedId = eid;

    // populate the fields
    row = $("#" + eid)[0];
    console.log(row);

    $("#header1-id")[0].value = eid;

    var usr = row.children[0].textContent;
    $("#header2")[0].value = usr;
    console.log(usr);

    var pass = row.children[1].textContent;
    $("#header3")[0].value = pass;
    console.log(pass);
}

function onUpdateConfirmClick(ev) {
    console.log("Update confirm button clicked");

    errors = "";

    eid = $("#header1-id")[0].value;
    if(isNaN(eid) || eid.length === 0) {
        errors += "id must be a number\n";
    }

    usr = $("#header2")[0].value;
    if(usr.length === 0) {
        errors += "name must have a value\n";
    }

    pass = $("#header3")[0].value;
    if(pass.length === 0) {
        errors += "other_name must have a value\n";
    }

    if(errors.length>0) {
        alert(errors);
        return;
    }

    // send the request to the server
    updated = {eid: eid, username : usr, password: pass};

    console.log("updated", updated);

    $.ajax({
        type: 'POST',
        url: '/JSPTemplate_war_exploded/homeService?' + $.param({
            "id": eid,
            "username": usr,
            "password": pass
        }),
        success: function() {
            populateList();
        }
    });
}