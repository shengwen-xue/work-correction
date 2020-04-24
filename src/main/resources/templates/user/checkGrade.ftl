<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看成绩</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function checkDetails(id) {
            location.href = "checkDetails?id=" + id;
        }

        function getBackMain() {
            location.href = "/selectAllStudent";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>查看成绩</h1>
    </div>
    <div class="body">
        <input type="button" value="返回到主页面" onclick="getBackMain()">
        <table>
            <tr>
                <td>编号</td>
                <td>上传者</td>
                <td>文件名</td>
                <td>下载Url</td>
                <td>上传时间</td>
                <td>分数</td>
                <td>评语</td>
                <td>评论时间</td>
                <td>操作</td>
            </tr>
            <#list tbMarks as mark>
                <tr>
                    <td>${mark.id!''}</td>
                    <td>${mark.uploader!''}</td>
                    <td>${mark.fileName!''}</td>
                    <td>${mark.checkUrl!''}</td>
                    <td>${mark.uploadTime?date}</td>
                    <td>${mark.grade!'暂无'}</td>
                    <td>
                        <#if mark.remark?? && mark.remark?length gte 10>
                            ${mark.remark?substring(0,8)}...
                        <#elseif mark.remark?? && mark.remark?length lte 10>
                            ${mark.remark}
                        <#else >
                            ${mark.remark!'暂无'}
                        </#if>
                    </td>
                    <td>
                        <#if mark.markTime??>
                            ${mark.markTime?date}
                        <#else>
                            ${mark.markTime!'暂无'}
                        </#if>
                    <td>
                        <a href="${mark.checkUrl!''}" target="_blank">下载</a>
                        <input type="button" value="查看详情" onclick="checkDetails(${mark.id!''})">
                    </td>
                </tr>
            </#list>
        </table>
    </div>
</div>
</body>
</html>