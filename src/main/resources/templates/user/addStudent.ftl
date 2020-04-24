<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增学生页</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function addStudent() {
            var name = $("[name='name']").val();
            $.get("/selectByUserName", {userName:name}, function (data) {
                if(data.flag){
                    alert("存在该用户");
                } else {
                    $.post("/addStudent", $("form").serialize(), function(obj){
                        if(obj.flag){
                            alert("新增成功");
                            location.href = "/studentManagement";
                        }else{
                            alert(obj.message);
                        }
                    },"json");
                }
            }, "json");
        }

        function getBackStudentManagement() {
            location.href = "/studentManagement";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>作业批改系统 学生-新增(<span style="color: red">*</span>为必填项)</h1>
    </div>
    <div class="body">
        <form action="" method="post">
            <table>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="name" placeholder="请输入用户名..."><span style="color: red">*</span></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password" placeholder="请输入密码..."><span style="color: red">*</span></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" name="phoneNumber" placeholder="请输入手机号..."><span style="color: red">*</span></td>
                </tr>
                <tr>
                    <td>电子邮箱</td>
                    <td><input type="text" name="eMail" placeholder="请输入邮箱..."><span style="color: red">*</span></td>
                </tr>
                <tr>
                    <td>学号</td>
                    <td><input type="text" name="studentNumber" placeholder="请输入学号..."><span style="color: red">*</span></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="返回" onclick="getBackStudentManagement()">
                        <input type="button" value="新增" onclick="addStudent()">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>