<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
<head>

    <base href="<%=basePath%>">

        <title>尊老金后台管理</title>


    <meta http-equiv="pragma" content="no-cache">

    <meta http-equiv="cache-control" content="no-cache">

    <meta http-equiv="expires" content="0">


    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" href="<%=basePath%>/css/login/style.css">

    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/vector.js"></script>

</head>
<body>

<div id="container">
    <div id="output">
        <div class="containerT">
            <h1>用户登录</h1>
            <form class="form" id="entry_form">
                <input type="text" placeholder="用户名" id="entry_name">
                <input type="password" placeholder="密码" id="entry_password">
                <button type="button" id="entry_btn">登录</button>
                <div id="prompt" class="prompt"></div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        Victor("container", "output");
        $("#entry_name").focus();
        $(document).keydown(function (event) {
            if (event.keyCode == 13) {
                $("#entry_btn").click();
            }
        });
    });

    $("#entry_btn").on("click",function () {

        var username = $("#entry_name").val();
        var password = $("#entry_password").val();
        $.post("<%=basePath%>/user/login",{"username":username,"password":password},function (data) {

            if (data.code == 0){
                var userId = data.data;
                window.location.href="<%=basePath%>/roster/indexPage?loginId="+userId;
            }else {
                alert(data.message);
            }

        });

    })
</script>
</body>
</html>
