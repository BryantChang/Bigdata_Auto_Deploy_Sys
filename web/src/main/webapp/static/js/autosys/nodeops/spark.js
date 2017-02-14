$(document).ready(function(){
    $('.sparkClusterOps').click(function(){
        $("#spark_node_ops_body").html("执行操作中...");
        $("#sparkNodeOpsClose").attr("disabled", "disabled");
        var type = $(this).attr("type");
        var ops = $(this).attr("ops");
        $('#sparkNodeOpsModal').modal('show');
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/nodeops/" + type +  "/" + ops + "cluster",
            success: function(data, status){
                if(data.code == 100000) {
                    $("#spark_node_ops_body").html("操作成功!!");
                    $("#sparkNodeOpsClose").removeAttr("disabled");
                }else {
                    $("#spark_node_ops_body").html("操作失败,原因:" + data.msg);
                    $("#sparkNodeOpsClose").removeAttr("disabled");
                }
            },
            dataType: 'json'
        });
    });



    $('.sparkNodeOps').click(function(){
        var nodetype = $(this).attr("nodetype");
        var ip = $(this).attr("ip");
        var id = $(this).attr("nodeid");
        var targetId = "status_" + nodetype + "_" + id;
        var resStr = "";
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/nodeops/spark/checknode?type=" + nodetype + "&ip=" + ip,
            success: function(data, status){
                if(data.code == 100000) {
                    resStr = "&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:" + data.msg;
                    $("#" + targetId).html(resStr);
                }else {
                    resStr = "&nbsp;&nbsp;&nbsp;&nbsp;<i class='icon-play'></i>状态:" + 查询错误;
                    $("#" + targetId).html(resStr);
                }
            },
            dataType: 'json'
        });
    });


    $('.sparkOps').click(function(){
        var ip = $(this).attr("ip");
        var clusterType = $(this).attr("clustertype");
        var nodeType = $(this).attr("nodetype");
        var opsType = $(this).attr("opstype");
        var masterIp = $(this).attr("masterip");
        $("#spark_node_ops_body").html("执行操作中...");
        $("#sparkNodeOpsClose").attr("disabled", "disabled");
        $('#sparkNodeOpsModal').modal('show');
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/nodeops/spark/ops?masterip=" + masterIp + "&clustertype=" + clusterType +  "&nodetype=" + nodeType + "&ip=" + ip + "&opstype=" + opsType,
            success: function(data, status){
                if(data.code == 100000) {
                    $("#spark_node_ops_body").html("操作成功!!");
                    $("#sparkNodeOpsClose").removeAttr("disabled");
                }else {
                    $("#spark_node_ops_body").html("操作失败,原因:" + data.msg);
                    $("#sparkNodeOpsClose").removeAttr("disabled");
                }
            },
            dataType: 'json'
        });
    });

    $('.sparkViewLogbtn').click(function(){
        var logPath = $(this).attr('logname');
        var opsStr = "查看方式:tail -f " + spark_home + "/logs/" +  logPath + ".out";
        $('#view_log_body').html(opsStr);
    });


});