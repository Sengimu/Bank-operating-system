function login(){
	window.location.href = "login.html";
}
function register(){
	window.location.href = "register.html";
}
function checklogin() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	if (username == "") {
		swal("账户不能为空");
		// document.getElementById("notice").value="账户不能为空"；
	} else if (password == "") {
		swal("密码不能为空");
		// document.getElementById("notice").value="账户不能为空"；

	} else {
		$.ajax({
			url: '/login/login',
			data: {
				emailOrAccountNumber: username,
				password: password
			},
			type: 'GET',
			dataType: 'json',
			headers: {
				"Content-Type": "application/json"
			},
			success: function(data) {
				console.log(data);
				if (data.code === 200) {
					swal("成功登录");
					window.location.href = "StateBonds.html";
				} else if (data.msg == "用户名或邮箱不存在") {
					swal("用户名或邮箱不存在，登录失败");
				} else if (data.msg == "密码错误") {
					swal("密码错误，请重试");
				} else {
					swal("未知错误，请联系管理员");
				}
			}
		})
	}
}
