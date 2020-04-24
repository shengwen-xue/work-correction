    <!-- 注册页面Js -->
    // timer变量，控制时间
    var InterValObj;
    // 间隔函数，1秒执行
    var count = 60;
    // 当前剩余秒数
    var curCount;

    function sendMessage() {
        curCount = count;
        // 设置button效果，开始计时
        // 设置按钮为禁用状态
        document.getElementById("codeId").setAttribute("disabled", true);
        // 更改按钮文字
        document.getElementById("codeId").value = "请在" + curCount + "秒后重新获取";
        // 启动计时器timer处理函数，1秒执行一次
        InterValObj = window.setInterval(setRemainTime, 1000);
        // 向后台发送处理数据
        var mobileNumber = $("[name='mobileNumber']").val();
        if (mobileNumber == null || mobileNumber == "") {
            alert("手机号码不能为空");
            return;
        } else {
            $.get("/selectMobileNumber", {mobileNumber: mobileNumber}, function (data) {
                console.info(data);
                if (data.flag) {
                    alert("发送成功");
                    return;
                } else {
                    alert(data.message);
                }
            }, "json");
        }
    }

    // 时间处理函数
    function setRemainTime() {
        if (curCount == 0) {
            // 停止计时器
            window.clearInterval(InterValObj);
            // 移除禁用状态改为可用
            document.getElementById("codeId").removeAttribute("disabled");
            document.getElementById("codeId").value = "重新发送验证码";
        } else {
            curCount--;
            document.getElementById("codeId").value = "请在" + curCount + "秒后重新获取";
        }
    }

    function signIn() {
        var verificationCode = $("[name='verificationCode']").val();
        if (verificationCode == null || verificationCode == "") {
            alert("验证码不能为空!");
        } else {
            // 校验前端输入的验证码是否正确
            $.get("/verifyCode", {verificationCode: verificationCode}, function (obj) {
                console.info(obj);
                var consoleCode = obj.data;
                if (consoleCode == verificationCode) {
                    // 2.做注册
                    var username = $("[name='username']").val();
                    var password = $("[name='password']").val();
                    var mobileNumber = $("[name='mobileNumber']").val();
                    $.post("/saveUser", {
                        userName: username,
                        password: password,
                        mobileNumber: mobileNumber
                    }, function (succ) {
                        if (succ.flag) {
                            alert("注册成功！即将跳转到登录页面");
                            location.href = "/toLogin";
                        } else {
                            alert(succ.message + ": 该用户已存在");
                        }
                    }, "json")
                } else {
                    alert(obj.message);
                }
            }, "json");
        }
    }

    function redirect() {
        location.href = "/toLogin";
    }
