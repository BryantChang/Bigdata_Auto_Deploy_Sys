$(document).ready(function(){
    $('#submitAddCluster').click(function(){
        var clustername = $('#clustername').val();
        var cdesc = $('#cdesc').val();
        var infoid = $('#infoid').prop("selected");
        alert(infoid)
    });
});