<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看详情</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function getBack() {
            location.href = "/selectAllGrade";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>评阅详情</h1>
    </div>
    <div class="body">
        <input type="hidden" disabled value="${mark.id!''}"><br/>
        上传者：<input type="text" disabled value="${mark.uploader!''}"><br/>
        文件名：<input type="text" disabled value="${mark.fileName!''}"><br/>
        下载路径：<textarea rows="2" disabled cols="20">${mark.checkUrl!''}</textarea><br/>
        上传时间：<input type="text" disabled value="${mark.uploadTime?date}"><br/>
        分数：<input type="text" disabled value="${mark.grade!'暂无'}"><br/>
        评语：<textarea rows="2" disabled cols="20">${mark.remark!'暂无'}</textarea><br/>
        评论时间：
        <#if mark.markTime??>
            <input type="text" disabled value="${mark.markTime?date}"><br/>
        <#else>
            <input type="text" disabled value="${mark.markTime!'暂无'}"><br/>
        </#if>
        <input type="button" value="返回" onclick="getBack()">
    </div>
</div>
</body>
</html>