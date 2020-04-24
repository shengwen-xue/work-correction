    <!-- 登录页面Js -->
    // 登录
    function toLogin() {
        var username = $("[name='username']").val();
        var password = $("[name='password']").val();
        $.post("/userLogin", {userName: username, password: password}, function (successData) {
            if (successData.flag) {
                var name = successData.data;
                $.get("selectRoleByUserName", {userName: name}, function (obj) {
                    var role_name = obj.data.roleName;
                    alert("登录成功! 欢迎 " + role_name + " - " + name);
                    if (role_name == "管理员") {
                        location.href = "/selectAllUser";
                    }
                    if (role_name == "教师") {
                        location.href = "/selectAllWork";
                    }
                    if (role_name == "学生") {
                        location.href = "/selectAllStudent";
                    }
                }, "json");
            } else {
                alert(successData.message);
            }
        }, "json");

    }
    function toRegister() {
        var str = confirm("该注册只能注册为学生身份，若注册成教师身份请联系系统管理员！");
        if (str) {
            location.href = "toRegister";
        } else {
            location.href = "toLogin";
        }

    }
