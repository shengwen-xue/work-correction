<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员平台</title>
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

        function checking(id) {
            location.href = "/checking?id="+id;
        }

        function teacherManagement() {
            location.href = "/teacherManagement";
        }

        function studentManagement() {
            location.href = "/studentManagement";
        }
        
        function logout() {
            $.get("/logout", function (data) {
                if(data.flag){
                    alert(data.data);
                    location.href = "/toLogin";
                } else {
                    alert(data.message);
                }
            }, "json");
        }
        
        function deleteAll() {
            var arrayList = new Array();
            $("input:checkbox[name='ids']:checked").each(function () {
                arrayList.push($(this).val());
            });
            if(arrayList.length > 0) {
                var msg = "确认删除吗?";
                if(confirm(msg)){
                    // 对话框确认执行的操作
                    $.ajax({
                        type: 'POST',
                        url: '/deleteAll',
                        data: JSON.stringify(arrayList),
                        dataType: 'json',
                        contentType:'application/json',
                        success: function (obj) {
                            if(obj.flag) {
                                alert("删除成功");
                                location.href = "/selectAllUser";
                            } else {
                                alert(obj.message);
                            }
                        }
                    });
                } else {
                    location.href = "/selectAllUser";
                }
            } else {
                alert("请至少选择一项！");
            }
        }

        function adminInfoManagement() {
            var name = "<@shiro.principal property='name'/>";
            location.href = "/selectUser?userName=" + name;
        }

        function toAssignAuthority() {
            location.href = "/assignAuthority";
        }

        function toForum() {
            location.href = "/toForum";
        }
    </script>
</head>
<body>
<div class="box">
        <div class="head">
            <h1>作业批改系统-管理员平台</h1>
        </div>
        <div class="body">
        <div class="right">
            <@shiro.user>
                欢迎<@shiro.principal property="name"/> 登录 <input type="button" value="退出" onclick="logout()">
                <input type="button" value="个人信息管理" onclick="adminInfoManagement()">
            </@shiro.user>
        </div>
        <form class="from" action="selectAllUser" method="get">
            用户名 <input type="text" name="name" value="${name!''}">
            <input type="submit" value="查询">
        </form>
        <input type="button" value="全选" onclick="checkeds()">
        <input type="button" value="反选" onclick="isChecked()">
        <input type="button" value="全不选" onclick="noChecked()">
        <input type="button" value="批量删除" onclick="deleteAll()">
        <input type="button" value="分配权限" onclick="toAssignAuthority()">
        <input type="button" value="论坛" onclick="toForum()">
        <table>
            <tr>
                <td>选项</td>
                <td>编号</td>
                <td>姓名</td>
                <td>角色</td>
                <td>电子邮箱</td>
                <td>手机号</td>
                <td>学号</td>
                <td>操作
                    <input type="button" value="教师管理" class="ui-button" onclick="teacherManagement()">
                    <input type="button" value="学生管理" onclick="studentManagement()">
                </td>
            </tr>
            <#list userList.list as user>
                <tr>
                    <td><input type="checkbox" name="ids" value="${user.id!''}"></td>
                    <td>${user.id!''}</td>
                    <td>${user.name!''}</td>
                    <td>${user.role_name!''}</td>
                    <td>${user.e_mail!''}</td>
                    <td>${user.phone_number!''}</td>
                    <td>${user.student_number!''}</td>
                    <td>
                        <input type="button" value="查看" onclick="checking(${user.id})">

                    </td>
                </tr>
            </#list>
            <tr>
                <td colspan="11">
                    <#if userList.isFirstPage==false>
                        <a href="./selectAllUser">首页</a>
                        <a href="./selectAllUser?pageNum=${userList.prePage}">上一页</a>
                    </#if>
                    <#list userList.navigatepageNums as element>
                        <#if element==userList.pageNum>
                            <a href="./selectAllUser?pageNum=${element}">${element}</a>
                        </#if>
                        <#if element!=userList.pageNum>
                            <a href="./selectAllUser?pageNum=${element}">${element}</a>
                        </#if>
                    </#list>

                    <#if userList.isLastPage==false>
                        <a href="./selectAllUser?pageNum=${userList.nextPage}">下一页</a>
                        <a href="./selectAllUser?pageNum=${userList.pages}">尾页</a>
                    </#if>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>