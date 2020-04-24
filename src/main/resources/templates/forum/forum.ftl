<!DOCTYPE html>
<html>
<head>
    <title>论坛</title>
    <meta charset="utf-8">
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
            height: 900px;
            background-color: #f7f7f7;
        }
        .label {
            width: 100%;
            height: 45px;
            float: left;
            background-color: #000;
        }
        .label-ul {
            width: 852px;
            height: 44px;
            margin: 0 250px 0 250px;
        }
        .label ul li {
            float: left;
            padding: 0 29px;
            line-height: 42px;
            font-size: 15px;
        }

        .body {
            width: 854px;
            height: 850px;
            /* margin: 45px 250px 0px 250px; */
            background-color: #f7f7f7;
        }
        .content-con {
            padding-left: 7px;
            padding-right: 7px;
            text-indent: 2em;
            font-size: 16px;
            line-height: 30px;
            background-color: #fff;
            padding-bottom: 22px;
            margin: 0px 0px 10px 0px;
        }
        .reply-count {
            width: 320px;
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
        .img{
            width: 78px;
            height: 20px;
            float: right;
        }
        .img a {
            padding-left: 2px;
        }
        .img a:hover {
            color: #349edf;
            font-weight: 700;
        }
        .content-con:hover {
            border-bottom: 1px solid #349edf;
        }
        .content-con a:hover{
            font-weight: 700;
        }
        .body-left {
            width: 200px;
            height: 450px;
            float: left;
            margin: 40px 25px 0 20px;
        }
        .body-left img {
            width: 200px;
            height: 450px;
        }
        .body-right {
            width: 200px;
            height: 450px;
            float: right;
            margin: 40px 15px 0 25px;
        }
        .body-right img {
            width: 200px;
            height: 450px;

        }
        .first a {
            background-color:#e75636;
        }

        /* 清除li样式 */
        ul,li{
            list-style: none;
        }
        /* 将ul相对定位，目的是让内容的div绝对定位时相对ul定位，否则会相对body定位 */
        /* ul弹性盒，目的是让li横着排，也可以将li浮动或者转行内块 */
        ul{
            position:relative;
            display:flex;
        }
        /* 将三个li宽度平分，高度60px */
        li{
            flex:1;
            height:60px;
        }
        /* input隐藏 */
        input{
            display:none;
        }
        /* 设置input的下一个节点label的样式 */
        input+label{
            display:block;
            width: 78px;
            height: 45px;
            font-weight: 700;
            color: #E0FFFF;
            text-align: center;
            line-height: 45px;
        }
        /* 设置div内容的基础样式 隐藏内容div*/
        input+label+div{
            display:none;
            position: absolute;
            left:0;
            top:60px;
        }
        /* input选中状态时候对应的label的样式 */
        input:checked+label{
            font-weight: 700;
            background: #e75636;
        }
        /* input选中时候显示对应的div */
        input:checked+label+div{
            display:block;
        }
    </style>
</head>
<body>
<div class="box">
    <strong>
        <marquee behavior="scroll" direction="right" loop="-1" scrollamount="10">
            <h1>欢迎来到论坛，在这里您可以尽情的和小伙伴分享您的疑难问题</h1>
        </marquee>
    </strong>
    <div class="label">
        <div class="label-ul">
            <ul>
                <li>
                    <input type="radio" name="check" id="active1" checked><label for="active1">推荐</label>
                    <div class="body">
                        <div class="content">
                            <#if problemDTOList?? && (problemDTOList?size > 0)>
                                <#list problemDTOList as problemDTO>
                                    <input type="hidden" value="${problemDTO.id!''}" />
                                    <div class="content-con">
                                        <a href="http://localhost:8081/toDetails?id=${problemDTO.id!''}" title="${problemDTO.problemTitle!''}" target="_blank">${problemDTO.problemTitle!''}</a><br/>
                                        <div class="reply-count">
                                            <span id="userName">${problemDTO.creatBy!''}</span> 发布于 <span id="span">${problemDTO.creatTime?datetime}</span>
                                            <div class="img"><img src="http://common.cnblogs.com/images/icon_comment.gif" width="18px" height="17px"><a href="#">${problemDTO.replyCount!'0'}</a></div>
                                        </div>
                                    </div>
                                </#list>
                            <#else>
                                <span>暂无数据哦^_^</span>
                            </#if>
                        </div>
                    </div>
                </li>
                <li>
                    <input type="radio" name="check" id="active2"><label for="active2">Java</label>
                    <div class="body">
                        <div class="content">
                            <#if problemDTOListByJava?? && (problemDTOListByJava?size > 0)>
                                <#list problemDTOListByJava as javaProblem>
                                    <input type="hidden" value="${javaProblem.id!''}" />
                                    <div class="content-con">
                                        <a href="http://localhost:8081/toDetails?id=${javaProblem.id!''}" title="${javaProblem.problemTitle!''}" target="_blank">${javaProblem.problemTitle!''}</a><br/>
                                        <div class="reply-count">
                                            <span id="userName">${javaProblem.creatBy!''}</span> 发布于 <span id="span">${javaProblem.creatTime?datetime}</span>
                                            <div class="img"><img src="http://common.cnblogs.com/images/icon_comment.gif" width="18px" height="17px"><a href="#">${javaProblem.replyCount!'0'}</a></div>
                                        </div>
                                    </div>
                                </#list>
                            <#else>
                                <span>暂无数据哦^_^</span>
                            </#if>
                        </div>
                    </div>
                </li>
                <li>
                    <input type="radio" name="check" id="active3"><label for="active3">PHP</label>
                    <div class="body">
                        <div class="content">
                            <#if problemDTOListByPhp?? && (problemDTOListByPhp?size > 0)>
                                <#list problemDTOListByPhp as phpProblem>
                                    <input type="hidden" value="${phpProblem.id!''}" />
                                    <div class="content-con">
                                        <a href="http://localhost:8081/toDetails?id=${phpProblem.id!''}" title="${phpProblem.problemTitle!''}" target="_blank">${phpProblem.problemTitle!''}</a><br/>
                                        <div class="reply-count">
                                            <span id="userName">${phpProblem.creatBy!''}</span> 发布于 <span id="span">${phpProblem.creatTime?datetime}</span>
                                            <div class="img"><img src="http://common.cnblogs.com/images/icon_comment.gif" width="18px" height="17px"><a href="#">${phpProblem.replyCount!'0'}</a></div>
                                        </div>
                                    </div>
                                </#list>
                            <#else>
                                <span>暂无数据哦^_^</span>
                            </#if>
                        </div>
                    </div>
                </li>
                <li>
                    <input type="radio" name="check" id="active4"><label for="active4">Python</label>
                    <div class="body">
                        <div class="content">
                            <#if problemDTOListByPython?? && (problemDTOListByPython?size > 0)>
                                <#list problemDTOListByPython as pythonProblem>
                                    <input type="hidden" value="${pythonProblem.id!''}" />
                                    <div class="content-con">
                                        <a href="http://localhost:8081/toDetails?id=${pythonProblem.id!''}" title="${pythonProblem.problemTitle!''}" target="_blank">${pythonProblem.problemTitle!''}</a><br/>
                                        <div class="reply-count">
                                            <span id="userName">${pythonProblem.creatBy!''}</span> 发布于 <span id="span">${pythonProblem.creatTime?datetime}</span>
                                            <div class="img"><img src="http://common.cnblogs.com/images/icon_comment.gif" width="18px" height="17px"><a href="#">${pythonProblem.replyCount!'0'}</a></div>
                                        </div>
                                    </div>
                                </#list>
                            <#else>
                                <span>暂无数据哦^_^</span>
                            </#if>
                        </div>
                    </div>
                </li>
                <li>
                    <input type="radio" name="check" id="active5"><label for="active5">JavaScript</label>
                    <div class="body">
                        <div class="content">
                            <#if problemDTOListByJavaScript?? && (problemDTOListByJavaScript?size > 0)>
                                <#list problemDTOListByJavaScript as javaScriptProblem>
                                    <input type="hidden" value="${javaScriptProblem.id!''}" />
                                    <div class="content-con">
                                        <a href="http://localhost:8081/toDetails?id=${javaScriptProblem.id!''}" title="${javaScriptProblem.problemTitle!''}" target="_blank">${javaScriptProblem.problemTitle!''}</a><br/>
                                        <div class="reply-count">
                                            <span id="userName">${javaScriptProblem.creatBy!''}</span> 发布于 <span id="span">${javaScriptProblem.creatTime?datetime}</span>
                                            <div class="img"><img src="http://common.cnblogs.com/images/icon_comment.gif" width="18px" height="17px"><a href="#">${javaScriptProblem.replyCount!'0'}</a></div>
                                        </div>
                                    </div>
                                </#list>
                            <#else>
                                <span>暂无数据哦^_^</span>
                            </#if>
                        </div>
                    </div>
                </li>
                <li>
                    <input type="radio" name="check" id="active6"><label for="active6">Vue</label>
                    <div class="body">
                        <div class="content">
                            <#if problemDTOListByVue?? && (problemDTOListByVue?size > 0)>
                                <#list problemDTOListByVue as vueProblem>
                                    <input type="hidden" value="${vueProblem.id!''}" />
                                    <div class="content-con">
                                        <a href="http://localhost:8081/toDetails?id=${vueProblem.id!''}" title="${vueProblem.problemTitle!''}" target="_blank">${vueProblem.problemTitle!''}</a><br/>
                                        <div class="reply-count">
                                            <span id="userName">${vueProblem.creatBy!''}</span> 发布于 <span id="span">${vueProblem.creatTime?datetime}</span>
                                            <div class="img"><img src="http://common.cnblogs.com/images/icon_comment.gif" width="18px" height="17px"><a href="#">${vueProblem.replyCount!'0'}</a></div>
                                        </div>
                                    </div>
                                </#list>
                            <#else>
                                <span>暂无数据哦^_^</span>
                            </#if>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="body-left">
        <a href="http://www.cac.gov.cn/2020-03/03/c_1584794079763058.htm" target="_blank"><img src="https://my-images-1301935246.cos.ap-shanghai.myqcloud.com/wuhanjiayou.gif" title="众志成城 共克时艰"></a>
    </div>
    <div class="body-right">
        <a href="https://isheji5.com/picture/273420.html" target="_blank"><img src="https://my-images-1301935246.cos.ap-shanghai.myqcloud.com/1.png" title="设计坞 招聘hr"></a>
    </div>
</div>
</body>
</html>