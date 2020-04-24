<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户分配权限</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <style>
        /*网页的全局样式 解决兼容问题*/
        body,div,p,ul,ol,li,dl,dt,dd,h1,h2,h3,h4,h5,h6,table,tr,td,form,input,select,textarea,span,img,a,em,strong,*{ margin:0; padding:0;}
        body{ font-family:"圆幼"; font-size:12px; color:#000000;}
        ul,ol,li{ list-style:none;}
        h1,h2,h3,h4,h5,h6{ font-size:14px;}
        input,select,textarea{ vertical-align:middle;}
        img{ border:none; vertical-align:middle;}
        a{ text-decoration:none; color:#333333;}
        a:hover{ color:#009999;}
        .clear{ clear:both; height:0px; width:0px; overflow:hidden;}

        .box {
            width: 100%;
            height: 714px;
            background-color: #f7f7f7;
        }
        .right {
            width: 47px;
            height: 35px;
            float: right;
            padding: 7px;
            border-radius: 5px;
            text-align: center;
            background-color: #4cc0c1;
            border: 1px solid #4cc0c1;
            color: #fffffb;
            position: absolute;
            margin-top: -46px;
            margin-left: 1126px;
        }
        .head {
            width: 100%;
            height: 51px;
            background-color: #25313e;
        }
        .head h1 {
            padding: 17px 0 17px 16px;
            font-weight: 900;
            color: #adbece;
        }
        .body {
            width: 1000px;
            height: 632px;
            border: 1px solid e8e8e8;
            text-align: center;
            margin: 14px auto;
        }
        .body-from {
            width: 1000px;
            height: 591px;
            background-color: #ffffff;
        }
        .body-from h1 {
            float: left;
            color: #222;
            margin: 10px 430px 10px 10px;
        }
        .ul {
            margin-top: 41px;
        }
        .ul table {
            width: 500px;
        }
        .ul table tr td {
            width: 110px;
            height: 50px;
        }
        .ul table tr {
            background: #f1f1f1;
            -webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
            box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
        }
        .body-from-left {
            width: 500px;
            height: 591px;
            float: left;
            border-right: 1px solid #ccc;
        }
        .body-from-right {
            width: 499px;
            height: 591px;
            float: right;
        }
        .assign {
            width: 220px;
            height: 35px;
            font-size: 12px;
            color: #fffffb;
            background-color: #4cc0c1;
            margin: 20px;
            border-radius: 10px;
            border: 1px solid #4cc0c1;
        }
    </style>
    <script>
        $(function(){
            // 当页面加载完成的时候，自动调用该方法
            window.onload=function(){
                // 获得所要回显的值
                var studentCheckeds = $("#studentIdHidden").val();
                var teacherCheckeds = $("#teacherIdHidden").val();
                // 拆分为字符串数组
                var studentCheckArray = studentCheckeds.split(",");
                var teacherCheckArray = teacherCheckeds.split(",");
                // 获得所有的复选框对象
                var checkBoxAll = $("input[name='id']");
                var checkBox = $("input[name='ids']");
                for(var i=0;i<studentCheckArray.length;i++){
                    // 获取所有复选框对象的value属性，然后studentCheckArray[i]和他们匹配，如果有，则说明他应被选中
                    $.each(checkBoxAll,function(j,checkbox) {
                        // 获取复选框的value属性
                        var checkValue = $(checkbox).val();
                        if (studentCheckArray[i] == checkValue) {
                            $(checkbox).attr("checked", true);
                        }
                    })
                }
                for(var i=0;i<teacherCheckArray.length;i++){
                    // 获取所有复选框对象的value属性，然后，teacherCheckArray[i]和他们匹配，如果有，则说明他应被选中
                    $.each(checkBox,function(j,checkbox) {
                        // 获取复选框的value属性
                        var checkValue = $(checkbox).val();
                        if (teacherCheckArray[i] == checkValue) {
                            $(checkbox).attr("checked", true);
                        }
                    })
                }
            }
        });

        function cancel() {
            location.href = "/selectAllUser";
        }
        
        function assignStudent() {
            var arrayList = new Array();
            $("input[name='id']:checked").each(function() {
                arrayList.push($(this).val());
            });
            var roleId = 3;
            $.ajax({
                type: 'POST',
                url: '/updateAuthority?roleId=' + roleId,
                data: JSON.stringify(arrayList),
                dataType: 'json',
                contentType:'application/json',
                success: function (obj) {
                    if(obj.flag) {
                        alert("学生权限分配成功");
                        location.href = "/selectAllUser";
                    } else {
                        alert(obj.message);
                    }
                }
            });
        }

        function assignTeacher() {
            var arrayList = new Array();
            $("input[name='ids']:checked").each(function() {
                arrayList.push($(this).val());
            });
            var roleId = 2;
            $.ajax({
                type: 'POST',
                url: '/updateAuthority?roleId=' + roleId,
                data: JSON.stringify(arrayList),
                dataType: 'json',
                contentType:'application/json',
                success: function (obj) {
                    if(obj.flag) {
                        alert("教师权限分配成功");
                        location.href = "/selectAllUser";
                    } else {
                        alert(obj.message);
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>分配权限</h1>
        <input type="button" value="返回" class="right" onclick="cancel()">
    </div>
    <div class="body">
        <div class="body-from">
            <div class="body-from-left">
                <h1>学生权限</h1>
                <div class="ul">
                    <table>
                        <tr>
                            <td>选项</td>
                            <td>拥有的权限</td>
                            <td>权限说明</td>
                        </tr>
                        <#list permissionList as permission>
                            <tr>
                                <td><input type="checkbox" name="id" value="${permission.id!''}"></td>
                                <td>${permission.permissionName!''}</td>
                                <td>${permission.permissionSpecification!''}</td>
                            </tr>
                        </#list>
                    </table>
                    <input type="hidden" value="${studentId!''}" id="studentIdHidden">
                    <input type="button" value="分配学生权限" class="assign" onclick="assignStudent()">
                </div>
            </div>
            <div class="body-from-right">
                <h1>教师权限</h1>
                <div class="ul">
                    <table>
                        <tr>
                            <td>选项</td>
                            <td>拥有的权限</td>
                            <td>权限说明</td>
                        </tr>
                        <#list permissionList as permission>
                            <tr>
                                <td><input type="checkbox" name="ids" value="${permission.id!''}"></td>
                                <td>${permission.permissionName!''}</td>
                                <td>${permission.permissionSpecification!''}</td>
                            </tr>
                        </#list>
                    </table>
                    <input type="hidden" value="${teacherId!''}" id="teacherIdHidden">
                    <input type="button" value="分配教师权限" class="assign" onclick="assignTeacher()">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>