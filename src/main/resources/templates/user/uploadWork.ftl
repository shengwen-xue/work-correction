<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传作业</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function showOrHide(){
            var div = $("#showOrHide").get(0);
            if(div.style.display == ""){
                div.style.display = "none";
            }else{
                div.style.display = "";
            }
        }

        function getBackTeacherMain() {
            location.href = "/selectAllWork";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>教师-发布作业</h1>
    </div>
    <div class="body">
        <input type="button" value="发布作业" onclick="showOrHide()">
        <div id="showOrHide" style="display: none;border:0px solid #CCCCCC;">
            <form action="/uploadWork" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td>作业标题</td>
                        <td><input type="text" name="workName" placeholder="请输入作业标题..."></td>
                    </tr>
                    <tr>
                        <td>上传作业</td>
                        <td><input type="file" name="file"></td>
                    </tr>
                    <tr>
                        <td>开始日期</td>
                        <td><input type="date" name="startDate" required></td>
                    </tr>
                    <tr>
                        <td>截止日期</td>
                        <td><input type="date" name="endDate" required></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" value="取消" onclick="getBackTeacherMain()">
                            <input type="submit" value="发布">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>