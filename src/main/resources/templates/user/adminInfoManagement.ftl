<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息管理</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function cancel() {
            location.href = "/selectAllUser";
        }

        function updateUser() {
            $.post("/adminInfoUpdate", $("form").serialize(), function(obj){
                if(obj.flag){
                    alert("修改成功");
                    location.href = "/toLogin";
                }else{
                    alert(obj.message);
                }
            },"json");
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>个人信息管理</h1>
    </div>
    <div class="body">
        <form action="" method="post">
            <table>
                <input type="hidden" name="id" value="${userUpdateBO.id!''}">
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="name" value="${userUpdateBO.name!''}"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password" value="${userUpdateBO.password!''}"></td>
                </tr>
                <tr>
                    <td>电子邮箱</td>
                    <td><input type="text" name="eMail" value="${userUpdateBO.EMail!''}"></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" name="phoneNumber" value="${userUpdateBO.phoneNumber!''}"></td>
                </tr>
                <tr>
                    <td>学号</td>
                    <td><input type="text" name="studentNumber" value="${userUpdateBO.studentNumber!''}"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="取消" onclick="cancel()">
                        <input type="button" value="修改" onclick="updateUser()">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>