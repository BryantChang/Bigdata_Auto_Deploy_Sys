$(document).ready(function(){
    $('.hadoopClusterOps').click(function(){
        $("#hadoop_node_ops_body").html("执行操作中...");
        $("#hadoopNodeOpsClose").attr("disabled", "disabled");
        var type = $(this).attr("type");
        var ops = $(this).attr("ops");
        $('#hadoopNodeOpsModal').modal('show');
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/nodeops/hadoop/" + type +  "/" + ops + "cluster",
            success: function(data, status){
                if(data.code == 100000) {
                    $("#hadoop_node_ops_body").html("操作成功!!");
                    $("#hadoopNodeOpsClose").removeAttr("disabled");
                }else {
                    $("#hadoop_node_ops_body").html("操作失败,原因:" + data.msg);
                    $("#hadoopNodeOpsClose").removeAttr("disabled");
                }
            },
            dataType: 'json'
        });
    });



    $('.hadoopNodeOps').click(function(){
        var nodetype = $(this).attr("nodetype");
        var ip = $(this).attr("ip");
        var id = $(this).attr("nodeid");
        var targetId = "status_" + nodetype + "_" + id;
        var resStr = "";
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/nodeops/hadoop/checknode?type=" + nodetype + "&ip=" + ip,
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


    $('.hadoopOps').click(function(){
        var ip = $(this).attr("ip");
        var clusterType = $(this).attr("clustertype");
        var nodeType = $(this).attr("nodetype");
        var opsType = $(this).attr("opstype");
        $("#hadoop_node_ops_body").html("执行操作中...");
        $("#hadoopNodeOpsClose").attr("disabled", "disabled");
        $('#hadoopNodeOpsModal').modal('show');
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/nodeops/hadoop/ops?clustertype=" + clusterType +  "&nodetype=" + nodeType + "&ip=" + ip + "&opstype=" + opsType,
            success: function(data, status){
                if(data.code == 100000) {
                    $("#hadoop_node_ops_body").html("操作成功!!");
                    $("#hadoopNodeOpsClose").removeAttr("disabled");
                }else {
                    $("#hadoop_node_ops_body").html("操作失败,原因:" + data.msg);
                    $("#hadoopNodeOpsClose").removeAttr("disabled");
                }
            },
            dataType: 'json'
        });
    });

    $('.hadoopViewLogbtn').click(function(){
        var logPath = $(this).attr('logname');
        var opsStr = "查看方式:tail -f " + hadoop_home + "/logs/" +  logPath + ".log";
        $('#view_log_body').html(opsStr);
    });

});







