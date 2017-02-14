$(document).ready(function(){
    $('#submitAddSparkNode').click(function(){
        var hostname = $('#hostname').val();
        var ip = $('#ip').val();
        var os = $('#os').val();
        var sshuser = $('#sshuser').val();
        var sshpass = $('#sshpass').val();
        var sshport = $('#sshport').val();
        var master = $("#master").prop("checked");
        var slave = $("#slave").prop("checked");

        if((hostname == "") || (ip == "")
            || (os == "") || (sshuser == "")
            || (sshpass == "") || (sshport == "")) {
            alert("相关信息不能为空！");
        }else if ((master || slave)== false){
            alert("您必须选择一个节点角色");
        }else {
            var data = {
                'hostname': hostname,
                'ip': ip,
                'os': os,
                'sshuser': sshuser,
                'sshpass': sshpass,
                'sshport': sshport,
                'master': master,
                'slave': slave,
            };
            $.ajax({
                type: 'POST',
                url: baseUrl + "/api/clustermanager/spark/addnode",
                data: data,
                success: function(data, status){
                    if(data.code == 100000) {
                        alert('添加成功');
                        $("#addNode").modal('hide');
                        window.location.reload();
                    }else {
                        alert('添加失败');
                    }
                },
                dataType: 'json'
            });
        }

    });



    $('.submitUpdateSparkNode').click(function(){
        var id = $(this).attr('update');
        var hostname = $('#hostname_'+id).val();
        var ip = $('#ip_'+id).val();
        var os = $('#os_'+id).val();
        var sshuser = $('#sshuser_'+id).val();
        var sshpass = $('#sshpass_'+id).val();
        var sshport = $('#sshport_'+id).val();
        var master = $("#master_"+id).prop("checked");
        var slave = $("#slave_"+id).prop("checked");

        if((hostname == "") || (ip == "")
            || (os == "") || (sshuser == "")
            || (sshpass == "") || (sshport == "")) {
            alert("相关信息不能为空！");
        }else if ((master || slave)== false){
            alert("您必须选择一个节点角色");
        }else {
            var data = {
                'id': id,
                'hostname': hostname,
                'ip': ip,
                'os': os,
                'sshuser': sshuser,
                'sshpass': sshpass,
                'sshport': sshport,
                'master': master,
                'slave': slave,
            };
            $.ajax({
                type: 'POST',
                url: baseUrl + "/api/clustermanager/spark/updatenode",
                data: data,
                success: function(data, status){
                    if(data.code == 100000) {
                        alert('修改成功');
                        window.location.reload();
                    }else{
                        alert('修改失败');
                    }
                },
                dataType: 'json'
            });
        }
    });

    $('.submitDeleteSparkNode').click(function(){
        var id = $(this).attr('del');
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/clustermanager/spark/delnode?id="+id,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('删除成功');
                    window.location.reload();
                }else {
                    alert('删除失败');
                }
            },
            dataType: 'json'
        });
    });
});