<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <meta charset="utf-8">
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
        .questions {
            width: 70px;
            height: 27px;
            border: 1px solid #0084ff;
            background-color: #0084ff;
            color: #fff;
            size: 14px;
            border-radius: 4px;
            font-weight: 900;
            /* position: absolute;
            margin-left: 1072px; */
            float: right;
            margin-top: 5px;
        }
        .body {
            width: 100%;
            height: 850px;
        }
        .pro {
            width: 1000px;
            margin: 0 auto;
            margin-bottom: 10px;
            background-color: #fff;
        }
        .problem-title {
            padding-left: 20px;
            padding-right: 7px;
            padding-bottom: 22px;
        }
        .problem-title a {
            font-size: 16px;
            font-weight: 900;
            color: #1a1a1a;
            line-height: 32px;
        }
        .problem-info {
            width: 220px;
            height: 20px;
            font-size: 13px;
            line-height: 21px;
            float: right;
        }
        #userName {
            color: #005a94;
            font-weight: 700;
        }
        #span {
            font-weight: 700;
        }
        .content {
            width: 1000px;
            background-color: #fff;
            margin: 0 auto;
        }
        .content-detalis {
            padding: 10px 13px 22px 20px;
            border-bottom: 1px dashed #ccc;
            margin-bottom: 8px;
        }
        .content-detalis h6 {
            line-height: 21px;
            font-size: 14px;
            font-weight: 500;
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
            var replyContent = $("#text").val();
            var problemId = $("#problemId").val();
            var reolyName = $("#name").val();
            $.post("/saveReply", {replyContent:replyContent, problemId:problemId, reolyName:reolyName}, function (obj) {
                if(obj.flag) {
                    alert("回复成功!");
                    location.href = "/toForum";
                } else {
                    alert(obj.message);
                }
            }, "json")
        }

        function toQuestions() {
            location.href = "/toQuestions";
        }
    </script>
</head>
<body>
<div class="box">
    <div class="head">
        <div class="img">
            <img src="https://my-images-1301935246.cos.ap-shanghai.myqcloud.com/uugai.com_1587522616355.png">
            <input type="button" class="questions" value="提问" onclick="toQuestions()"/>
        </div>
    </div>
    <div class="body">
        <div class="pro">
            <div class="problem-title">
                <a href="#" title="${tbProblem.problemTitle!''}">
                    ${tbProblem.problemTitle!''}
                </a><br/>
                <div class="problem-info">
                    <span id="userName">${tbProblem.creatBy!''}</span> 发布于 <span id="span">${tbProblem.creatTime?datetime}</span>
                </div>
            </div>
        </div>
        <div class="content">
            <#if replyList?? && (replyList?size > 0)>
                <#list replyList as reply>
                    <div class="content-detalis">
                        <h6>
                            ${reply.replyContent!''}
                        </h6>
                        <div class="problem-info">
                            <span id="userName">${reply.replyName!''}</span> 发布于 <span id="span">${reply.replyTime?datetime}</span>
                        </div>
                    </div>
                </#list>
            <#else>
                <span>暂无评论哦^_^</span>
            </#if>
        </div>
        <div class="boot">
            <input type="hidden" id="problemId" value="${problemId!''}" />
            <input type="hidden" id="name" value="${userName!''}" />
            <textarea placeholder="想对作者说点什么" id="text"></textarea>
            <input type="button" value="发表评论" class="forumButton" onclick="comment()"/>
        </div>
    </div>
</div>
</body>
</html>