function registerUser(){
	
	var userId = $("#userId").val();
	var password = $("#password").val();
	var realName = $("#realName").val();
	var bankNum = $("#bankNum").val();
	var userType = $("#userType").val();
	var okay = 1;
	if(userId==""){
		$("#userId_remind").html("账号不能为空");
		okay = 0;
	}
		
	if(password==""){
		$("#password_remind").html("密码不能为空");
		okay = 0;
	}
		
	if(realName==""){
		$("#realName_remind").html("姓名不能为空");
		okay = 0;
	}
		
	var re = new RegExp("[0-9]{7}");
	if(bankNum==""||!re.test(bankNum)){
		$("#bankNum_remind").html("银行账号必须为7位数字");
		okay = 0;
	}
	if(okay == 1){
		$.post('/FRS/frs/register',
				{"userId":userId, "password":password, "realName":realName, "bankNum":bankNum, "userType":userType},
				function(result){
					$('#new_user_panel').hide('fast');
					var create_result = "";
					if(result == null||result == ""){
						create_result = "new user create fail!";
						$("#create_fail").html(create_result);
						$("#create_fail").show('fast');
					}else{
						create_result = "new user create success!";
						$("#create_success").html(create_result);
						$("#create_success").show('fast');
						window.location.reload();
						var newHtml = "<table class=\"table table-striped table-bordered table-condensed\"><><tr><td>账号</td></tr><td>密码</td></table>";
						$('#new_user_info').html(newHtml);
					}
				}
		);
	}
	
}
function on_edit_click(a, isAllUser){
	var td = $(a).parent();
	var tr = td.parent();
	var td1 = tr.find("td")[0];
	var td1Html = td1.innerHTML;
	td1.innerHTML = "<input id=\"input_userId\" disabled=\"disabled\" style=\"width:120px;\" name=\"userId\" value=\""+ td1Html +"\"/>";
	
	var td2 = tr.find("td")[1];
	var td2Html = td2.innerHTML;
	td2.innerHTML = "<input id=\"input_realName\" style=\"width:120px;\" name=\"realName\" value=\""+ td2Html +"\"/>";
	
	var td3 = tr.find("td")[2];
	var td3Html = td3.innerHTML;
	td3.innerHTML = "<input id=\"input_password\" style=\"width:120px;\" name=\"password\" value=\""+ td3Html +"\"/>";
	
	var td4 = tr.find("td")[3];
	var td4Html = td4.innerHTML;
	td4.innerHTML = "<input id=\"input_bankNum\" style=\"width:120px;\" name=\"bankNum\" value=\""+ td4Html +"\"/>";
	
	var td5 = tr.find("td")[4];
	var td5Html = td5.innerHTML;
	if(isAllUser){
		td5.innerHTML = "<select id=\"input_userType\" style=\"width:120px;\" name=\"userType\"><option value =\"CommonUser\">普通用户</option><option value =\"ManagerUser\">管理员用户</option><option value=\"FinancialExaminers\">财务审核人员</option><option value=\"FinancialManager\">财务主管</option></select>";
		
	}
	td[0].innerHTML = "<a href=\"javascript:void(0)\" onclick=\"on_ok_click(this)\"><i class=\"icon-ok\"></i>&nbsp;&nbsp;ok</a>";
	
}
function is_good_userId(){
	
}

function on_ok_click(a){
	var userId = $("#input_userId").val();
	var password = $("#input_password").val();
	var realName = $("#input_realName").val();
	var bankNum = $("#input_bankNum").val();
	var userType = $("#input_userType").val();
	var okay = 1;
	if(userId==""){
		$("#userId_remind").html("账号不能为空");
		okay = 0;
	}
		
	if(password==""){
		$("#password_remind").html("密码不能为空");
		okay = 0;
	}
		
	if(realName==""){
		$("#realName_remind").html("姓名不能为空");
		okay = 0;
	}
		
	var re = new RegExp("[0-9]{7}");
	if(bankNum==""||!re.test(bankNum)){
		$("#bankNum_remind").html("必须为7位数字");
		okay = 0;
	}
	if(okay == 1){
		$.post('/FRS/frs/updateUser',
				{"userId":userId, "password":password, "realName":realName, "bankNum":bankNum, "userType":userType},
				function(result){
					$('#new_user_panel').hide('fast');
					var update_result = "";
					if(result == null||result == ""){
						update_result = "user update fail!";
						$("#update_fail").html(update_result);
						$("#update_fail").show('fast');
					}else{
						update_result = "user update success!";
						$("#update_success").html(update_result);
						$("#update_success").show('fast');
						var td = $(a).parent();
						var tr = td.parent();
						var td1 = tr.find("td")[0];
						var td1Html = td1.innerHTML;
						td1.innerHTML = result.userId;
						
						var td2 = tr.find("td")[1];
						var td2Html = td2.innerHTML;
						td2.innerHTML = result.realName;
						
						var td3 = tr.find("td")[2];
						var td3Html = td3.innerHTML;
						td3.innerHTML = result.password;
						
						var td4 = tr.find("td")[3];
						var td4Html = td4.innerHTML;
						td4.innerHTML = result.bankNum;
						
						var td5 = tr.find("td")[4];
						td5.innerHTML = result.userType;
						
						td[0].innerHTML = "<a href=\"javascript:void(0)\" onclick=\"on_edit_click(this)\"><i class=\"icon-pencil\"></i>&nbsp;&nbsp;edit</a>";
						
					}
				}
		);
	}else{
		alert("fail");
	}
}


function hide_me(t){
	$(t).hide('fast');
}

function showUserList(t){
	$("#chooseUser").slideToggle("slow");
	if(t.innerHTML=="选择用户")
		t.innerHTML = "收起";
	else
		t.innerHTML = "选择用户";
}


function updateSelectedUser(){
	var newUserList = "";

	$(":checked").each(
		function() {
			var t = $(this);
			newUserList += "<tr><td>"+$(this).val()+"</td><td>"+$(this).attr('title')+"</td><td></td></tr>";
      });
	$("#newUserList").html(newUserList);
}

function on_edit_task_click(a, isAllUser){
	var td = $(a).parent();
	var tr = td.parent();
	
	var td3 = tr.find("td")[2];
	var td3Html = td3.innerHTML;
	td3.innerHTML = "<textarea id=\"input_task\" cols=\"200\" rows=\"1\" name=\"task\" value=\""+ td3Html +"\"/>";
	
	td[0].innerHTML = "<a href=\"javascript:void(0)\" onclick=\"on_ok_task_click(this)\"><i class=\"icon-ok\"></i>&nbsp;&nbsp;ok</a>";
	
}

function on_ok_task_click(a){
	var task = $("#input_task").val();
	var td = $(a).parent();
	var tr = td.parent();
	var userId = tr.find("td")[0].innerHTML;
	var projectId = $("#hiddenProjecId").val();
	
		$.post('/FRS/frs/updateTask',
				{"userId":userId, "projectId":projectId, "task":task},
				function(result){
					var update_result = "";
					if(result == null||result == ""){
						update_result = "user update fail!";
						$("#update_fail").html(update_result);
						$("#update_fail").show('fast');
					}else{
						update_result = "user update success!";
						$("#update_success").html(update_result);
						$("#update_success").show('fast');
						var td = $(a).parent();
						var tr = td.parent();
						
						var td3 = tr.find("td")[2];
						td3.innerHTML = result;
						
						td[0].innerHTML = "<a href=\"javascript:void(0)\" onclick=\"on_edit_click(this)\"><i class=\"icon-pencil\"></i>&nbsp;&nbsp;edit</a>";
						
					}
				}
		);
}
