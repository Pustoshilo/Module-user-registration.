$(document).ready(function(){
    $('#addPerson').click(function () {
        addPerson();
    });
    $('#deletePerson').click(function () {
        deletePerson();
    });
});


function addPerson() {
    $('#personForm').prop('action', 'add.form');
    $('#personButton').prop('value', 'Add person');
    $('#personForm').show();
}

function deletePerson() {
    $('#personForm').prop('action', 'delete.form');
    $('#personButton').prop('value', 'Delete person');
    $('#personForm').show();
}