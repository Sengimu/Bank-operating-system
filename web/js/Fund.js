function login(){
	window.location.href = "login.html";
}
function register(){
	window.location.href = "register.html";
}
function insert(){
	var i=1;
	i++;
	var tab=document.getElementById("BondTable");
	var fundName = document.getElementById("fundName").value;
	var currentValue = document.getElementById("currentValue").value;
	var fundShares = document.getElementById("fundShares").value;
	var redemptionFee = document.getElementById("redemptionFee").value;
	var subscriptionAmount = document.getElementById("subscriptionAmount").value;
	var agent = document.getElementById("agent").value;
	
	if(fundName==""){
		swal("基金名称不能为空")
	}else if(currentValue==""){
		swal("当日基金净值不能为空")
	}else if(fundShares==""){
		swal("基金份额不能为空")
	}else if(redemptionFee==""){
		swal("赎回费不能为空")
	}else if(subscriptionAmount==""){
		swal("申购金额不能为空")
	}else if(agent==""){
		swal("代理人不能为空")
	}else{
		$.ajax({
			url: '/login/insertFund',
			data: JSON.stringify({
				fundName:fundName,
				currentValue:currentValue,
				fundShares:fundShares,
				redemptionFee:redemptionFee,
				subscriptionAmount:subscriptionAmount,
				agent:agent
				// emailOrAccountNumber: username,
				// password: password
			}),
			type: 'POST',
			dataType: 'json',
			headers: {
				"Content-Type": "application/json"
			},
			success: function(data) {
				console.log(data);
				if (data.code === 200) {
					var r=tab.insertRow();
					var c1=r.insertCell(0);
					var c2=r.insertCell(1);
					var c3=r.insertCell(2);
					var c4=r.insertCell(3);
					var c5=r.insertCell(4);
					var c6=r.insertCell(5);
					c1.innerHTML=fundName;
					c2.innerHTML=currentValue;
					c3.innerHTML=fundShares;	
					c4.innerHTML=redemptionFee;
					c5.innerHTML=subscriptionAmount;
					c6.innerHTML=agent;
					swal("成功插入");
				} else if(data.msg=="已存在"){
					swal("已存在该基金")
				}
				// else if (data.msg == "用户名或邮箱不存在") {
				// 	swal("用户名或邮箱不存在，登录失败");
				// } else if (data.msg == "密码错误") {
				// 	swal("密码错误，请重试");
				// } else {
				// 	swal("未知错误，请联系管理员");
				// }
			}
		})
		
	}
	
	
	
}


function search(){
	var tab=document.getElementById("BondTable");
	var fundName = document.getElementById("fundName").value;
	var searchName = document.getElementById("searchName").value;
	var currentValue = document.getElementById("currentValue").value;
	var fundShares = document.getElementById("fundShares").value;
	var redemptionFee = document.getElementById("redemptionFee").value;
	var subscriptionAmount = document.getElementById("subscriptionAmount").value;
	var agent = document.getElementById("agent").value;
	var Funds = 3;
	for(var i = 1;i<tab.rows.length;){
	            tab.deleteRow(i);
	        }
	// var a = "dnmd";
	if(searchName == ""){
	$.ajax({
		url: '/login/searchAll',
		data: {
			form: Funds
		},
		type: 'GET',
		dataType: 'json',
		headers: {
			"Content-Type": "application/json"
		},
		success: function(data) {
			console.log(data);
			if (data.code === 200) {
				// alert(data.data[0].bondName);
					
					
					for(i=0;i<data.data.length;i++){
					fundName=data.data[i].fundName;
					currentValue=data.data[i].currentValue;
					fundShares=data.data[i].fundShares;
					redemptionFee=data.data[i].redemptionFee;
					subscriptionAmount=data.data[i].subscriptionAmount;
					agent=data.data[i].agent;
					
					
					var r=tab.insertRow();
					var c1=r.insertCell(0);
					var c2=r.insertCell(1);
					var c3=r.insertCell(2);
					var c4=r.insertCell(3);
					var c5=r.insertCell(4);
					var c6=r.insertCell(5);
					c1.innerHTML=fundName;
					c2.innerHTML=currentValue;
					c3.innerHTML=fundShares;	
					c4.innerHTML=redemptionFee;
					c5.innerHTML=subscriptionAmount;
					c6.innerHTML=agent;
					}
				
			} 
			
		}
			
		
	})
	
	
	}
	else{
	$.ajax({
		url: '/login/searchOne',
		data: {
			form: Funds,
			name: searchName
		},
		type: 'GET',
		dataType: 'json',
		headers: {
			"Content-Type": "application/json"
		},
		success: function(data) {
			console.log(data);
			if (data.code === 200) {
					
					fundName=data.data.fundName;
					currentValue=data.data.currentValue;
					fundShares=data.data.fundShares;
					redemptionFee=data.data.redemptionFee;
					subscriptionAmount=data.data.subscriptionAmount;
					agent=data.data.agent;
					
					
					var r=tab.insertRow();
					var c1=r.insertCell(0);
					var c2=r.insertCell(1);
					var c3=r.insertCell(2);
					var c4=r.insertCell(3);
					var c5=r.insertCell(4);
					var c6=r.insertCell(5);
					c1.innerHTML=fundName;
					c2.innerHTML=currentValue;
					c3.innerHTML=fundShares;	
					c4.innerHTML=redemptionFee;
					c5.innerHTML=subscriptionAmount;
					c6.innerHTML=agent;
			} 
			
		}
			
		
	})
	}
}
