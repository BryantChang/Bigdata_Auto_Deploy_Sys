$(document).ready(function(){
    $('.generateHadoopConfig').click(function(){
        var fileindex = $(this).attr("item");
        var data = {
            "fileindex": fileindex
        };
        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/hadoop/generateconfig",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('生成成功');
                }else {
                    alert('生成失败');
                }
            },
            dataType: 'json'
        });
    });

    $('.addFromCurrentXML').click(function(){
        var fileindex = $(this).attr("fileindex");
        var data = {
            "fileindex": fileindex
        };
        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/hadoop/generatefromcurrentxml",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('操作成功');
                    window.location.reload();
                }else {
                    alert('操作失败');
                }
            },
            dataType: 'json'
        });
    });


    $('.addFromCurrentFile').click(function(){
        var fileindex = $(this).attr("fileindex");
        var data = {
            "fileindex": fileindex
        };
        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/spark/addfromcurfile",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('操作成功');
                    window.location.reload();
                }else {
                    alert('操作失败');
                }
            },
            dataType: 'json'
        });
    });


    $('.submitDeleteHadoopXmlSetting').click(function(){
        var id = $(this).attr('del');
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/configmanage/hadoop/delxmlsetting?id="+id,
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


    $('.submitAddHadoopXmlSetting').click(function(){
        var settingName = $('#hadoop_xml_setting_name_add').val();
        var settingValue = $('#hadoop_xml_setting_value_add').val();
        var settingDescription = $('#hadoop_xml_setting_description_add').val();
        var settingFilename = $('#hadoop_xml_setting_filename_add').val();
        var data = {
            'name': settingName,
            'value': settingValue,
            'description': settingDescription,
            'filename': settingFilename
        };

        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/hadoop/addxmlsetting",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('添加成功');
                    window.location.reload();
                }else{
                    alert('添加失败');
                }
            },
            dataType: 'json'
        });
    });


    $('.submitUpdateHadoopXmlSetting').click(function(){
        var settingId = $(this).attr("update");
        var settingName = $('#hadoop_xml_setting_name_' + settingId).val();
        var settingValue = $('#hadoop_xml_setting_value_' + settingId).val();
        var settingDescription = $('#hadoop_xml_setting_description_' + settingId).val();
        var fileName = $('#filename').val();
        var data = {
            'id': settingId,
            'name': settingName,
            'value': settingValue,
            'description': settingDescription,
            'filename': fileName
        };

        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/hadoop/updatexmlsetting",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('修改成功');
                    window.location.reload();
                }else {
                    alert('修改失败');
                }
            },
            dataType: 'json'
        });
    });
});