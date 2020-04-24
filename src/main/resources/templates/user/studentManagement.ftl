<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function checkeds() {
            $("input[type='checkbox']").prop("checked",true)
        }

        function noChecked() {
            $("input[type='checkbox']").prop("checked",false)
        }

        function isChecked() {
            $("input[type='checkbox']").prop("checked",function(i,val){
                return !val;
            })
        }

        function findUserById(id) {
            location.href = "/findUser?id="+id;
        }

        function deleteUser(id) {
            $.post("/deleteUser", {id:id}, function (data) {
                if(data.flag){
                    alert("deleted " + data.message);
                    location.href = "/studentManagement";
                } else {
                    alert(data.message);
                }
            }, "json")
        }

        function getBackStudent() {
            location.href = "/selectAllUser";
        }

        function toAddStudent() {
            location.href = "/toAddStudent";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>学生管理</h1>
    </div>
    <div class="body">
        <input type="button" value="返回主页面" onclick="getBackStudent()">
        <input type="button" value="全选" onclick="checkeds()">
        <input type="button" value="反选" onclick="isChecked()">
        <input type="button" value="全不选" onclick="noChecked()">
        <table>
            <tr>
                <td>选项</td>
                <td>编号</td>
                <td>姓名</td>
                <td>创建时间</td>
                <td>操作
                    <input type="button" value="新增学生" onclick="toAddStudent()">
                </td>
            </tr>
            <#list list as user>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.createTime?date}</td>
                    <td>
                        <input type="button" value="修改" onclick="findUserById(${user.id})">
                        <input type="button" value="删除" onclick="deleteUser(${user.id})">
                    </td>
                </tr>
            </#list>
        </table>
    </div>
</div>
</body>
</html>