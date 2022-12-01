function login() {
	window.location.href = "login.html";
}

function register() {
	window.location.href = "register.html";
}

function insert() {
	var i = 1;
	i++;
	var tab = document.getElementById("BondTable");
	var bondName = document.getElementById("bondName").value;
	var nation = document.getElementById("nation").value;
	var interest = document.getElementById("interest").value;
	var price = document.getElementById("price").value;
	var time = document.getElementById("time").value;


	if (bondName == "") {
		swal("债券名称不能为空")
	} else if (nation == "") {
		swal("国家不能为空")
	} else if (interest == "") {
		swal("到期本息不能为空")
	} else if (interest == "") {
		swal("发行价格不能为空")
	} else if (time == "") {
		swal("偿还期限不能为空")
	} else {
		$.ajax({
			url: '/login/insertStateBonds',
			data: JSON.stringify({
				bondName: bondName,
				nation: nation,
				interest: interest,
				price: price,
				time: time
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
					swal("成功插入");
					var r = tab.insertRow();
					var c1 = r.insertCell(0);
					var c2 = r.insertCell(1);
					var c3 = r.insertCell(2);
					var c4 = r.insertCell(3);
					var c5 = r.insertCell(4);
					c1.innerHTML = bondName;
					c2.innerHTML = nation;
					c3.innerHTML = interest;
					c4.innerHTML = price;
					c5.innerHTML = time;
				} else if (data.msg == "已存在") {
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

function search() {
	var tab = document.getElementById("BondTable");
	// var dnmd=document.getElementById("dnmd");
	var searchBondName = document.getElementById("searchBondName").value;
	var bondName = document.getElementById("bondName").value;
	var nation = document.getElementById("nation").value;
	var interest = document.getElementById("interest").value;
	var price = document.getElementById("price").value;
	var time = document.getElementById("time").value;
	var StateBonds = 1;
	for (var i = 1; i < tab.rows.length;) {
		tab.deleteRow(i);
	}
	// var a = "dnmd";
	if (searchBondName == "") {
		$.ajax({
			url: '/login/searchAll',
			data: {
				form: StateBonds
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


					for (i = 0; i < data.data.length; i++) {
						bondName = data.data[i].bondName;
						nation = data.data[i].nation;
						interest = data.data[i].interest;
						price = data.data[i].price;
						time = data.data[i].time;


						var r = tab.insertRow();
						var c1 = r.insertCell(0);
						var c2 = r.insertCell(1);
						var c3 = r.insertCell(2);
						var c4 = r.insertCell(3);
						var c5 = r.insertCell(4);
						c1.innerHTML = bondName;
						c2.innerHTML = nation;
						c3.innerHTML = interest;
						c4.innerHTML = price;
						c5.innerHTML = time;
					}

				}

			}


		})


	} else {
		$.ajax({
			url: '/login/searchOne',
			data: {
				form: StateBonds,
				name: searchBondName
			},
			type: 'GET',
			dataType: 'json',
			headers: {
				"Content-Type": "application/json"
			},
			success: function(data) {
				console.log(data);
				if (data.code === 200) {

					bondName = data.data.bondName;
					nation = data.data.nation;
					interest = data.data.interest;
					price = data.data.price;
					time = data.data.time;

					var r = tab.insertRow();
					var c1 = r.insertCell(0);
					var c2 = r.insertCell(1);
					var c3 = r.insertCell(2);
					var c4 = r.insertCell(3);
					var c5 = r.insertCell(4);
					c1.innerHTML = bondName;
					c2.innerHTML = nation;
					c3.innerHTML = interest;
					c4.innerHTML = price;
					c5.innerHTML = time;
				}

			}


		})
	}
}
