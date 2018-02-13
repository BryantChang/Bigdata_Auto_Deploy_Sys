$(document).ready(function(){
    $('#submitAddCluster').click(function(){
        var clustername = $('#clustername').val();
        var cdesc = $('#cdesc').val();
        var infoid = $('#infoid').val();
        var userid = $('#curUser').val();
        var data = {
            "clustername": clustername,
            "cdesc": cdesc,
            "infoid": infoid,
            "userid": userid
        };
        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/clustermanager/addcluster",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('添加成功');
                    $("#addCluster").modal('hide');
                    window.location.reload();
                }else {
                    alert('添加失败');
                }
            },
            dataType: 'json'
        });
    });
});