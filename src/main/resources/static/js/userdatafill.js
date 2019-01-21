$(document).ready(function() {


    var userListStr = $('#jsonUsersString').text();
    var userList = jQuery.parseJSON( userListStr );
    console.log(userList);
    console.log("id = " + userList[0].id);
    console.log("name = " + userList[0].name);
    console.log("login = " + userList[0].login);
    console.log("password = " + userList[0].password);
    console.log("role = " + userList[0].roles[0].roleName);


    var myform = document.getElementById("jsonUsersString").innerHTML;
    console.log(myform);





    for(var i = 0; i < userList.length; i++) {
        var role = userList[i].roles[0];
        console.log(userList[i].id + ' ' + userList[i].login + ' ' + userList[i].name + ' ' + userList[i].password + ' ' + role.roleName + '\r\n');

        $('#listrole').append(
            '<tr>' +
            '<td>' + userList[i].id + '</td>' +
            '<td>' + userList[i].login + '</td>' +
            '<td>' + userList[i].name + '</td>' +
            '<td>' + userList[i].password + '</td>' +
            '<td>' + role.roleName + '</td>' +
            '<td>' +
            '<button class="btn btn-success" id="edButton" data-toggle="modal" data-target="#myModal">Edit</button>' +
            ' ' +
            '<button type="button" class="btn btn-primary" id="delButton">Delete</button>'
            + '</td>'
            + '</tr>'
        );

    }





});