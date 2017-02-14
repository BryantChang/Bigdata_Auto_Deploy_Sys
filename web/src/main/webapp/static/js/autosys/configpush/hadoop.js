$(document).ready(function(){
    $('.pushConfig').click(function(){
        $("#config_push_body").html("配置推送中...");
        $("#configPushClose").attr("disabled", "disabled");
        $('#config_push').modal();
        var host = $(this).attr("host");
        var clusterType = $(this).attr("clustertype");
        var fileindex = $(this).attr("fileindex");
        var data = {
            'host': host,
            'fileindex': fileindex,
            'clustertype': clusterType
        };
        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configpush",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    $("#config_push_body").html("推送成功!!");
                    $("#configPushClose").removeAttr("disabled");
                }else {
                    $("#config_push_body").html("推送失败,原因:" + data.msg);
                    $("#configPushClose").removeAttr("disabled");
                }
            },
            dataType: 'json'
        });
    });
    var curIndex = 0;
    var nodesNum = 0;
    var bodyHtml = "";
    function pushConfigs(nodes, index, clusterType, fileindex) {
        var url = baseUrl + "/api/configpush";
        var hostName = nodes[index];
        bodyHtml += "正在推送至:" + hostName + "<br/>";
        $("#config_push_body").html(bodyHtml);
        var data = {
            'host': hostName,
            'fileindex': fileindex,
            'clustertype': clusterType
        };
        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configpush",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    bodyHtml += "推送成功<br/>";
                    $("#config_push_body").html(bodyHtml);
                    curIndex++;
                    if(curIndex == nodesNum ) {
                        $("#configPushClose").removeAttr("disabled");
                        curIndex = 0;
                        nodesNum = 0;
                        bodyHtml = "";
                    }else {
                        pushConfigs(nodes, curIndex, clusterType, fileindex);
                    }
                }else {
                    bodyHtml += "推送失败,原因:" + data.msg + "<br/>";
                    $("#config_push_body").html(bodyHtml);
                    curIndex++;
                    if(curIndex == nodesNum) {
                        $("#configPushClose").removeAttr("disabled");
                        curIndex = 0;
                        nodesNum = 0;
                        bodyHtml = "";
                    }else {
                        pushConfigs(nodes, curIndex, clusterType, fileindex);
                    }
                }
            },
            dataType: 'json'
        });
    }

    $('.pushAll').click(function(){
        var allNodes = $(this).attr("nodes");
        var nodesArr = allNodes.split(",");
        var clusterType = $(this).attr("clustertype");
        var fileindex = $(this).attr("fileindex");
        nodesNum = nodesArr.length;
        bodyHtml += "配置推送中...<br/>";
        $("#config_push_body").html(bodyHtml);
        $("#configPushClose").attr("disabled", "disabled");
        $('#config_push').modal();
        pushConfigs(nodesArr, curIndex, clusterType, fileindex);
    });
});