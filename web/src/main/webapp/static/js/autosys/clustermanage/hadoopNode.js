$(document).ready(function(){ 
	$('#submitAddHadoopNode').click(function(){
		var hostname = $('#hostname').val();
		var ip = $('#ip').val();
		var os = $('#os').val();
		var sshuser = $('#sshuser').val();
		var sshpass = $('#sshpass').val();
		var sshport = $('#sshport').val();
		var namenode = $("#namenode").prop("checked");
		var datanode = $("#datanode").prop("checked");
		var secondarynamenode = $("#secondarynamenode").prop("checked");
		var resourcemanager = $("#resourcemanager").prop("checked");
		var nodemanager = $("#nodemanager").prop("checked");
		
		if((hostname == "") || (ip == "") 
				|| (os == "") || (sshuser == "")
				|| (sshpass == "") || (sshport == "")) {
			alert("相关信息不能为空！");
		}else if ((namenode || datanode || secondarynamenode || resourcemanager || nodemanager)== false){
			alert("您必须选择一个节点角色");
		}else {
			var data = {
					'hostname': hostname,
					'ip': ip,
					'os': os,
					'sshuser': sshuser,
					'sshpass': sshpass,
					'sshport': sshport,
					'namenode': namenode,
					'datanode': datanode,
					'secondarynamenode': secondarynamenode,
					'resourcemanager': resourcemanager,
					'nodemanager': nodemanager
			};
			$.ajax({
				  type: 'POST',
				  url: baseUrl + "/api/clustermanager/hadoop/addnode",
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
	
	
	
	$('.submitUpdateHadoopNode').click(function(){
		var id = $(this).attr('update');
		var hostname = $('#hostname_'+id).val();
		var ip = $('#ip_'+id).val();
		var os = $('#os_'+id).val();
		var sshuser = $('#sshuser_'+id).val();
		var sshpass = $('#sshpass_'+id).val();
		var sshport = $('#sshport_'+id).val();
		var namenode = $("#namenode_"+id).prop("checked");
		var datanode = $("#datanode_"+id).prop("checked");
		var secondarynamenode = $("#secondarynamenode_"+id).prop("checked");
		var resourcemanager = $("#resourcemanager_"+id).prop("checked");
		var nodemanager = $("#nodemanager_"+id).prop("checked");
		
		if((hostname == "") || (ip == "") 
				|| (os == "") || (sshuser == "")
				|| (sshpass == "") || (sshport == "")) {
			alert("相关信息不能为空！");
		}else if ((namenode || datanode || secondarynamenode || resourcemanager || nodemanager)== false){
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
					'namenode': namenode,
					'datanode': datanode,
					'secondarynamenode': secondarynamenode,
					'resourcemanager': resourcemanager,
					'nodemanager': nodemanager
			};
			$.ajax({
				  type: 'POST',
				  url: baseUrl + "/api/clustermanager/hadoop/updatenode",
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
		}
	});
	
	$('.submitDeleteHadoopNode').click(function(){
		var id = $(this).attr('del');
		$.ajax({
			  type: 'GET',
			  url: baseUrl + "/api/clustermanager/hadoop/delnode?id="+id,
			  success: function(data, status){
				  if(data.code == 100000) {
					  alert('删除成功');
					  window.location.reload();
				  }else {
					  alert('删除成功');
				  }
			  },
			  dataType: 'json'
		});
	});
});