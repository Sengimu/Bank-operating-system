function login(){
	window.location.href = "login.html";
}
function register(){
	window.location.href = "register.html";
}
function checkregister() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var checkpassword = document.getElementById("checkpassword").value;
	var email = document.getElementById("email").value;
	// var notice = document.getElementById("notice").value;
	var num = /^\w+@[a-z0-9]+\.[a-z]+$/i;
	if (username == "") {
		swal("账户不能为空");
		// document.getElementById("notice").value="账户不能为空"；
	} else if (email == "") {
		swal("邮箱不能为空")
	} else if (password == "") {
		swal("密码不能为空")
	} else if (checkpassword == "") {
		swal("请输入确认密码")
	} else if (!num.test(email)) {
		swal("邮箱格式错误")
		document.getElementById("email").value = "";
	} else if (password != checkpassword) {
		swal("两次密码输入不一样，请重新输入");
		document.getElementById("password").value = "";
		document.getElementById("checkpassword").value = "";

	} else {
		$.ajax({
			url: '/login/register',
			data: JSON.stringify({
				accountNumber: username,
				email: email,
				password: password
			}),
			/*data: {
				form: $('#name').val(),
				name: $('#zone').val()
			},*/
			type: 'POST',
			dataType: 'json',
			headers: {
				"Content-Type": "application/json"
			},
			success: function(data) {
				console.log(data);
				if (data.code === 200) {
					swal("注册成功");
					window.location.href = "login.html";
				} else if(data.msg == "用户名已存在"){
					swal("账户名已被注册");
				}else if(data.msg == "邮箱已存在"){
					swal("邮箱已被注册");
				}
				else if (data.code == -1) {
					swal("失败");
				} else {
					swal("未知错误，请联系管理员");
				}
			}
		})
	}
}
