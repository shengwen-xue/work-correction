<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>作业批改系统-注册</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/method/register.js"></script>
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
            height: 678px;
            background-color: #f7f7f7;
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
            width: 416px;
            height: 515px;
            border: 1px solid e8e8e8;
            text-align: center;
            margin: 14px auto;
        }
        .body-title {
            width: 415px;
            height: 38px;
            background-color: #f5f5f5;
            border: 1px solid #e8e8e8;
        }
        .body-title h1 {
            font-size: 14px;
            color: #333333;
            padding: 12px 0 12px 0;
        }
        .body-from {
            width: 416px;
            height: 477px;
            background-color: #ffffff;
        }
        .from-userName {
            float: left;
            padding: 20px 0 9px 16px;
            color: #717171;
        }
        .textName {
            width: 380px;
            height: 33px;
            text-indent: 8px;
            margin: 0 16px 0 16px;
            border: 1px solid #d9d9d9;
            float: left;
        }
        .from-password {
            float: left;
            padding: 20px 0 9px 16px;
            color: #717171;
        }
        .textPassword {
            width: 380px;
            height: 33px;
            text-indent: 8px;
            margin: 0 16px 0 16px;
            border: 1px solid #d9d9d9;
            float: left;
        }
        .from-mobileNumber {
            float: left;
            padding: 20px 0 9px 16px;
            color: #717171;
        }
        .textPhone {
            width: 380px;
            height: 33px;
            text-indent: 8px;
            margin: 0 16px 0 16px;
            border: 1px solid #d9d9d9;
            float: left;
        }
        .from-verificationCode {
            float: left;
            padding: 14px 0 6px 16px;
            color: #717171;
        }
        .textCode {
            width: 210px;
            height: 33px;
            text-indent: 8px;
            margin: 0 0 0 16px;
            border: 1px solid #d9d9d9;
            float: left;
        }
        #codeId {
            width: 155px;
            height: 34px;
            background-color: #4cc0c1;
            font-size: 14px;
            color: #fffffb;
            border: 1px solid #4cc0c1;
        }
        .from-reset {
            width: 83px;
            height: 33px;
            background-color: #4cc0c1;
            margin: 3px 0 10px 16px;
            border: 1px solid #4cc0c1;
            font-size: 12px;
            font-weight: 700;
            color: #fffffb;
            float: left;
        }
        .from-loginButton {
            width: 285px;
            height: 33px;
            background-color: #4cc0c1;
            margin: 3px 16px 10px 16px;
            border: 1px solid #4cc0c1;
            font-size: 12px;
            font-weight: 700;
            color: #fffffb;
            float: right;
        }
        .line {
            width: 382px;
            margin: 55px 13px 10px 13px;
            border: 1px dashed #eaeaea;
        }
        .hint {
            font-size: 13px;
            color: #979797;
            padding: 5px 0 5px 0;
            text-align: center;
        }
        .from-registButton {
            width: 380px;
            height: 33px;
            color: #717171;
            font-size: 14px;
            text-align: center;
            background-color: #fafafa;
            border: 1px solid #dadada;
        }
    </style>
</head>
<body>
<div class="box">
    <div class="head">
        <h1>作业批改系统-注册</h1>
    </div>
    <div class="body">
        <div class="body-title">
            <h1>注册</h1>
        </div>
        <div class="body-from">
            <form action="">
                <h2 class="from-userName">用户名</h2><br/><br/><br/>
                <input type="text" name="username" placeholder="请输入用户名" class="textName"><br/><br/>
                <h2 class="from-password">密码</h2><br/><br/><br/>
                <input type="password" name="password" placeholder="请输入密码" class="textPassword"><br/><br/>
                <h2 class="from-mobileNumber">手机号</h2><br/><br/><br/>
                <input type="text" name="mobileNumber" placeholder="请输入11位手机号" class="textPhone"><br/><br/>
                <h2 class="from-verificationCode">验证码</h2><br/><br/><br/>
                <input type="text" name="verificationCode" placeholder="请输入6位短信验证码" class="textCode">
                <input type="button" id="codeId" value="点击获取验证码" onclick="sendMessage()"><br/><br/>
                <input type="reset" value="重置" class="from-reset">
                <input type="button" value="注册" class="from-loginButton" onclick="signIn()">
                <div class="line"></div>
                <h2 class="hint">已经有账户了？</h2>
                <input type="button" value="登录" class="from-registButton" onclick="redirect()">
            </form>
        </div>
    </div>
</div>
</body>
</html>