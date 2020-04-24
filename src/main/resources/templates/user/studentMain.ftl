<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生平台</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function logout() {
            $.get("/logout", function (data) {
                console.info(data)
                if(data.flag){
                    alert(data.data);
                    location.href = "/toLogin";
                } else {
                    alert(data.message);
                }
            }, "json");
        }

        function personInfoManagement() {
            var name = "<@shiro.principal property='name'/>";
            location.href = "/findUserByuserName?userName=" + name;
        }

        function toUploadAnswer() {
            var name = "<@shiro.principal property='name'/>";
            location.href = "/toUploadAnswer?name=" + name;
        }

        function checkGrade() {
            location.href = "/selectAllGrade";
        }

        function toForum() {
            location.href = "/toForum";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>作业批改系统-学生平台</h1>
    </div>
    <div class="body">
        <div class="right">
            <@shiro.user>
                欢迎<@shiro.principal property="name"/> 登录 <input type="button" value="退出" onclick="logout()">
                <input type="button" value="个人信息管理" onclick="personInfoManagement()">
            </@shiro.user>
        </div>
        <input type="button" value="上传答案" onclick="toUploadAnswer()">
        <input type="button" value="查看成绩" onclick="checkGrade()">
        <input type="button" value="论坛" onclick="toForum()">
        <form action="/selectAllWork" method="get">
            <table>
                <tr>
                    <td>编号</td>
                    <td>作业名称</td>
                    <td>下载Url</td>
                    <td>开始时间</td>
                    <td>结束时间</td>
                    <td>操作</td>
                </tr>
                <#list list as work>
                    <tr>
                        <td>${work.id!''}</td>
                        <td>${work.workName!''}</td>
                        <td>${work.checkUrl!''}</td>
                        <td>${work.startDate?date}</td>
                        <td>${work.endDate?date}</td>
                        <td>
                            <a href="${work.checkUrl!''}" target="_blank">下载</a>
                        </td>
                    </tr>
                </#list>
            </table>
        </form>
    </div>
</div>
</body>
</html>