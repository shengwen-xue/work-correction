<!DOCTYPE html>
<html lang="en">
<head>
    <title>提问</title>
    <meta charset="UTF-8">
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
            height: 100%;
            background-color: #f7f7f7;
        }
        .head {
            width: 100%;
            height: 40px;
            border-bottom: 1px solid #f9f9f9;
            background-color: #fff;
        }
        .img {
            width: 1000px;
            height: 40px;
            margin: 0 auto;
        }
        .img img {
            width: 137px;
            height: 40px;
        }

        .body {
            width: 100%;
            height: 850px;
        }
        .pro {
            width: 1000px;
            height: 30px;
            font-size: 14px;
            font-weight: 800;
            margin: 0 auto;
            margin-top: 10px;
            margin-bottom: 10px;
            background-color: #fff;
        }
        .pro input {
            margin: 9px;
        }
        .boot {
            width: 1000px;
            height: 120px;
            margin: 0 auto;
        }
        .boot textarea {
            width: 992px;
            min-width: 992px;
            max-width: 992px;
            border-radius: 4px;
            padding-left: 8px;
            padding-top: 8px;
            height: 76px;
            min-height: 76px;
            max-height: 120px;
            overflow-y: hidden;
            margin-top: 0px;
            margin-bottom: 0px;
        }
        .forumButton {
            width: 100px;
            height: 33px;
            position: absolute;
            margin-left: 902px;
            margin-top: 5px;
            color: #fff;
            font-size: 14px;
            border-radius: 4px;
            border: 1px solid #ca0c16;
            text-align: center;
            background-color: #ca0c16;
        }
    </style>
    <script>
        function comment() {
            var problemTitle = $("#text").val();
            var name = $("#name").val();
            var arrayList = new Array();
            $("input:checkbox[name='ids']:checked").each(function () {
                arrayList.push($(this).val());
            });
            if(arrayList.length > 0) {
                $.post("/saveProblem", {problemTitle:problemTitle, name:name, arrayList:arrayList}, function (obj) {
                    if(obj.flag) {
                        alert("发布成功!");
                        location.href = "/toForum";
                    } else {
                        alert(obj.message);
                    }

                }, "json")
            } else {
                alert("请至少选择一种您要发布的标签！");
            }
        }
    </script>
</head>
<body>
    <div class="box">
        <div class="head">
            <div class="img">
                <img src="https://my-images-1301935246.cos.ap-shanghai.myqcloud.com/uugai.com_1587522616355.png">
            </div>
        </div>
        <div class="body">
            <div class="pro">
                问题所属的标签栏
                <#if tbLabels?? && (tbLabels?size > 0)>
                    <#list tbLabels as label>
                        <input type="checkbox" name="ids" value="${label.id!''}">${label.labelName!''}
                    </#list>
                <#else>
                    <span>暂无数据哦^_^</span>
                </#if>
            </div>
            <div class="boot">
                <input type="hidden" id="name" value="${name!''}" />
                <textarea placeholder="发布您的问题..." id="text"></textarea>
                <input type="button" value="发布问题" class="forumButton" onclick="comment()" />
            </div>
        </div>
    </div>  
</body>
</html>