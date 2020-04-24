<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看详情</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function cancel() {
            location.href = "/reviewHomework";
        }

        function reviewWork() {
            var id = $("[name='id']").val();
            var grade = $("[name='grade']").val();
            var remark = $("[name='remark']").val();
            var markTime = $("[name='markTime']").val();
            $.post("/reviewWork", {id:id,grade:grade,remark:remark,markTime:markTime}, function (data) {
                console.info(data);
                if(data.flag){
                    alert("批阅成功");
                    location.href = "/reviewHomework";
                }else{
                    alert(data.message);
                }
            }, "json");
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>评阅详情</h1>
    </div>
    <div class="body">
        <form action="" method="post">
            <table>
                <input type="hidden" name="id" value="${tbMark.id!''}">
                <tr>
                    <td>上传者</td>
                    <td><input type="text" disabled name="uploader" value="${tbMark.uploader!''}"></td>
                </tr>
                <tr>
                    <td>文件名</td>
                    <td><input type="text" disabled name="fileName" value="${tbMark.fileName!''}"></td>
                </tr>
                <tr>
                    <td>下载路径</td>
                    <td>
                        <input type="text" name="checkUrl" value="${tbMark.checkUrl!''}" style="width: 300px">
                        <a href="${tbMark.checkUrl!''}" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <td>上传时间</td>
                    <td><input type="text" name="uploadTime" value="${tbMark.uploadTime?date}"></td>
                </tr>
                <tr>
                    <td>分数</td>
                    <td><input type="text" name="grade" required value="${tbMark.grade!'暂无'}"></td>
                </tr>
                <tr>
                    <td>评语</td>
                    <td><textarea name="remark" rows="2" required cols="20">${tbMark.remark!'暂无'}</textarea></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="取消" onclick="cancel()">
                        <input type="button" value="批阅" onclick="reviewWork()">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>