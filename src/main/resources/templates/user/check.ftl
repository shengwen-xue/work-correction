<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户详细信息</title>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>用户详细信息</h1>
    </div>
    <div class="body">
        <form action="/selectAllUser" method="get">
            <table>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" value="${userBO.name!''}" disabled style="width: 200px"></td>
                </tr>
                <tr>
                    <td>角色</td>
                    <td><input type="text" value="${userBO.roleName!''}" disabled style="width: 200px"></td>
                </tr>
                <tr>
                    <td>电子邮箱</td>
                    <td><input type="text" value="${userBO.EMail!''}" disabled style="width: 200px"></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" value="${userBO.phoneNumber!''}" disabled style="width: 200px"></td>
                </tr>
                <tr>
                    <td>学号</td>
                    <td><input type="text" value="${userBO.studentNumber!''}" disabled style="width: 200px"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="返回">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>