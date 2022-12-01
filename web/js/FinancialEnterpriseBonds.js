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
	var bondName = document.getElementById("bondName").value;
	var enterprise = document.getElementById("enterprise").value;
	var interest = document.getElementById("interest").value;
	var price = document.getElementById("price").value;
	var time = document.getElementById("time").value;
	
	if(bondName==""){
		swal("债券名称不能为空")
	}else if(enterprise==""){
		swal("企业名称不能为空")
	}else if(interest==""){
		swal("到期本息不能为空")
	}else if(interest==""){
		swal("发行价格不能为空")
	}else if(time==""){
		swal("偿还期限不能为空")
	}else{
		$.ajax({
			url: 'http://42.192.155.106:22987/login/insertFinancialEnterpriseBonds',
			data: JSON.stringify({
				bondName:bondName,
				enterprise:enterprise,
				interest:interest,
				price:price,
				time:time
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
					c1.innerHTML=bondName;
					c2.innerHTML=enterprise;
					c3.innerHTML=interest;	
					c4.innerHTML=price;
					c5.innerHTML=time;
					swal("成功插入");
				} else if(data.msg=="已存在"){
					swal("已存在该债券")
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
	// var dnmd=document.getElementById("dnmd");
	var bondName = document.getElementById("bondName").value;
	var searchName = document.getElementById("searchName").value;
	var enterprise = document.getElementById("enterprise").value;
	var interest = document.getElementById("interest").value;
	var price = document.getElementById("price").value;
	var time = document.getElementById("time").value;
	var FinancialEnterpriseBonds = 2;
	for(var i = 1;i<tab.rows.length;){
	            tab.deleteRow(i);
	        }
	// var a = "dnmd";
	if(searchName == ""){
	$.ajax({
		url: 'http://42.192.155.106:22987/login/searchAll',
		data: {
			form: FinancialEnterpriseBonds
		},
		type: 'GET',
		dataType: 'json',
		headers: {
			"Content-Type": "application/json"
		},
		success: function(data) {
			console.log(data);
			if (data.code === 200) {
				// swal(data.data[0].bondName);
					
					
					for(i=0;i<data.data.length;i++){
					bondName=data.data[i].bondName;
					enterprise=data.data[i].enterprise;
					interest=data.data[i].interest;
					price=data.data[i].price;
					time=data.data[i].time;
					
					
					var r=tab.insertRow();
					var c1=r.insertCell(0);
					var c2=r.insertCell(1);
					var c3=r.insertCell(2);
					var c4=r.insertCell(3);
					var c5=r.insertCell(4);
					c1.innerHTML=bondName;
					c2.innerHTML=enterprise;
					c3.innerHTML=interest;	
					c4.innerHTML=price;
					c5.innerHTML=time;
					}
				
			} 
			
		}
			
		
	})
	
	
	}
	else{
	$.ajax({
		url: 'http://42.192.155.106:22987/login/searchOne',
		data: {
			form: FinancialEnterpriseBonds,
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
					
					bondName=data.data.bondName;
					enterprise=data.data.enterprise;
					interest=data.data.interest;
					price=data.data.price;
					time=data.data.time;
					
					var r=tab.insertRow();
					var c1=r.insertCell(0);
					var c2=r.insertCell(1);
					var c3=r.insertCell(2);
					var c4=r.insertCell(3);
					var c5=r.insertCell(4);
					c1.innerHTML=bondName;
					c2.innerHTML=enterprise;
					c3.innerHTML=interest;	
					c4.innerHTML=price;
					c5.innerHTML=time;
			} 
			
		}
			
		
	})
	}
}
