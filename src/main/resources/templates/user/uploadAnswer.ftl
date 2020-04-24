<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传答案</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
    <script>
        function getBackStudengtMain() {
            location.href = "/selectAllStudent";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>(注：请将上传的文档名称修改成格式为：xxx单元试题答案或xxx答案 方便老师下载批阅)</h1>
    </div>
    <div class="body">
        <form action="/uploadFile" method="post" enctype="multipart/form-data">
            <input type="hidden" name="userName" value="${userName!''}">
            <table>
                <tr>
                    <td>文件</td>
                    <td><input type="file" name="file_name"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="取消" onclick="getBackStudengtMain()">
                        <input type="submit" value="上传">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>