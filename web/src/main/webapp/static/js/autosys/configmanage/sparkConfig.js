$(document).ready(function(){
    $('.generateSparkConfig').click(function(){
        var fileindex = $(this).attr("item");
        var data = {
            "fileindex": fileindex
        };
        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/spark/generateconfig",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('生成成功');
                }
            },
            dataType: 'json'
        });
    });
    $('.submitUpdateSparkDefaultSetting').click(function(){
        var settingId = $(this).attr("update");
        var settingName = $('#default_setting_name_' + settingId).val();
        var settingValue = $('#default_setting_value_' + settingId).val();
        var settingDescription = $('#default_setting_description_' + settingId).val();
        var data = {
            'id': settingId,
            'name': settingName,
            'value': settingValue,
            'description': settingDescription,
            'filename': "spark-defaults.conf"
        };

        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/spark/updatedefault",
            data: data,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('修改成功');
                    window.location.reload();
                }
            },
            dataType: 'json'
        });
    });

    $('.submitDeleteSparkDefaultSetting').click(function(){
        var id = $(this).attr('del');
        $.ajax({
            type: 'GET',
            url: baseUrl + "/api/configmanage/spark/deldefault?id="+id,
            success: function(data, status){
                if(data.code == 100000) {
                    alert('删除成功');
                    window.location.reload();
                }
            },
            dataType: 'json'
        });
    });
    $('.submitAddSparkDefaultSetting').click(function(){
        var settingName = $('#default_setting_name_add').val();
        var settingValue = $('#default_setting_value_add').val();
        var settingDescription = $('#default_setting_description_add').val();
        var data = {
            'name': settingName,
            'value': settingValue,
            'description': settingDescription,
            'filename': "spark-defaults.conf"
        };

        $.ajax({
            type: 'POST',
            url: baseUrl + "/api/configmanage/spark/adddefault",
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
});